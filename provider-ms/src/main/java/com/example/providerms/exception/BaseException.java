package com.example.providerms.exception;


import com.example.providerms.exception.type.NotFoundExceptionType;
import com.example.providerms.response.enums.ResponseMessage;
import lombok.*;

import java.util.Map;

import static com.example.providerms.response.enums.ErrorResponseMessages.NOT_FOUND;
import static com.example.providerms.response.enums.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private ResponseMessage responseMessage;
    private NotFoundExceptionType notFoundData;

    // todo: fix. it doesn't return dynamic error message
    @Override
    public String getMessage() {
        return responseMessage.message();
    }

    public static BaseException of(ResponseMessage responseMessage) {
        return BaseException.builder().responseMessage(responseMessage).build();
    }

    public static BaseException unexpected() {
        return of(UNEXPECTED);
    }

    public static BaseException notFound(String target, String field, String value) {
        BaseException baseException = BaseException.builder()
                .responseMessage(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field, value))
                )
                .build();

        return baseException;
    }

}
