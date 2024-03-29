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

import com.vmware.activiti.engine.identity.TenantQuery;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import java.io.Serializable;

/**
 * @author Tom Baeyens
 */
public class CreateTenantQueryCmd implements Command<TenantQuery>, Serializable {

  private static final long serialVersionUID = 1L;

  public TenantQuery execute(CommandContext commandContext) {
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().createNewTenantQuery();
  }

}
