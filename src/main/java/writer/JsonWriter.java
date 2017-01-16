package writer;

import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    protected Writer writer;

    protected static final char OBJECT_BEGIN = '{';
    protected static final char OBJECT_END = '}';
    protected static final char ARRAY_BEGIN = '[';
    protected static final char ARRAY_END = ']';
    protected static final char SEPARATOR = ',';
    protected static final char PROPERTY_SEPARATOR = ':';
    protected static final String NULL = "null";
    protected static final char QUOTE = '\"';

    public JsonWriter() {

    }

    public JsonWriter(Writer writer) {
        this.writer = writer;
    }

    public void writeObjectBegin() throws IOException {
        writer.write(OBJECT_BEGIN);
    }

    public void writeObjectEnd() throws IOException {
        writer.write(OBJECT_END);
    }

    public void writeArrayBegin() throws IOException {
        writer.write(ARRAY_BEGIN);
    }

    public void writeArrayEnd() throws IOException {
        writer.write(ARRAY_END);
    }

    public void writeString(String value) throws IOException {
        writer.append(QUOTE).append(value).write(QUOTE);
    }

    public void writeNumber(Number value) throws IOException {
        writer.write(String.valueOf(value));
    }

    public void writeBoolean(Boolean value) throws IOException {
        writer.write(String.valueOf(value));
    }

    public void writeNull() throws IOException {
        writer.write(NULL);
    }

    public void writeSeparator() throws IOException {
        writer.write(SEPARATOR);
    }

    public void writePropertySeparator() throws IOException {
        writer.write(PROPERTY_SEPARATOR);
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
