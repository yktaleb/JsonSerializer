package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;

public class PrimitiveArrayMapper extends JsonMapper<Object> {

    public PrimitiveArrayMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Object obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            if (Array.getLength(obj) != 0) {
                writer.writeArrayBegin();
                for (int i = 0; i < Array.getLength(obj); i++) {
                    Object object = Array.get(obj, i);
                    if (object != null) {
                        serializer.serialize(object, writer);
                    } else {
                        writer.writeNull();
                    }
                    if (i != Array.getLength(obj) - 1) {
                        writer.writeSeparator();
                    }
                }
                writer.writeArrayEnd();
            } else {
                writer.writeEmptyArray();
            }
        } else
            writer.writeNull();
    }
}
