package hello.mn.domain;

import java.util.List;

public interface FruitRepository {

    List<Fruit> findAll();

    List<Fruit> findBySeason(String season);

    Fruit create(Fruit fruit);
}
