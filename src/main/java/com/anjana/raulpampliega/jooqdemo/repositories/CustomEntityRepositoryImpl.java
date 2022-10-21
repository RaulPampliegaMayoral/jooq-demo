package com.anjana.raulpampliega.jooqdemo.repositories;

import static org.hibernate.jpa.QueryHints.HINT_READONLY;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

@Slf4j
@RequiredArgsConstructor
public class CustomEntityRepositoryImpl implements CustomEntityRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public Statistics getSessionStatistics() {
    return entityManager.unwrap(Session.class).getSessionFactory().getStatistics();
  }

  public List<Entity> findWithKeySetPagination(int size) {
    List<Entity> values = entityManager
        .createQuery("select e from Entity e order by e.id", Entity.class)
        .setMaxResults(size)
        .setHint(HINT_READONLY, true)
        .getResultList();
    entityManager.clear();
    return values;
  }

  public List<Entity> findWithKeySetPagination(int last, int size) {
    List<Entity> values = entityManager
        .createQuery("select e from Entity e where e.id > :last order by e.id", Entity.class)
        .setParameter("last", last)
        .setMaxResults(size)
        .setHint(HINT_READONLY, true)
        .getResultList();
    entityManager.clear();
    return values;
  }

  public void saveAll(Collection<Entity> entities, int batchSize) {
    if (CollectionUtils.isEmpty(entities)) {
      return;
    }

    StatelessSession session = entityManager.unwrap(Session.class).getSessionFactory()
        .openStatelessSession();

    if (batchSize > 0) {
      session.setJdbcBatchSize(batchSize);
    }

    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();

      int i = 0;
      for (Entity entity : entities) {
        if (i % batchSize == 0 && i > 0) {
          transaction.commit();
          transaction.begin();
        }

        session.insert(entity);
        i++;
      }

      transaction.commit();
    } catch (RuntimeException e) {
      if (Objects.nonNull(transaction) && transaction.isActive()) {
        transaction.rollback();
      }
      throw e;
    } finally {
      session.close();
    }
  }

}
