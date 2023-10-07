package com.example.usermanagementms.exception.type;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundExceptionType {

    private String target;
    private Map<String, Object>fields;

    public static NotFoundExceptionType of(String target, Map<String, Object> fields) {
        return NotFoundExceptionType.builder()
                .fields(fields)
                .target(target)
                .build();
    }
}
