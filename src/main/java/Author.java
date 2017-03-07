import annotations.JsonIgnore;
import annotations.JsonProperty;

public class Author {
    public String name;
    @JsonProperty
    private String country;
    @JsonIgnore
    public Integer yearOfBirth;

    public Author(String name, String country, Integer yearOfBirth) {
        this.name = name;
        this.country = country;
        this.yearOfBirth = yearOfBirth;
    }
}
