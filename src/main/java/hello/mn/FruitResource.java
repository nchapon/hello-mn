package hello.mn;


import hello.mn.domain.Fruit;
import hello.mn.domain.FruitDTO;
import hello.mn.domain.FruitRepository;
import hello.mn.domain.FruityService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;
    private final FruityService fruityService;

    public FruitResource(FruitRepository fruitRepository, FruityService fruityService) {
        this.fruitRepository = fruitRepository;
        this.fruityService = fruityService;
    }

    @Get
    public List<FruitDTO> listFruits(@QueryValue Optional<String> season){

        if (season.isPresent()){
            return fruitRepository.findBySeason(season.get()).stream()
                    .map(f -> FruitDTO.of(f, fruityService.getFruitByName(f.getName()).blockingGet()))
                    .collect(Collectors.toList());
        }
        return CollectionUtils.iterableToList(fruitRepository.findAll()).stream()
                .map(f -> FruitDTO.of(f, fruityService.getFruitByName(f.getName()).blockingGet()))
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
