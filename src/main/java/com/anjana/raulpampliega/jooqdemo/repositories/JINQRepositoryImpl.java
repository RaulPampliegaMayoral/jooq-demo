package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jinq.jpa.JPQL;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.stereotype.Repository;

@Repository
public class JINQRepositoryImpl implements CustomRepository {

  @PersistenceContext
  private EntityManager entityManager;
  private JinqJPAStreamProvider jinqJPAStreamProvider;

  public JINQRepositoryImpl(EntityManager entityManager,
      JinqJPAStreamProvider jinqJPAStreamProvider) {
    this.entityManager = entityManager;
    this.jinqJPAStreamProvider = jinqJPAStreamProvider;
  }

  @Override
  public List<Entity> getEntityByIds(List<Integer> ids) {
    return jinqJPAStreamProvider.streamAll(entityManager, Entity.class)
        .where(entity -> ids.contains(entity.getId()))
        .collect(Collectors.toList());
  }

  @Override
  public List<AttributeValue> getAllAttributeValues() {
    return jinqJPAStreamProvider.streamAll(entityManager, AttributeValue.class)
        .collect(Collectors.toList());
  }

  @Override
  public List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType) {
    String sourceToFind = String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, sourceType, sourceId);
    return jinqJPAStreamProvider.streamAll(entityManager, RelationShip.class)
        .where(relationShip -> JPQL.like(relationShip.getSource(), sourceToFind))
        .collect(Collectors.toList());
  }

  @Override
  public List<RelationShip> getDatasetFlags(String username, Integer datasetId) {
    String dsaAriPrefix = String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE, "DSA");
    String datasetAriPrefix = String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, "DATASET",
        datasetId);

    List<String> dsaDatasets = jinqJPAStreamProvider.streamAll(entityManager, RelationShip.class)
        .where(relationShip -> relationShip.getObjectSubType().equals("DSA_DATASET"))
        .where(relationShip -> JPQL.like(relationShip.getSource(), dsaAriPrefix))
        .where(relationShip -> JPQL.like(relationShip.getDestination(), datasetAriPrefix))
        .select(RelationShip::getSource)
        .collect(Collectors.toList());

    return jinqJPAStreamProvider.streamAll(entityManager, RelationShip.class)
        .where(relationShip -> "USER".equals(relationShip.getDestinationType()))
        .where(relationShip -> username.equals(relationShip.getDestination()))
        /*.where(relationShip -> JPQL.isIn(relationShip.getSource(),
            jinqJPAStreamProvider.streamAll(entityManager, RelationShip.class)
                .where(relationShip2 -> relationShip2.getObjectSubType().equals("DSA_DATASET"))
                .where(relationShip2 -> JPQL.like(relationShip2.getSource(), dsaAriPrefix))
                .where(relationShip2 -> JPQL.like(relationShip2.getDestination(), datasetAriPrefix))
                .select(RelationShip::getSource)))*/
        .where(relationShip -> dsaDatasets.contains(relationShip.getSource()))
        .collect(Collectors.toList());
  }
}
