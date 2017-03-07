package mappers;

import org.junit.Before;
import org.junit.Test;
import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ArrayMapperTest {

    private JsonSerializer serializer;
    private StringWriter stringWriter;
    private int[] primitiveArray;
    private Integer[] objectArray;


    @Before
    public void initialize() throws Exception {
        serializer = new JsonSerializer();
        stringWriter = new StringWriter();
        primitiveArray =  new int[]{10,8,1};
        objectArray =  new Integer[]{10,8,1};
    }

    @Test
    public void testObjectArrayMapper() throws Exception {
        ObjectArrayMapper arrayMapper = new ObjectArrayMapper(serializer);
        JsonWriter writer = new JsonWriter(stringWriter);
        arrayMapper.write(objectArray, writer);
        writer.flush();

        assertEquals("[10,8,1]", stringWriter.toString());
    }

    @Test
    public void testPrimitiveArrayMapper() throws Exception {
        PrimitiveArrayMapper arrayMapper = new PrimitiveArrayMapper(serializer);
        JsonWriter writer = new JsonWriter(stringWriter);
        arrayMapper.write(primitiveArray, writer);
        writer.flush();

        assertEquals("[10,8,1]", stringWriter.toString());
    }
}
