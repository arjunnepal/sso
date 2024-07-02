package com.custom.spring.sso.consent;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class CompositeKey implements Serializable {
    private String registrationClientId;
    private String principalName;
}
