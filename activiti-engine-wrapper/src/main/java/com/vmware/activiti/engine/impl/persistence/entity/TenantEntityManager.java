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
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.EntityManager;

import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public interface TenantEntityManager extends EntityManager<TenantEntity> {

  Tenant createNewTenant(String tenantId);

  TenantQuery createNewTenantQuery();

  List<Tenant> findTenantByQueryCriteria(TenantQueryImpl query, Page page);

  long findTenantCountByQueryCriteria(TenantQueryImpl query);

  List<Tenant> findTenantsByUser(String userId);

  List<Tenant> findTenantsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults);

  long findTenantCountByNativeQuery(Map<String, Object> parameterMap);

  boolean isNewTenant(Tenant tenant);

}
