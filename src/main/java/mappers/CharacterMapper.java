package mappers;

import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.IOException;

public class CharacterMapper extends JsonMapper<Character> {

    public CharacterMapper(JsonSerializer serializer) {
        super(serializer);
    }

    @Override
    public void write(Character obj, JsonWriter writer) throws IOException {
        if (!isNull(obj)) {
            writer.writeString(obj.toString());
        } else {
            writer.writeNull();
        }
    }
}


