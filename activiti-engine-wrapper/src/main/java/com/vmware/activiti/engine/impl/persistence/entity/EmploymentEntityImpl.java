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

import org.activiti.engine.impl.persistence.entity.AbstractEntityNoRevision;

import java.io.Serializable;

public class EmploymentEntityImpl extends AbstractEntityNoRevision implements EmploymentEntity, Serializable {

  private static final long serialVersionUID = 1L;

  protected String userId;
  protected String tenantId;

  public EmploymentEntityImpl() {
    
  }

  public Object getPersistentState() {
    return EmploymentEntityImpl.class;
  }
  
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

}
