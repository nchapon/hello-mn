package hello.mn.monitoring;


import hello.mn.domain.Fruit;
import hello.mn.domain.FruitRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;

import java.awt.*;

@Endpoint(id = "ready",
        prefix = "custom",
        defaultEnabled = true,
        defaultSensitive = false)
public class ReadynessProbe {

    private final FruitRepository fruitRepository;

    public ReadynessProbe(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Read(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Status> returnReadyStatus(){

        Iterable<Fruit> fruits = fruitRepository.findAll();
        if (CollectionUtils.iterableToList(fruits).size() > 0){
            return HttpResponse.ok().body(Status.UP);
        }
        return HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE);
    }


}
