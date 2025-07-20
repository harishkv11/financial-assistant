package com.harishverma.financial.assistant.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@ToString
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
