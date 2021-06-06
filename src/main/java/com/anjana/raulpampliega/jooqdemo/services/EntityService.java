package com.anjana.raulpampliega.jooqdemo.services;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import java.util.List;

public class EntityService {

  private final CustomRepository repository;

  public EntityService(CustomRepository repository) {
    this.repository = repository;
  }

  public List<Entity> getAllEntitiesFiltered(List<Integer> ids) {
    return repository.getEntityByIds(ids);
  }
}
