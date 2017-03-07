package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;

public class ObjectArrayMapper extends JsonMapper<Object[]> {

    public ObjectArrayMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Object[] obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            if (obj.length != 0) {
                writer.writeArrayBegin();
                for (int i = 0; i < obj.length; i++) {
                    Object object = obj[i];
                    if (obj[i] != null) {
                        serializer.serialize(object, writer);
                    } else {
                        writer.writeNull();
                    }
                    if (i != obj.length - 1) {
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
