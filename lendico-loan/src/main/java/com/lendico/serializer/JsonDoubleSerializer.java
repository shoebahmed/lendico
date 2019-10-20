package com.lendico.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDoubleSerializer extends JsonSerializer<Double> {

  @Override
  public void serialize(Double value, JsonGenerator generator, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    generator.writeNumber(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP));
  }
}
