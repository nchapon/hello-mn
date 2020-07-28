package hello.mn;


import hello.mn.domain.*;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;
    private final FruityServiceClient fruityServiceClient;

    public FruitResource(FruitRepository fruitRepository, FruityServiceClient fruityService) {
        this.fruitRepository = fruitRepository;
        this.fruityServiceClient = fruityService;
    }

    @Get
    public List<FruitDTO> listFruits(@QueryValue Optional<String> season){

        if (season.isPresent()){
            return fruitRepository.findBySeason(season.get()).stream()
                    .map(f -> FruitDTO.of(f, fruityServiceClient.getFruitByName(f.getName())))
                    .collect(Collectors.toList());
        }
        return CollectionUtils.iterableToList(fruitRepository.findAll()).stream()
                .map(f -> FruitDTO.of(f, fruityServiceClient.getFruitByName(f.getName())))
                .collect(Collectors.toList());

    }

    @Get("/{id}")
    public HttpResponse<Fruit> getFruits(@PathVariable Long id){

        Optional<Fruit> fruit = fruitRepository.findById(id);

        if (fruit.isPresent()){
            return HttpResponse.status(HttpStatus.OK).body(fruit.get());
        } else {
            return HttpResponse.notFound();
        }

    }

    @Post
    public HttpResponse<Fruit> save(String name,String season){
        Fruit fruit = new Fruit(name, season);

        final Fruit createdFruit = fruitRepository.save(fruit);

        return HttpResponse.created(createdFruit)
                .headers(headers -> headers.location(makeUriLocation(createdFruit.getId())));
    }

    @Put("/{id}")
    public HttpResponse<Fruit> update(@PathVariable Long id, String name,String season){
        Fruit fruit = new Fruit(name, season);
        fruit.setId(id);

        Fruit updatedFruit = fruitRepository.update(fruit);

        return HttpResponse.status(HttpStatus.OK)
                .body(updatedFruit)
                .headers(headers -> headers.location(makeUriLocation(updatedFruit.getId())));
    }


    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Long id){

        Optional<Fruit> fruitToBeDeleted = fruitRepository.findById(id);

        if (fruitToBeDeleted.isPresent()){
            fruitRepository.delete(fruitToBeDeleted.get());
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    private URI makeUriLocation(Long id) {
        return URI.create("/fruits/"+id);
    }
}
