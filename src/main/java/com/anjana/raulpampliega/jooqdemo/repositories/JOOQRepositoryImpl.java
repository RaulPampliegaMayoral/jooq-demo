package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooq.model.Tables;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class JOOQRepositoryImpl implements CustomRepository {

  @Autowired
  DataSource ds;

  @Bean
  public DSLContext context() {
    return DSL.using(ds, SQLDialect.POSTGRES, new Settings().withRenderNameStyle(RenderNameStyle.AS_IS));
  }

  @Override
  public List<Entity> getEntityByIds(List<Integer> ids) {
    return context().selectFrom(Tables.ENTITY)
        .where(Tables.ENTITY.ID_ENTITY.in(ids))
        .fetchInto(Entity.class);
  }

  @Override
  public List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType) {
    return context().selectFrom(Tables.RELATIONSHIP)
        .where(Tables.RELATIONSHIP.ID_SOURCE.like(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, sourceType, sourceId)))
        .fetchInto(RelationShip.class);
  }

  @Override
  public List<RelationShip> getDatasetFlags(String username, Integer datasetId) {
    return context().selectFrom(Tables.RELATIONSHIP)
        .where(Tables.RELATIONSHIP.DESTINATION_TYPE.equal("USER"))
        .and(Tables.RELATIONSHIP.ID_DESTINATION.equal(username))
        .and(Tables.RELATIONSHIP.ID_SOURCE.in(
            context().select(Tables.RELATIONSHIP.ID_SOURCE).from(Tables.RELATIONSHIP)
            .where(Tables.RELATIONSHIP.OBJECT_SUB_TYPE.equal("DSA_DATASET"))
            .and(Tables.RELATIONSHIP.ID_SOURCE.like(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE, "DSA")))
            .and(Tables.RELATIONSHIP.ID_DESTINATION.like(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, "DATASET", datasetId)))
            .fetch(Record1::value1))
        )
        .fetchInto(RelationShip.class);
  }
}
