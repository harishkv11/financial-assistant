package com.harishverma.financial.assistant.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@ToString
public class AuthResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }
}
