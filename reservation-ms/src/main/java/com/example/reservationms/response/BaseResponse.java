package com.example.reservationms.response;

import com.example.reservationms.exception.BaseException;
import com.example.reservationms.exception.type.NotFoundExceptionType;
import com.example.reservationms.response.enums.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.example.reservationms.response.enums.ErrorResponseMessages.NOT_FOUND;
import static com.example.reservationms.response.enums.SuccessResponseMessage.SUCCESS;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private HttpStatus status;
    private Meta meta;
    private T data;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static final class Meta {
        private String key;
        private String message;

        public static Meta of(String key, String message) {
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }

        public static Meta of(ResponseMessage responseMessages) {
            return of(responseMessages.key(), responseMessages.message());
        }

        public static Meta of(BaseException ex) {
            if (ex.getResponseMessage().equals(NOT_FOUND)) {
                NotFoundExceptionType notFoundData = ex.getNotFoundData();

                return of(
                        String.format(ex.getResponseMessage().key(), notFoundData.getTarget().toLowerCase()),
                        String.format(ex.getResponseMessage().message(), notFoundData.getTarget(), notFoundData.getFields().toString())
                );
            }

            return of(ex.getResponseMessage());
        }

    }

    public static <T> BaseResponse <T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of(SUCCESS))
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse<?> error(BaseException ex) {
        return BaseResponse.builder()
                .meta(Meta.of(ex))
                .status(ex.getResponseMessage().status())
                .build();
    }
}
