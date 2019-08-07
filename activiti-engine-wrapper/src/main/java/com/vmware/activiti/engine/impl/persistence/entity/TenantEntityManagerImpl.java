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

import com.vmware.activiti.engine.identity.Tenant;
import com.vmware.activiti.engine.identity.TenantQuery;
import com.vmware.activiti.engine.impl.TenantQueryImpl;
import com.vmware.activiti.engine.impl.persistence.entity.data.TenantDataManager;
// import org.activiti.engine.delegate.event.ActivitiEventType;
import com.vmware.activiti.engine.delegate.event.ActivitiEventType;
import com.vmware.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.Page;
import com.vmware.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import com.vmware.activiti.engine.impl.persistence.entity.AbstractEntityManager;
import org.activiti.engine.impl.persistence.entity.data.DataManager;

import java.util.List;
import java.util.Map;

/**
 * @author Tom Baeyens
 * @author Saeid Mirzaei
 * @author Joram Barrez
 */
public class TenantEntityManagerImpl extends AbstractEntityManager<TenantEntity> implements TenantEntityManager {

  protected TenantDataManager tenantDataManager;

  public TenantEntityManagerImpl(ProcessEngineConfigurationImpl processEngineConfiguration, TenantDataManager tenantDataManager) {
    super(processEngineConfiguration);
    this.tenantDataManager = tenantDataManager;
  }

  @Override
  protected DataManager<TenantEntity> getDataManager() {
    return tenantDataManager;
  }
  
  public Tenant createNewTenant(String tenantId) {
    TenantEntity tenantEntity = tenantDataManager.create();
    tenantEntity.setId(tenantId);
    tenantEntity.setRevision(0); // Needed as tenants can be transient and not save when they are returned 
    return tenantEntity;
  }

  @Override
  public void delete(String tenantId) {
    TenantEntity tenant = tenantDataManager.findById(tenantId); 

    if (tenant != null) {
      
      getEmploymentEntityManager().deleteEmploymentByTenantId(tenantId);
      if (getEventDispatcher().isEnabled()) {
        getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createEmploymentEvent(ActivitiEventType.EMPLOYMENTS_DELETED, tenantId, null));
      }
      
      delete(tenant);
    }
  }

  public TenantQuery createNewTenantQuery() {
    return new TenantQueryImpl(getCommandExecutor());
  }

  public List<Tenant> findTenantByQueryCriteria(TenantQueryImpl query, Page page) {
    return tenantDataManager.findTenantByQueryCriteria(query, page);
  }

  public long findTenantCountByQueryCriteria(TenantQueryImpl query) {
    return tenantDataManager.findTenantCountByQueryCriteria(query);
  }

  public List<Tenant> findTenantsByUser(String userId) {
    return tenantDataManager.findTenantsByUser(userId);
  }

  public List<Tenant> findTenantsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return tenantDataManager.findTenantsByNativeQuery(parameterMap, firstResult, maxResults);
  }

  public long findTenantCountByNativeQuery(Map<String, Object> parameterMap) {
    return tenantDataManager.findTenantCountByNativeQuery(parameterMap);
  }

  @Override
  public boolean isNewTenant(Tenant tenant) {
    return ((TenantEntity) tenant).getRevision() == 0;
  }

  public TenantDataManager getTenantDataManager() {
    return tenantDataManager;
  }

  public void setTenantDataManager(TenantDataManager tenantDataManager) {
    this.tenantDataManager = tenantDataManager;
  }
  
}
