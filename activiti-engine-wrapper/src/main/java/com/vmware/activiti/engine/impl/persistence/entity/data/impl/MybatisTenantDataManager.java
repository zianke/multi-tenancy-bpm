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

import com.vmware.activiti.engine.identity.Tenant;
import com.vmware.activiti.engine.impl.TenantQueryImpl;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntity;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntityImpl;
import com.vmware.activiti.engine.impl.persistence.entity.data.TenantDataManager;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.data.AbstractDataManager;

import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class MybatisTenantDataManager extends AbstractDataManager<TenantEntity> implements TenantDataManager {

  public MybatisTenantDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
    super(processEngineConfiguration);
  }

  @Override
  public Class<? extends TenantEntity> getManagedEntityClass() {
    return TenantEntityImpl.class;
  }
  
  @Override
  public TenantEntity create() {
    return new TenantEntityImpl();
  }
  
  @SuppressWarnings("unchecked")
  public List<Tenant> findTenantByQueryCriteria(TenantQueryImpl query, Page page) {
    return getDbSqlSession().selectList("selectTenantByQueryCriteria", query, page);
  }

  public long findTenantCountByQueryCriteria(TenantQueryImpl query) {
    return (Long) getDbSqlSession().selectOne("selectTenantCountByQueryCriteria", query);
  }

  @SuppressWarnings("unchecked")
  public List<Tenant> findTenantsByUser(String userId) {
    return getDbSqlSession().selectList("selectTenantsByUserId", userId);
  }

  @SuppressWarnings("unchecked")
  public List<Tenant> findTenantsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return getDbSqlSession().selectListWithRawParameter("selectTenantByNativeQuery", parameterMap, firstResult, maxResults);
  }

  public long findTenantCountByNativeQuery(Map<String, Object> parameterMap) {
    return (Long) getDbSqlSession().selectOne("selectTenantCountByNativeQuery", parameterMap);
  }
  
}
