package mappers;

import org.junit.Before;
import org.junit.Test;
import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CollectionMapperTest {
    private JsonSerializer serializer;
    private StringWriter stringWriter;
    private ArrayList<Double> list;

    @Before
    public void initialize() throws Exception {
        serializer = new JsonSerializer();
        stringWriter = new StringWriter();

        list = new ArrayList<>();
        list.add(new Double(120));
        list.add(null);
        list.add(new Double(0.012));
        list.add(new Double(92039));
    }

    @Test
    public void write() throws Exception {
        CollectionMapper collectionMapper = new CollectionMapper(serializer);
        JsonWriter writer = new JsonWriter(stringWriter);
        collectionMapper.write(list, writer);
        writer.flush();

        assertEquals("[120.0,null,0.012,92039.0]", stringWriter.toString());
    }
}
