package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.core.utils.ARISerializer;
import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository {

  String ARI_PARTIAL_ENTITY_OBJECTSUBTYPE_ID =
      ARISerializer.ARI_ENTITY_PREFIX + "%s" + ARISerializer.SEPARATOR + "%s"
          + ARISerializer.SEPARATOR + "%%";

  String ARI_PARTIAL_ENTITY_OBJECTSUBTYPE =
      ARISerializer.ARI_ENTITY_PREFIX + "%s" + ARISerializer.SEPARATOR + "%%";

  String ARI_PARTIAL_ENTITY_ANY_OBJECTSUBTYPE_ID =
      ARISerializer.ARI_ENTITY_PREFIX + "%%" + ARISerializer.SEPARATOR + "%s"
          + ARISerializer.SEPARATOR + "%%";

  List<Entity> getEntityByIds(List<Integer> ids);
  List<AttributeValue> getAllAttributeValues();
  List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType);
  List<RelationShip> getDatasetFlags(String username, Integer datasetId);
}
