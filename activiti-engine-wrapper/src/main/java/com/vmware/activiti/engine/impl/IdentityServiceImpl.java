/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vmware.activiti.engine.impl;

import com.vmware.activiti.engine.IdentityService;
import com.vmware.activiti.engine.identity.*;
import com.vmware.activiti.engine.impl.cmd.*;

/**
 * @author Tom Baeyens
 */
public class IdentityServiceImpl extends org.activiti.engine.impl.IdentityServiceImpl implements IdentityService {

  public Tenant newTenant(String tenantId) {
    return commandExecutor.execute(new CreateTenantCmd(tenantId));
  }

  public void saveTenant(Tenant tenant) {
    commandExecutor.execute(new SaveTenantCmd(tenant));
  }

  public TenantQuery createTenantQuery() {
    return commandExecutor.execute(new CreateTenantQueryCmd());
  }

  @Override
  public NativeTenantQuery createNativeTenantQuery() {
    return new NativeTenantQueryImpl(commandExecutor);
  }

  public void createEmployment(String userId, String tenantId) {
    commandExecutor.execute(new CreateEmploymentCmd(userId, tenantId));
  }

  public void deleteTenant(String tenantId) {
    commandExecutor.execute(new DeleteTenantCmd(tenantId));
  }

  public void deleteEmployment(String userId, String tenantId) {
    commandExecutor.execute(new DeleteEmploymentCmd(userId, tenantId));
  }
}
