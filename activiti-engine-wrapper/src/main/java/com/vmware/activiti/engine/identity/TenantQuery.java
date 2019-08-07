package com.vmware.activiti.engine.identity;

import org.activiti.engine.query.Query;

public interface TenantQuery extends Query<TenantQuery, Tenant> {

  TenantQuery tenantId(String tenantId);

  TenantQuery tenantName(String tenantName);
  
  TenantQuery tenantNameLike(String tenantNameLike);

  TenantQuery tenantType(String tenantType);

  TenantQuery tenantEmployee(String tenantEmployeeUserId);
  
  TenantQuery potentialStarter(String procDefId);

  TenantQuery orderByTenantId();
  
  TenantQuery orderByTenantName();
  
  TenantQuery orderByTenantType();

}
