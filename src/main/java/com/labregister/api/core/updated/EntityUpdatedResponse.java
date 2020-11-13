package com.labregister.api.core.updated;

import java.net.URI;

public class EntityUpdatedResponse<T> {

  private final T entity;

  private final URI location;


  public EntityUpdatedResponse(T entity, URI location) {
    this.entity = entity;
    this.location = location;
  }

  public T getEntity() {
    return entity;
  }

  public URI getLocation() {
    return location;
  }
}
