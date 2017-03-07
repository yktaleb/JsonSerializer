package mappers;

import annotations.JsonIgnore;
import annotations.JsonProperty;
import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class PojoMapper extends JsonMapper<Object>{

    public PojoMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Object obj, JsonWriter writer) throws IOException {
        List<Field> allFields = getFieldsCheckedByName(obj.getClass().getDeclaredFields());
        List<Field> fields = getSerializableFields(allFields);
        if (!isNull(obj)) {
            writer.writeObjectBegin();
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                try {
                    Object object;
                    if (field.getType().isArray()) {
                        object = createArray(field.get(obj));
                    } else {
                        object = field.get(obj);
                    }
                    writeField(getFieldName(field), object, writer);
                    if (i != fields.size() - 1) {
                        writer.writeSeparator();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            writer.writeObjectEnd();
        } else
            writer.writeNull();
    }

    private List<Field> getSerializableFields(List<Field> fields) {
        List<Field> serializableFileds = new ArrayList<Field>();
        for (int i = 0; i < fields.size(); i++) {
            if (isSerializable(fields.get(i))) {
                serializableFileds.add(fields.get(i));
            }
        }
        return serializableFileds;
    }

    private List<Field> getFieldsCheckedByName(Field[] fields) {
        List<Field> checkedFields = new ArrayList<Field>();
        boolean isAnnotatedCurrentField = false;
        boolean canAdd = true;
        String nameCurrentField = null;
        for (int i = 0; i < fields.length; i++) {
            Field currentField = fields[i];
            if (currentField.isAnnotationPresent(JsonProperty.class) && !currentField.isAnnotationPresent(JsonIgnore.class)) {
                isAnnotatedCurrentField = true;
                nameCurrentField = currentField.getAnnotation(JsonProperty.class).name();
                if (nameCurrentField.equals(JsonProperty.NULL) || nameCurrentField == null) {
                    nameCurrentField = currentField.getName();
                }
            } else {
                nameCurrentField = currentField.getName();
            }
            for (int j = 0; j < fields.length; j++) {
                if (!fields[j].isAnnotationPresent(JsonProperty.class) || fields[j].isAnnotationPresent(JsonIgnore.class)) {
                    continue;
                }
                if (isAnnotatedCurrentField ) {
                    if (i > j) {
                        if (isNameFiedEqualsAnnotationName(nameCurrentField, fields[j])) {
                            canAdd = false;
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (i != j) {
                        if (isNameFiedEqualsAnnotationName(nameCurrentField, fields[j])) {
                            canAdd = false;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (!canAdd) {
                canAdd = true;
                isAnnotatedCurrentField = false;
                continue;
            }
            isAnnotatedCurrentField = false;
            checkedFields.add(currentField);
        }
        return checkedFields;
    }

    private boolean isNameFiedEqualsAnnotationName(String nameCurrentField, Field annotationField) {
        String annotationName = annotationField.getAnnotation(JsonProperty.class).name();
        if (annotationName.equals(JsonProperty.NULL) || annotationName == null) {
            annotationName = annotationField.getName();
        }
        if (annotationName.equals(nameCurrentField)) {
            return true;
        }
        return false;
    }

    private Object[] createArray(Object object) {
        Object[] array = new Object[Array.getLength(object)];
        for(int i = 0; i < array.length; i++) {
            array[i] = Array.get(object, i);
        }
        return array;
    }

    private void writeField(String name, Object obj, JsonWriter writer) throws IOException {
        writer.writeString(name);
        writer.writePropertySeparator();
        serializer.serialize(obj, writer);
    }

    private boolean isSerializable(Field field) {
        if (!field.isAnnotationPresent(JsonIgnore.class)) {
            if (Modifier.isPublic(field.getModifiers()) || field.getModifiers() == 0) {
                if (!Modifier.isTransient(field.getModifiers())) {
                    return true;
                }
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
