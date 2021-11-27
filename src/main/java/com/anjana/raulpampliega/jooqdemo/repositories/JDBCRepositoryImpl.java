package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCRepositoryImpl implements CustomRepository {

  @Override
  public List<Entity> getEntityByIds(List<Integer> ids) {
    return null;
  }

  @Override
  public List<AttributeValue> getAllAttributeValues() {
    String url = "jdbc:postgresql://rdbservice:5432/anjana?currentSchema=anjana";
    Properties props = new Properties();
    props.setProperty("user","anjana");
    props.setProperty("password","anjana");
    //props.setProperty("driver","org.postgresql.Driver");
    try {
      Connection conn = DriverManager.getConnection(url, props);

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM attribute_value");
      List<AttributeValue> values = new ArrayList<>();
      while (rs.next())
      {
        AttributeValue attr = new AttributeValue();
        values.add(attr);
      }
      rs.close();
      st.close();
      return values;
    } catch (Exception e) {
      e.printStackTrace();
    }


    return null;
  }

  @Override
  public List<RelationShip> findRelationByInputObject(Integer sourceId, String sourceType) {
    return null;
  }

  @Override
  public List<RelationShip> getDatasetFlags(String username, Integer datasetId) {
    return null;
  }
}
