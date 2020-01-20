package com.diff.test.unit.controller.converter;


import static org.junit.Assert.assertEquals;

import com.diff.controller.converter.StringBase64BodyConverter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
class StringBase64BodyConverterTest {

	private StringBase64BodyConverter converter = new StringBase64BodyConverter();

	@Test
	void shouldConvertEncodedBody() {
		String expectedBody = "{\"id\":\"12345\"}";
		String encodedBody = "eyJpZCI6IjEyMzQ1In0=";
		assertEquals(expectedBody, converter.convert(encodedBody));
	}

}
