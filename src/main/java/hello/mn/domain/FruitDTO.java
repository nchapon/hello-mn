package hello.mn.domain;

public class FruitDTO {

    private final String name;
    private final String season;
    private final double carbohydrates;
    private final double calories;

    public FruitDTO(String name, String season, double carbohydrates, double calories) {
        this.name = name;
        this.season = season;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
    }

    public static FruitDTO of(Fruit fruit, FruityVice fruityVice){
        return new FruitDTO(fruit.getName(),
                fruit.getSeason(),
                fruityVice.getNutritions().getCarbohydrates(),
                fruityVice.getNutritions().getCalories());
    }

    public String getName() {
        return name;
    }

    public String getSeason() {
        return season;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getCalories() {
        return calories;
    }
}
