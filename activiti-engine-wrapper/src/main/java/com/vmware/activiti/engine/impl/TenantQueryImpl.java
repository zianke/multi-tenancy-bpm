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

import com.vmware.activiti.engine.identity.Tenant;
import com.vmware.activiti.engine.identity.TenantQuery;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import java.util.List;

/**
 * @author Joram Barrez
 */
public class TenantQueryImpl extends AbstractQuery<TenantQuery, Tenant> implements TenantQuery {

  private static final long serialVersionUID = 1L;
  protected String id;
  protected String name;
  protected String nameLike;
  protected String type;
  protected String userId;
  protected String procDefId;

  public TenantQueryImpl() {
  }

  public TenantQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public TenantQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  public TenantQuery tenantId(String id) {
    if (id == null) {
      throw new ActivitiIllegalArgumentException("Provided id is null");
    }
    this.id = id;
    return this;
  }

  public TenantQuery tenantName(String name) {
    if (name == null) {
      throw new ActivitiIllegalArgumentException("Provided name is null");
    }
    this.name = name;
    return this;
  }

  public TenantQuery tenantNameLike(String nameLike) {
    if (nameLike == null) {
      throw new ActivitiIllegalArgumentException("Provided nameLike is null");
    }
    this.nameLike = nameLike;
    return this;
  }

  public TenantQuery tenantType(String type) {
    if (type == null) {
      throw new ActivitiIllegalArgumentException("Provided type is null");
    }
    this.type = type;
    return this;
  }

  public TenantQuery tenantEmployee(String userId) {
    if (userId == null) {
      throw new ActivitiIllegalArgumentException("Provided userId is null");
    }
    this.userId = userId;
    return this;
  }

  public TenantQuery potentialStarter(String procDefId) {
    if (procDefId == null) {
      throw new ActivitiIllegalArgumentException("Provided processDefinitionId is null or empty");
    }
    this.procDefId = procDefId;
    return this;

  }

  // sorting ////////////////////////////////////////////////////////

  public TenantQuery orderByTenantId() {
    return orderBy(TenantQueryProperty.TENANT_ID);
  }

  public TenantQuery orderByTenantName() {
    return orderBy(TenantQueryProperty.NAME);
  }

  public TenantQuery orderByTenantType() {
    return orderBy(TenantQueryProperty.TYPE);
  }

  // results ////////////////////////////////////////////////////////

  public long executeCount(CommandContext commandContext) {
    checkQueryOk();
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().findTenantCountByQueryCriteria(this);
  }

  public List<Tenant> executeList(CommandContext commandContext, Page page) {
    checkQueryOk();
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().findTenantByQueryCriteria(this, page);
  }

  // getters ////////////////////////////////////////////////////////

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNameLike() {
    return nameLike;
  }

  public String getType() {
    return type;
  }

  public String getUserId() {
    return userId;
  }


  public String getProcDefId() {
    return procDefId;
  }
  
}
