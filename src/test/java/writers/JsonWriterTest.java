package writers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class JsonWriterTest {
    private StringWriter stringWriter;
    private JsonWriter jsonWriter;

    @Before
    public void initialize() {
        stringWriter = new StringWriter();
        jsonWriter = new JsonWriter(stringWriter);
    }

    @After
    public void close() {
        jsonWriter = null;
        stringWriter = null;
    }

    @Test
    public void testWriteObjectBegin() throws IOException {
        jsonWriter.writeObjectBegin();
        jsonWriter.flush();

        assertEquals("{", stringWriter.toString());
    }

    @Test
    public void testWriteObjectEnd() throws IOException {
        jsonWriter.writeObjectEnd();
        jsonWriter.flush();

        assertEquals("}", stringWriter.toString());
    }

    @Test
    public void testWriteArrayBegin() throws IOException {
        jsonWriter.writeArrayBegin();
        jsonWriter.flush();

        assertEquals("[", stringWriter.toString());
    }

    @Test
    public void testWriteArrayEnd() throws IOException {
        jsonWriter.writeArrayEnd();
        jsonWriter.flush();

        assertEquals("]", stringWriter.toString());
    }

    @Test
    public void testWriteString() throws IOException {
        jsonWriter.writeString("someString");
        jsonWriter.flush();

        assertEquals("\"someString\"", stringWriter.toString());
    }

    @Test
    public void testWriteNumber() throws IOException {
        jsonWriter.writeNumber(11.04f);
        jsonWriter.flush();

        assertEquals("11.04", stringWriter.toString());
    }

    @Test
    public void testWriteBooleanTrue() throws IOException {
        jsonWriter.writeBoolean(true);
        jsonWriter.flush();

        assertEquals("true", stringWriter.toString());
    }

    @Test
    public void testWriteBooleanFalse() throws IOException {
        jsonWriter.writeBoolean(false);
        jsonWriter.flush();

        assertEquals("false", stringWriter.toString());
    }


    @Test
    public void testWriteNull() throws IOException {
        jsonWriter.writeNull();
        jsonWriter.flush();

        assertEquals("null", stringWriter.toString());
    }

    @Test
    public void testWriteSeparator() throws IOException {
        jsonWriter.writeSeparator();
        jsonWriter.flush();

        assertEquals(",", stringWriter.toString());
    }

    @Test
    public void testWritePropertySeparator() throws IOException {
        jsonWriter.writePropertySeparator();
        jsonWriter.flush();

        assertEquals(":", stringWriter.toString());
    }
}
