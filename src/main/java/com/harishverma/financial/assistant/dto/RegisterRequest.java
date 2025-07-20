package com.harishverma.financial.assistant.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterRequest {
    private String email;
    private String password;
}

