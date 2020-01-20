package com.diff.controller.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 * Converts a base64 encoded http body to a decoded string
 */
@Component
public class StringBase64BodyConverter implements  BodyConverter<String, String> {

    @Override
    public String convert(String body) {
        return new String(Base64Utils.decodeFromString(body));
    }

}
