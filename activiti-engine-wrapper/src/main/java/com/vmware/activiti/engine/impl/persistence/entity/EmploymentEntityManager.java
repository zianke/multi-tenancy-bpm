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

import org.activiti.engine.impl.persistence.entity.EntityManager;

/**
 * @author Joram Barrez
 */
public interface EmploymentEntityManager extends EntityManager<EmploymentEntity> {

  void createEmployment(String userId, String tenantId);

  void deleteEmployment(String userId, String tenantId);
  
  void deleteEmploymentByTenantId(String tenantId);
  
  void deleteEmploymentByUserId(String userId);

}
