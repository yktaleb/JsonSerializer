package mappers;

import org.junit.Before;
import org.junit.Test;
import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapMapperTest {
    JsonSerializer serializer;
    StringWriter stringWriter;
    Map map;

    @Before
    public void initialize() throws Exception {
        serializer = new JsonSerializer();
        stringWriter = new StringWriter();
        serializer.setIndent(false);

        map = new HashMap<String, Integer>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
    }

    @Test
    public void write() throws Exception {
        MapMapper mapMapper = new MapMapper(serializer);
        JsonWriter writer = new JsonWriter(stringWriter);
        mapMapper.write(map, writer);
        writer.flush();

        assertEquals("{\"third\":3,\"first\":1,\"second\":2}", stringWriter.toString());
    }
}
