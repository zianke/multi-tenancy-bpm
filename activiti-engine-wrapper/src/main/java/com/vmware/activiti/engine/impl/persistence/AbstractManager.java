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

package com.vmware.activiti.engine.impl.persistence;

import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntityManager;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntityManager;
import org.activiti.engine.delegate.event.ActivitiEventDispatcher;
import org.activiti.engine.impl.asyncexecutor.AsyncExecutor;
import org.activiti.engine.impl.asyncexecutor.JobManager;
import com.vmware.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.history.HistoryManager;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.runtime.Clock;

/**
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public abstract class AbstractManager extends org.activiti.engine.impl.persistence.AbstractManager {

    protected ProcessEngineConfigurationImpl processEngineConfiguration;

    public AbstractManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    protected ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
        return processEngineConfiguration;
    }

    protected TenantEntityManager getTenantEntityManager() {
        return getProcessEngineConfiguration().getTenantEntityManager();
    }

    protected EmploymentEntityManager getEmploymentEntityManager() {
        return getProcessEngineConfiguration().getEmploymentEntityManager();
    }
}
