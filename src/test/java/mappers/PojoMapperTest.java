package mappers;

import annotations.JsonProperty;
import org.junit.Before;
import org.junit.Test;
import serializer.JsonSerializer;
import writers.JsonWriter;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class PojoMapperTest {
    JsonSerializer serializer;
    StringWriter stringWriter;

    String jSonString;

    @Before
    public void setUp() throws Exception {
        serializer = new JsonSerializer();
        stringWriter = new StringWriter();
    }

    @Test
    public void write() throws Exception {
        class Book {
            public String name;
            @JsonProperty
            private int year;
            @JsonProperty(name = "author")
            protected String auth;

            public Book(String name, int year, String auth) {
                this.name = name;
                this.year = year;
                this.auth = auth;
            }
        }
        Book book = new Book("Java 8", 2016, "Shildt");
        PojoMapper pojoMapper = new PojoMapper(serializer);
        JsonWriter writer = new JsonWriter(stringWriter);
        pojoMapper.write(book, writer);
        writer.flush();

        assertEquals("{\"name\":\"Java 8\",\"year\":2016,\"author\":\"Shildt\"}", stringWriter.toString());
    }
}
