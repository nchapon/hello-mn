package hello.mn.reactive;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.Optional;

@Client("punkapi")
public interface BeerServiceClient {

    @Get(value = "/v2/beers{?page}", produces = MediaType.APPLICATION_JSON)
    List<Beer> getBeers(Optional<Integer> page);

}
