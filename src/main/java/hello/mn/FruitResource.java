package hello.mn;


import hello.mn.domain.Fruit;
import hello.mn.domain.FruitRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Get
    public List<Fruit> listFruits(@QueryValue Optional<String> season){

        if (season.isPresent()){
            return fruitRepository.findBySeason(season.get());
        }
        return fruitRepository.findAll();

    }

    @Post
    public HttpResponse<Fruit> save(String name,String season){
        Fruit fruit = new Fruit(name, season);

        final Fruit createdFruit = fruitRepository.create(fruit);

        return HttpResponse.created(createdFruit)
                .headers(headers -> headers.location(makeUriLocation(createdFruit.getId())));
    }

    private URI makeUriLocation(Long id) {
        return URI.create("/fruits/"+id);
    }


}
