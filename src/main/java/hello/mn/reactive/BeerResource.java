package hello.mn.reactive;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Controller("/beers")
public class BeerResource {

    private final BeerServiceClient beerServiceClient;


    public BeerResource(BeerServiceClient beerServiceClient) {
        this.beerServiceClient = beerServiceClient;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Observable<Beer> getBeers(){


        return Observable.create(emitter -> {

            try {

                int page = 1;
                boolean hasMoreBeers = true;
                do {

                    List<Beer> beers = getBeers(page);
                    if (beers.isEmpty()) {
                        hasMoreBeers = false;
                        emitter.onComplete();
                    } else {
                        page++;
                       beers.stream().filter(beer -> (beer.getAbv() >15.0)).forEach(new Consumer<Beer>() {
                           @Override
                           public void accept(Beer beer) {
                               emitter.onNext(beer);
                           }
                       });
                    }
                } while (hasMoreBeers);

            } catch (Exception e) {
                emitter.onError(e);
            }

        });
    }


    private List<Beer> getBeers(int page)  {
        System.out.println("page = " + page);
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return beerServiceClient.getBeers(Optional.of(page));
    }

    @Get(value="/sync",  produces = MediaType.APPLICATION_JSON)
    public List<Beer> getBeersSync(){

        List<Beer> result = new ArrayList<>();

        int page=1;
        boolean hasElements=true;
        do {
            List<Beer> beers = getBeers(page);
            if (beers.isEmpty()){
                hasElements=false;
            } else {
                page++;
                result.addAll(beers.stream().filter(b -> (b.getAbv() > 15.0)).collect(Collectors.toList()));
            }

      } while(hasElements);
        return result;

    }

}
