package hello.mn.persistance;

import hello.mn.domain.Fruit;
import hello.mn.domain.FruitRepository;
import io.micronaut.transaction.annotation.ReadOnly;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class FruitRepositoryImpl implements FruitRepository {

    private final EntityManager entityManager;

    public FruitRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public List<Fruit> findAll() {
        String hql="select f from Fruit as f";

        TypedQuery<Fruit> query = entityManager.createQuery(hql, Fruit.class);
        return query.getResultList();
    }

    @Override
    @ReadOnly
    public List<Fruit> findBySeason(String season) {
        String hql="select f from Fruit as f " +
                "where f.season = :season";
        return entityManager.createQuery(hql, Fruit.class)
                .setParameter("season",season)
                .getResultList();
    }

    @Override
    @Transactional
    public Fruit create(Fruit fruit) {
        entityManager.persist(fruit);
        return fruit;
    }



}
