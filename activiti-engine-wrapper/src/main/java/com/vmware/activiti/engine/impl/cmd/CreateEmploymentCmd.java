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
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import java.io.Serializable;

/**
 * @author Tom Baeyens
 */
public class CreateEmploymentCmd implements Command<Object>, Serializable {

  private static final long serialVersionUID = 1L;

  String userId;
  String tenantId;

  public CreateEmploymentCmd(String userId, String tenantId) {
    this.userId = userId;
    this.tenantId = tenantId;
  }

  public Object execute(CommandContext commandContext) {
    if (userId == null) {
      throw new ActivitiIllegalArgumentException("userId is null");
    }
    if (tenantId == null) {
      throw new ActivitiIllegalArgumentException("tenantId is null");
    }
    ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getEmploymentIdentityManager().createEmployment(userId, tenantId);
    return null;
  }
}
