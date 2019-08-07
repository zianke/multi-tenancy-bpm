package com.vmware.activiti.engine.impl.persistence.entity;

import com.vmware.activiti.engine.identity.Tenant;
import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.persistence.entity.Entity;

public interface TenantEntity extends Tenant, Entity, HasRevision {

  String getName();

  void setName(String name);
 
  String getType();

  void setType(String type);

}
