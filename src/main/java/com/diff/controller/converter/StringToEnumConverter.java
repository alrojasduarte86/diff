package com.diff.controller.converter;

import com.diff.data.DiffSide;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, DiffSide> {
    @Override
    public DiffSide convert(String source) {
        return DiffSide.valueOf(source.toUpperCase());
    }
}