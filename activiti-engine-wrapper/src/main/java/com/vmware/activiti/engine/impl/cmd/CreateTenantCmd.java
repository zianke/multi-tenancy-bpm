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

package com.vmware.activiti.engine.impl.cmd;

import org.activiti.engine.ActivitiIllegalArgumentException;
import com.vmware.activiti.engine.identity.Tenant;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import java.io.Serializable;

/**
 * @author Tom Baeyens
 */
public class CreateTenantCmd implements Command<Tenant>, Serializable {

  private static final long serialVersionUID = 1L;

  protected String tenantId;

  public CreateTenantCmd(String tenantId) {
    if (tenantId == null) {
      throw new ActivitiIllegalArgumentException("tenantId is null");
    }
    this.tenantId = tenantId;
  }

  public Tenant execute(CommandContext commandContext) {
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().createNewTenant(tenantId);
  }

}
