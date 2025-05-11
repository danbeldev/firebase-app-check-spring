package com.github.danbel.spring.firebase.appcheck.exception;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Обработчик ошибок, возникающих при валидации токена Firebase App Check.
 * <p>
 * Используется в контексте {@code HandlerInterceptor} для перехвата и обработки исключений,
 * связанных с проверкой безопасности Firebase App Check. Метод {@link #handle} вызывается,
 * если в процессе проверки токена произошла ошибка, и должен вернуть решение — продолжать выполнение запроса или нет.
 */
@FunctionalInterface
public interface FirebaseAppCheckErrorHandler {

    /**
     * Обрабатывает ошибку валидации токена Firebase App Check.
     *
     * @param e         исключение, возникшее при валидации токена
     * @param errorType тип ошибки Firebase App Check, соответствующий исключению
     * @param request   текущий HTTP-запрос
     * @param response  текущий HTTP-ответ
     * @param handler   объект-обработчик, к которому относится текущий запрос
     * @return {@code true}, если выполнение запроса может быть продолжено; {@code false}, если запрос должен быть прерван
     */
    boolean handle(
            Exception e,
            FirebaseAppCheckErrorType errorType,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    );
}
