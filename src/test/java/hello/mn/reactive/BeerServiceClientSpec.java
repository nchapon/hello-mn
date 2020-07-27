package hello.mn.reactive;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@MicronautTest
public class BeerServiceClientSpec {

    @Inject
    BeerServiceClient beerServiceClient;

    @Test
    void getBeersFromPunkapi() {

        List<Beer> beers = beerServiceClient.getBeers(Optional.empty());

        Assertions.assertNotNull(beers);

    }
}
