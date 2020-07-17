package hello.mn.domain;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;


@Repository
public interface FruitRepository extends CrudRepository<Fruit,Long>{
    List<Fruit>  findBySeason(String s);
}
