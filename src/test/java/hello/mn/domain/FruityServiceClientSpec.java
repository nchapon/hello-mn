package hello.mn.domain;

import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Single;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class FruityServiceClientSpec {

    @Inject
    FruityService fruityService;

    @Test
    void testGetFruitByName() {

        FruityVice fruit = fruityService.getFruitByName("Banana").blockingGet();

        assertEquals("Banana", fruit.getName());
        assertEquals(22, fruit.getNutritions().getCarbohydrates());
        assertEquals(96, fruit.getNutritions().getCalories());

    }
}
