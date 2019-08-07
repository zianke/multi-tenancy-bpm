package com.vmware.activiti.engine.impl.interceptor;

import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntityManager;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntityManager;
import com.vmware.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Command;

public class CommandContext extends org.activiti.engine.impl.interceptor.CommandContext {

    protected ProcessEngineConfigurationImpl processEngineConfiguration;

    public CommandContext(Command<?> command, ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(command, processEngineConfiguration);
    }

    public TenantEntityManager getTenantEntityManager() {
        return processEngineConfiguration.getTenantEntityManager();
    }

    public EmploymentEntityManager getEmploymentIdentityManager() {
        return processEngineConfiguration.getEmploymentEntityManager();
    }

}
