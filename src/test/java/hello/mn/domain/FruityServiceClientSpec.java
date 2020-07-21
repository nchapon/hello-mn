package hello.mn.domain;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class FruityServiceClientSpec {

    @Inject
    FruityServiceClient fruityServiceClient;

    @Test
    void testGetFruitByName() {

        FruityVice fruit = fruityServiceClient.getFruitByName("Banana");

        assertEquals("Banana", fruit.getName());
        assertEquals(22, fruit.getNutritions().getCarbohydrates());
        assertEquals(96, fruit.getNutritions().getCalories());

    }
}
