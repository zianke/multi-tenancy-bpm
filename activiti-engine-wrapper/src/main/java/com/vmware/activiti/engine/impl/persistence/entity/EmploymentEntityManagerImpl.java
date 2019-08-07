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

package com.vmware.activiti.engine.impl.persistence.entity;

import com.vmware.activiti.engine.impl.persistence.entity.data.EmploymentDataManager;
// import org.activiti.engine.delegate.event.ActivitiEventType;
import com.vmware.activiti.engine.delegate.event.ActivitiEventType;
import com.vmware.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.AbstractEntityManager;
import org.activiti.engine.impl.persistence.entity.data.DataManager;

/**
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public class EmploymentEntityManagerImpl extends AbstractEntityManager<EmploymentEntity> implements EmploymentEntityManager {

  protected EmploymentDataManager employmentDataManager;

  public EmploymentEntityManagerImpl(ProcessEngineConfigurationImpl processEngineConfiguration, EmploymentDataManager employmentDataManager) {
    super(processEngineConfiguration);
    this.employmentDataManager = employmentDataManager;
  }
 
  @Override
  protected DataManager<EmploymentEntity> getDataManager() {
    return employmentDataManager;
  }
 
  public void createEmployment(String userId, String tenantId) {
    EmploymentEntity employmentEntity = create();
    employmentEntity.setUserId(userId);
    employmentEntity.setTenantId(tenantId);
    insert(employmentEntity, false);

    if (getEventDispatcher().isEnabled()) {
      getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEmploymentEvent(ActivitiEventType.EMPLOYMENT_CREATED, tenantId, userId));
    }
  }

  public void deleteEmployment(String userId, String tenantId) {
    employmentDataManager.deleteEmployment(userId, tenantId);  
    if (getEventDispatcher().isEnabled()) {
      getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEmploymentEvent(ActivitiEventType.EMPLOYMENT_DELETED, tenantId, userId));
    }
  }
  
  @Override
  public void deleteEmploymentByTenantId(String tenantId) {
    employmentDataManager.deleteEmploymentByTenantId(tenantId);
  }
  
  @Override
  public void deleteEmploymentByUserId(String userId) {
    employmentDataManager.deleteEmploymentByUserId(userId);
  }

  public EmploymentDataManager getEmploymentDataManager() {
    return employmentDataManager;
  }

  public void setEmploymentDataManager(EmploymentDataManager employmentDataManager) {
    this.employmentDataManager = employmentDataManager;
  }
  
}
