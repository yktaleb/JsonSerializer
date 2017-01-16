package mappers;

import serializer.JsonSerializer;
import writer.JsonWriter;

import java.io.IOException;

public class BooleanMapper extends JsonMapper<Boolean> {

    public BooleanMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Boolean obj, JsonWriter writer) throws IOException {
        if (!isNull(obj))
            writer.writeBoolean(obj);
        else
            writer.writeNull();
    }
}
