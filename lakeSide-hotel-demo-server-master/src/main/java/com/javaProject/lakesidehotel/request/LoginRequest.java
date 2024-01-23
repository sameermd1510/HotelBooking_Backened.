package com.javaProject.lakesidehotel.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Mohammad Sameer
 */
@Data
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
