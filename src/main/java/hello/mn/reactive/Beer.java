package hello.mn.reactive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Beer {

    private final String name;
    private final String tagline;
    private final double abv;

    @JsonCreator
    public Beer(@JsonProperty("name") String name,
                @JsonProperty("tagline") String tagline,
                @JsonProperty("abv") double abv) {
        this.name = name;
        this.tagline = tagline;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public double getAbv() {
        return abv;
    }


}
