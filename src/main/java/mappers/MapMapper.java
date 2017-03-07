package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MapMapper extends JsonMapper<Map<Object, Object>> {

    public MapMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Map<Object, Object> obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            Iterator<Object> iterator = obj.keySet().iterator();
            if (!iterator.hasNext() == false) {
                writer.writeObjectBegin();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    writer.writeString(key.toString());
                    writer.writePropertySeparator();
                    Object object = obj.get(key);
                    if (object != null) {
                        serializer.serialize(object, writer);
                    } else {
                        writer.writeNull();
                    }
                    if (iterator.hasNext()) {
                        writer.writeSeparator();
                    }
                }
                writer.writeObjectEnd();
            } else {
                writer.writeEmptyObject();
            }
        } else
            writer.writeNull();
    }
}
