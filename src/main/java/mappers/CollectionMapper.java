package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionMapper extends JsonMapper<Collection<?>> {

    public CollectionMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Collection<?> obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            Iterator<?> iterator = obj.iterator();
            if (!iterator.hasNext() == false) {
                writer.writeArrayBegin();
                while (iterator.hasNext()) {
                    Object object = iterator.next();
                    if (object != null) {
                        serializer.serialize(object, writer);
                    } else {
                        writer.writeNull();
                    }
                    if (iterator.hasNext()) {
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
