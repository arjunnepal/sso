package com.custom.spring.sso.client_registration;

import lombok.Getter;

@Getter
public class CustomRegistrationClientResourceMini {
    private final String id;
    private final String clientName;

    public CustomRegistrationClientResourceMini(String id, String clientName) {
        this.id = id;
        this.clientName = clientName;
    }
}
