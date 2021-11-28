package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
import com.anjana.raulpampliega.jooqdemo.model.AttributeValue$;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.Entity$;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip$;
import com.speedment.jpastreamer.application.JPAStreamer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class JPAStreamerRepositoryImpl implements CustomRepository {

  private final JPAStreamer jpaStreamer;

  public JPAStreamerRepositoryImpl(JPAStreamer jpaStreamer) {
    this.jpaStreamer = jpaStreamer;
  }

  @Override
  public List<Entity> getEntityByIds(final List<Integer> ids) {
    if (ids == null)
      return new ArrayList<>();

//   SQL GENERATED
//    select
//    entity0_.id_entity as id_entit1_0_,
//        entity0_.create_user as create_u2_0_,
//    entity0_.form_saved as form_sav3_0_,
//        entity0_.id_parent as id_paren4_0_,
//    entity0_.last_modified_date as last_mod5_0_,
//        entity0_.module as module6_0_,
//    entity0_.name as name7_0_,
//        entity0_.object_sub_type as object_s8_0_,
//    entity0_.organizational_unit as organiza9_0_,
//        entity0_.start_date as start_d10_0_,
//    entity0_.state as state11_0_,
//        entity0_.validity_date as validit12_0_,
//    entity0_.version as version13_0_
//        from
//    entity entity0_
//    where
//    entity0_.id_entity in (x , y , z)
//    order by
//    entity0_.id_entity asc
    return jpaStreamer.stream(Entity.class)
        .filter(Entity$.id.in(ids))
        .sorted(Entity$.id).collect(Collectors.toList());
  }

  @Override
  public List<AttributeValue> getAllAttributeValues() {
    return jpaStreamer.stream(AttributeValue.class)
        .sorted(AttributeValue$.id)
        .collect(Collectors.toList());
  }

  @Override
  public List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType) {
    return jpaStreamer.stream(RelationShip.class)
        .filter(RelationShip$.source.startsWith(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, sourceType, sourceId)))
        .sorted(RelationShip$.id)
        .collect(Collectors.toList());
  }

  @Override
  public List<RelationShip> getDatasetFlags(String username, Integer datasetId) {
    return jpaStreamer.stream(RelationShip.class)
        .filter(RelationShip$.destinationType.equal("USER")
            .and(RelationShip$.destination.equal(username))
            .and(RelationShip$.source.in(
                jpaStreamer.stream(RelationShip.class)
                    .filter(RelationShip$.objectSubType.equal("DSA_DATASET")
                        .and(RelationShip$.source.startsWith(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE, "DSA")))
                        .and(RelationShip$.destination.startsWith(String.format(ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID, "DATASET", datasetId)))
                    )
                    .map(RelationShip$.source)
                    .collect(Collectors.toList()))
            ))
        .collect(Collectors.toList());
  }
}
