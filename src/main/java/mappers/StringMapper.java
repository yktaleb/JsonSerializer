package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;

public class StringMapper extends JsonMapper<String> {

    public StringMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(String obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            writer.writeString(obj);
        } else {
            writer.writeNull();
        }
    }
}
