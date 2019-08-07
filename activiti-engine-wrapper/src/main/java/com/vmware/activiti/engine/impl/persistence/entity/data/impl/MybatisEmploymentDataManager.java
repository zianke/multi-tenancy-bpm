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
package com.vmware.activiti.engine.impl.persistence.entity.data.impl;

import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntity;
import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntityImpl;
import com.vmware.activiti.engine.impl.persistence.entity.data.EmploymentDataManager;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.data.AbstractDataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class MybatisEmploymentDataManager extends AbstractDataManager<EmploymentEntity> implements EmploymentDataManager {

  public MybatisEmploymentDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
    super(processEngineConfiguration);
  }

  @Override
  public Class<? extends EmploymentEntity> getManagedEntityClass() {
    return EmploymentEntityImpl.class;
  }
  
  @Override
  public EmploymentEntity create() {
    return new EmploymentEntityImpl();
  }
  
  @Override
  public void deleteEmployment(String userId, String tenantId) {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("userId", userId);
    parameters.put("tenantId", tenantId);
    getDbSqlSession().delete("deleteEmployment", parameters, EmploymentEntityImpl.class);
  }
  
  @Override
  public void deleteEmploymentByTenantId(String tenantId) {
    getDbSqlSession().delete("deleteEmploymentsByTenantId", tenantId, EmploymentEntityImpl.class);
  }
  
  @Override
  public void deleteEmploymentByUserId(String userId) {
    getDbSqlSession().delete("deleteEmploymentsByUserId", userId, EmploymentEntityImpl.class);
  }
  
}
