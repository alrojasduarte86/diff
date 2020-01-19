package com.waes.diff.controller.converter;

import com.waes.diff.data.DiffSide;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, DiffSide> {
    @Override
    public DiffSide convert(String source) {
        return DiffSide.valueOf(source.toUpperCase());
    }
}