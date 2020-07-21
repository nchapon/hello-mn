package hello.mn.domain;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;


public interface FruityService {

    @Get(value = "/api/fruit/{name}", produces = MediaType.APPLICATION_JSON)
    FruityVice getFruitByName(String name);

}
