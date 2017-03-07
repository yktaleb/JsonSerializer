package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;

public abstract class JsonMapper<T> {

    protected JsonSerializer serializer;

    public JsonMapper(JsonSerializer serializer) {
        this.serializer = serializer;
    }

    protected boolean isNull(T obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    public abstract void write(T obj, JsonWriter writer) throws IOException;


}
