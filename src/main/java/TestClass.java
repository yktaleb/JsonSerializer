import annotations.JsonIgnore;
import annotations.JsonProperty;

import java.util.Map;

public class TestClass {
    String stringValue;

    public int a;

    public Object object;

    @JsonProperty(name = "a")
    private double[] b;

    private int firstNumber;

    @JsonProperty
    private Integer secondNumber;

    @JsonIgnore
    @JsonProperty(name = "stringValue")
    public String string;

    @JsonProperty(name = "isRight")
    protected transient boolean isTrue;

    public Book book;

    public Boolean mapa;

    @JsonProperty(name = "mapa")
    public Map c;

    public int[][] array;

    public TestClass(String stringValue, int a, Object object, double[] b, int firstNumber, Integer secondNumber, String string, boolean isTrue, Book book, Boolean mapa, Map c, int[][] array) {
        this.stringValue = stringValue;
        this.a = a;
        this.object = object;
        this.b = b;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.string = string;
        this.isTrue = isTrue;
        this.book = book;
        this.mapa = mapa;
        this.c = c;
        this.array = array;
    }
}
