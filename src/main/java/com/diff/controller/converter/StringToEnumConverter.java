package com.diff.controller.converter;

import com.diff.data.DiffSide;
import org.springframework.core.convert.converter.Converter;

/**
 * Implementation of a spring converter that
 * takes a string value an converts it to a DiffSide enum.
 * This converter is used to convert the "lef"/"right" portion
 * of the PUT request of the DiffController to an enum
 */
public class StringToEnumConverter implements Converter<String, DiffSide> {
    @Override
    public DiffSide convert(String source) {
        return DiffSide.valueOf(source.toUpperCase());
    }
}