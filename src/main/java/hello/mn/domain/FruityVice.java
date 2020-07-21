package hello.mn.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FruityVice {

    private final String name;

    private final Nutritions nutritions;

    @JsonCreator
    public FruityVice(@JsonProperty("name") String name,
                      @JsonProperty("nutritions") Nutritions nutritions
                      ) {
        this.name = name;
        this.nutritions = nutritions;
    }

    public String getName() {
        return name;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }

    public static class Nutritions {
        private final double carbohydrates;
        private final double calories;

        @JsonCreator
        public Nutritions(@JsonProperty("carbohydrates") double carbohydrates,
                          @JsonProperty("calories") double calories) {
            this.carbohydrates = carbohydrates;
            this.calories = calories;
        }

        public double getCalories() {
            return calories;
        }

        public double getCarbohydrates() {
            return carbohydrates;
        }
    }
}
