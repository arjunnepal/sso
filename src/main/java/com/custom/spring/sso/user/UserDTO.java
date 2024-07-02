package com.custom.spring.sso.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    Map<String, Object> attributes;
    @NotBlank(message = "[username] is required.")
    private String username;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    @NotBlank(message = "[firstName] is required.")
    private String firstName;
    private String middleName;
    @NotBlank(message = "[lastName] is required.")
    private String lastName;

    public UserDTO(String username, String firstName, String middleName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}