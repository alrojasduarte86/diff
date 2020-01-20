package com.diff.test.unit.controller.converter;

import com.diff.controller.converter.StringToEnumConverter;
import com.diff.data.Diff;
import com.diff.data.DiffSide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StringToEnumConverterTest {

    private StringToEnumConverter converter = new StringToEnumConverter();

    @Test
    public void shouldConvertLeftString(){
        DiffSide side = converter.convert("lEfT");
        assertEquals(DiffSide.LEFT, side);
    }

    @Test
    public void shouldConvertRightString(){
        DiffSide side = converter.convert("rIgHt");
        assertEquals(DiffSide.RIGHT, side);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException(){
        DiffSide side = converter.convert("rIgHtf");
        assertEquals(DiffSide.RIGHT, side);
    }
}