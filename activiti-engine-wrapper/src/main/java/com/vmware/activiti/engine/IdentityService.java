package com.vmware.activiti.engine;

import com.vmware.activiti.engine.identity.*;

public interface IdentityService extends org.activiti.engine.IdentityService {

  Tenant newTenant(String tenantId);

  TenantQuery createTenantQuery();

  NativeTenantQuery createNativeTenantQuery();

  void saveTenant(Tenant tenant);

  void deleteTenant(String tenantId);

  void createEmployment(String userId, String tenantId);

  void deleteEmployment(String userId, String tenantId);

}
