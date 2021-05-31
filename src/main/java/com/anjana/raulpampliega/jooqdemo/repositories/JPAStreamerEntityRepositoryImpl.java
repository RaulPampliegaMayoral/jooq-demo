package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.Entity$;
import com.speedment.jpastreamer.application.JPAStreamer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class JPAStreamerEntityRepositoryImpl implements CustomEntityRepository {

  private final JPAStreamer jpaStreamer;

  public JPAStreamerEntityRepositoryImpl(JPAStreamer jpaStreamer) {
    this.jpaStreamer = jpaStreamer;
  }

  @Override
  public List<Entity> getByIds(final List<Integer> ids) {
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
}
