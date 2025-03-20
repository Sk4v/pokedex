package com.skav.pokedex.response;

import lombok.Data;

@Data
public class HttpResponse<T> {
    private String status;
    private String message;
    private T data;
}
