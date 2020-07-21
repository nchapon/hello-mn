package hello.mn.domain;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("fruity")
public interface FruityService {

    @Get(value = "/api/fruit/{name}", produces = MediaType.APPLICATION_JSON)
    Single<FruityVice> getFruitByName(String name);

}
