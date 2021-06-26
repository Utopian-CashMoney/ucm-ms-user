package com.ucm.ms.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Helper Class to bypass Default Spring BcryptPasswordEncoder so 
 * our application can just validate a regualr password from
 * database rather then Bcrpt password
 * 
 * @author Charvin Patel
 */


public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}