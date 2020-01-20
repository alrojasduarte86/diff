package com.diff.controller.converter;

/**
 * Defines a HTTP body converter
 * @param <B> The type of the body
 * @param <C> The class to which the body should be converted
 */
public interface BodyConverter<B, C> {
    C convert(B body);
}