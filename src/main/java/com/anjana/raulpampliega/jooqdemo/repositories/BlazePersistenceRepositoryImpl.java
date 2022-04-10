package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import com.blazebit.persistence.CriteriaBuilderFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Qualifier("BlazePersistenceRepository")
public class BlazePersistenceRepositoryImpl implements CustomRepository {

  @PersistenceContext
  private final EntityManager entityManager;
  private final CriteriaBuilderFactory cbf;

  @Override
  public List<Entity> getEntityByIds(List<Integer> ids) {
    return cbf.create(entityManager, Entity.class)
        .where("id").in(ids)
        .getResultList();
  }

  @Override
  public List<AttributeValue> getAllAttributeValues() {
    return cbf.create(entityManager, AttributeValue.class)
        .orderByAsc("id")
        .getResultList();
  }

  @Override
  public List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType) {
    String sourceToFind = String.format("'" + ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID + "'", sourceType,
        sourceId);
    return cbf.create(entityManager, RelationShip.class)
        .where("source").like().expression(sourceToFind).noEscape()
        .getResultList();
  }

  @Override
  public List<RelationShip> getDatasetFlags(String username, Integer datasetId) {
    String dsaFilter = String.format("'" + ARI_PARTIAL_ENTITY_OBJECTSUBTYPE + "'", "DSA");
    String datasetFilter = String.format("'" + ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID + "'", "DATASET",
        datasetId);
    return cbf.create(entityManager, RelationShip.class)
        .from(RelationShip.class, "r")
        .where("r.destinationType").eq("USER")
        .where("r.destination").eq(username)
        .where("r.source").in()
        .from(RelationShip.class, "r1")
        .where("r1.objectSubType").eq("DSA_DATASET")
        .where("r1.source").like().expression(dsaFilter).noEscape()
        .where("r1.destination").like().expression(datasetFilter).noEscape()
        .select("r1.source")
        .end()
        .getResultList();
  }
}
