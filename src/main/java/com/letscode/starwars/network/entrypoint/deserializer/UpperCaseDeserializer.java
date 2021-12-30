package com.letscode.starwars.network.entrypoint.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Optional;

public class UpperCaseDeserializer extends JsonDeserializer<String> {


  @Override
  public String deserialize(final JsonParser jsonParser,
                            final DeserializationContext deserializationContext) throws IOException {
    return Optional.ofNullable(jsonParser.getValueAsString())
            .map(String::toUpperCase)
            .orElse(jsonParser.getValueAsString());
  }
}
