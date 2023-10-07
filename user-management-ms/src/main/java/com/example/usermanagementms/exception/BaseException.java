package com.example.usermanagementms.exception;


import com.example.usermanagementms.enums.response.ResponseMessage;
import com.example.usermanagementms.exception.type.NotFoundExceptionType;
import lombok.*;

import java.util.Map;

import static com.example.usermanagementms.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.example.usermanagementms.enums.response.ErrorResponseMessages.UNEXPECTED;

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
        return BaseException.builder()
                .responseMessage(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field, value))
                )
                .build();
    }

}
