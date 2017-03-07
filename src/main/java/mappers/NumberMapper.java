package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;

public class NumberMapper extends JsonMapper<Number> {

    public NumberMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Number obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            writer.writeNumber(obj);
        } else {
            writer.writeNull();
        }
    }
}
