package com.anjana.raulpampliega.jooqdemo.model;

import com.blazebit.persistence.CTE;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@CTE
public class EntityCte {

  @Id
  private Integer id;
  private Integer ancestor;
}
