package com.thoughtworks.capacity.gtb.mvc.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResult {
    private String message;
}
