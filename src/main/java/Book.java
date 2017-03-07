import annotations.JsonProperty;

public class Book {

    public String name;
    @JsonProperty
    private int year;
    @JsonProperty(name = "author")
    protected Author auth;

    public Book(String name, int year, Author auth) {
        this.name = name;
        this.year = year;
        this.auth = auth;
    }
}
