package com.github.danbel.spring.firebase.appcheck;

import com.github.danbel.spring.firebase.appcheck.common.TestConstants;
import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.impl.FirebaseAppCheckErrorHandlerImpl;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckCallback;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckSecureStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FirebaseAppCheckProperties properties;

    @BeforeEach
    void setUp() {
        properties.setEnabled(true);
        properties.setSecureStrategy(FirebaseAppCheckSecureStrategy.PROTECT_ANNOTATED_OR_ROUTES);
        properties.setProjectId(TestConstants.FIREBASE_PROJECT_ID);
        properties.setProjectNumber(TestConstants.FIREBASE_PROJECT_NUMBER);
        properties.setHeaderName("X-Firebase-AppCheck");
        properties.getRoutes().clear();
        properties.addRoute(HttpMethod.POST, "/route");
        properties.setAdditionalSecurityCheck(null);
        properties.setErrorHandler(new FirebaseAppCheckErrorHandlerImpl());
    }

    @Nested
    class SecurityStrategyTests {
        @Test
        void protectAllStrategy_shouldSecureAllEndpoints() throws Exception {
            properties.setSecureStrategy(FirebaseAppCheckSecureStrategy.PROTECT_ALL);

            mockMvc.perform(get("/unsecured")).andExpect(status().isUnauthorized());
            mockMvc.perform(get("/annotation")).andExpect(status().isUnauthorized());
            mockMvc.perform(post("/route")).andExpect(status().isUnauthorized());
        }

        @Test
        void protectAnnotatedStrategy_shouldSecureOnlyAnnotatedEndpoints() throws Exception {
            properties.setSecureStrategy(FirebaseAppCheckSecureStrategy.PROTECT_ANNOTATED);

            mockMvc.perform(get("/unsecured")).andExpect(status().isOk());
            mockMvc.perform(get("/annotation")).andExpect(status().isUnauthorized());
            mockMvc.perform(post("/route")).andExpect(status().isOk());
        }

        @Test
        void protectRoutesStrategy_shouldSecureOnlyConfiguredRoutes() throws Exception {
            properties.setSecureStrategy(FirebaseAppCheckSecureStrategy.PROTECT_ROUTES);

            mockMvc.perform(get("/unsecured")).andExpect(status().isOk());
            mockMvc.perform(get("/annotation")).andExpect(status().isOk());
            mockMvc.perform(post("/route")).andExpect(status().isUnauthorized());
        }

        @Test
        void protectAnnotatedOrRoutesStrategy_shouldSecureBoth() throws Exception {
            properties.setSecureStrategy(FirebaseAppCheckSecureStrategy.PROTECT_ANNOTATED_OR_ROUTES);

            mockMvc.perform(get("/unsecured")).andExpect(status().isOk());
            mockMvc.perform(get("/annotation")).andExpect(status().isUnauthorized());
            mockMvc.perform(post("/route")).andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class ConfigurationTests {
        @Test
        void whenAppCheckDisabled_thenAllEndpointsShouldBeAccessible() throws Exception {
            properties.setEnabled(false);

            mockMvc.perform(get("/unsecured")).andExpect(status().isOk());
            mockMvc.perform(get("/annotation")).andExpect(status().isOk());
            mockMvc.perform(post("/route")).andExpect(status().isOk());
        }

        @Test
        void withValidToken_shouldAllowAccess() throws Exception {
            mockMvc.perform(get("/annotation")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isOk());

            mockMvc.perform(post("/route")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isOk());
        }

        @Test
        void withInvalidProjectNumber_shouldDenyAccess() throws Exception {
            properties.setProjectNumber("11111");

            mockMvc.perform(get("/annotation")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void withInvalidProjectId_shouldDenyAccess() throws Exception {
            properties.setProjectId("222");

            mockMvc.perform(post("/route")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void withCustomHeaderName_shouldWorkCorrectly() throws Exception {
            properties.setHeaderName("CUSTOM-FIREBASE-APP-CHECK");

            mockMvc.perform(get("/annotation")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isUnauthorized());

            mockMvc.perform(post("/route")
                            .header("CUSTOM-FIREBASE-APP-CHECK", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class EdgeCaseTests {
        @Test
        void withMalformedToken_shouldReturnUnauthorized() throws Exception {
            mockMvc.perform(get("/annotation")
                            .header("X-Firebase-AppCheck", "malformed-token"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void withoutToken_shouldReturnUnauthorized() throws Exception {
            mockMvc.perform(post("/route"))
                    .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    class AdditionalChecksTests {
        @Test
        void whenAdditionalSecurityCheckReturnsFalse_shouldDenyAccess() throws Exception {
            properties.setAdditionalSecurityCheck((ignore) -> false);

            mockMvc.perform(post("/route")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void whenAdditionalSecurityCheckReturnsTrue_shouldAllowAccess() throws Exception {
            properties.setAdditionalSecurityCheck((ignore) -> true);

            mockMvc.perform(post("/route")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isOk());
        }

        @Test
        void afterSecurityCheckCallback_shouldBeExecuted() throws Exception {
            var firebaseAppCheckCallback = Mockito.mock(FirebaseAppCheckCallback.class);
            properties.setAfterSecurityCheck(firebaseAppCheckCallback);

            mockMvc.perform(post("/route")
                            .header("X-Firebase-AppCheck", TestConstants.TOKEN_OF_THE_PROJECT))
                    .andExpect(status().isOk());

            Mockito.verify(firebaseAppCheckCallback, Mockito.times(1)).execute(Mockito.any());
        }
    }

    @Nested
    class ErrorHandlerTests {
        @Test
        void customErrorHandler_shouldBeCalledOnError() throws Exception {
            var errorHandler = Mockito.mock(FirebaseAppCheckErrorHandler.class);
            properties.setErrorHandler(errorHandler);

            mockMvc.perform(post("/route")).andExpect(status().isOk());

            Mockito.verify(errorHandler, Mockito.times(1))
                    .handle(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        }

        @Test
        void customErrorHandlerCanChangeResponseStatus() throws Exception {
            FirebaseAppCheckErrorHandler errorHandler =
                    (e, errorType, request, response, handler) -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        return false;
                    };
            properties.setErrorHandler(errorHandler);

            mockMvc.perform(post("/route")).andExpect(status().isInternalServerError());
        }
    }
}
