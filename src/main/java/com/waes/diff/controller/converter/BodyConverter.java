package com.waes.diff.controller.converter;

public interface BodyConverter<B, C> {
    C convert(B body);
}