package hello.mn.domain;

import io.micronaut.retry.annotation.Fallback;

@Fallback
public class FruityServiceFallback implements FruityService {

    public static final FruityVice EMPTY = new FruityVice("empty", new FruityVice.Nutritions(0.0, 0.0));

    @Override
    public FruityVice getFruitByName(String name) {
        return EMPTY;
    }
}
