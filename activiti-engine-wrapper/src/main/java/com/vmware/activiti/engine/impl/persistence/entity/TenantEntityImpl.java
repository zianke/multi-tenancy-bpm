package com.vmware.activiti.engine.impl.persistence.entity;

import org.activiti.engine.impl.persistence.entity.AbstractEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TenantEntityImpl extends AbstractEntity implements TenantEntity, Serializable {

  private static final long serialVersionUID = 1L;

  protected String name;
  protected String type;

  public TenantEntityImpl() {
  }

  public Object getPersistentState() {
    Map<String, Object> persistentState = new HashMap<String, Object>();
    persistentState.put("name", name);
    persistentState.put("type", type);
    return persistentState;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
