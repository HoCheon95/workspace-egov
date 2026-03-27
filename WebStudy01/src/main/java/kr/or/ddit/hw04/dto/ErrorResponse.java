package kr.or.ddit.hw04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private ConversionRequest caused;
}
