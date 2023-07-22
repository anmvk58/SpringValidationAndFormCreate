package com.anmv.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SuccessObject {
    @NonNull
    private String httpCode;
    @NonNull
    private String message;

}
