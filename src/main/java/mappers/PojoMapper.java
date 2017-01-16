package mappers;

import annotations.JsonIgnore;
import annotations.JsonProperty;
import serializer.JsonSerializer;
import writer.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PojoMapper extends JsonMapper<Object>{

    public PojoMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Object obj, JsonWriter writer) throws IOException {
        Field[] allFields = obj.getClass().getDeclaredFields();
        if (!isNull(obj)) {
            writer.writeObjectBegin();
            for (Field field : allFields) {
                if (isSerializable(field)) {
                    writeField(getFieldName(field), obj, writer);
                }
            }
            writer.writeObjectBegin();
        } else
            writer.writeNull();
    }

    private void writeField(String name, Object obj, JsonWriter writer) throws IOException {
        writer.writeString(name);
        writer.writePropertySeparator();
        serializer.serialize(obj);
    }

    private boolean isSerializable(Field field) {
        if (!field.isAnnotationPresent(JsonIgnore.class)) {
            if (Modifier.isPublic(field.getModifiers())) {
                if (Modifier.isTransient(field.getModifiers()) && field.isAnnotationPresent(JsonProperty.class)) {
                    return true;
                }
            } else if (field.isAnnotationPresent(JsonProperty.class)) {
                return true;
            }
        }
        return false;
    }

    private String getFieldName(Field field) {
        String defaultName = field.getName();
        if (field.isAnnotationPresent(JsonProperty.class)) {
            String annotationName = field.getAnnotation(JsonProperty.class).name();
            if (annotationName.equals(JsonProperty.NULL) || annotationName == null) {
                return defaultName;
            } else {
                return annotationName;
            }
        }
        return defaultName;
    }
}
