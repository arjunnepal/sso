package com.custom.spring.sso;

import com.custom.spring.sso.web.AuthorizationConsentController;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.thymeleaf.expression.Lists;

import java.util.Arrays;

@SpringBootApplication
@EnableMethodSecurity
@ImportRuntimeHints(SsoApplication.SsoApplicationRuntimeHintsRegistrar.class)
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

    static class SsoApplicationRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            // Thymeleaf
            hints.reflection().registerTypes(
                    Arrays.asList(
                            TypeReference.of(AuthorizationConsentController.ScopeWithDescription.class),
                            TypeReference.of(Lists.class)
                    ), builder ->
                            builder.withMembers(MemberCategory.DECLARED_FIELDS,
                                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS)
            );
        }

    }

}
