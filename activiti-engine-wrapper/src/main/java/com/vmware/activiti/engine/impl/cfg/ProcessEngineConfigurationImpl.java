package com.vmware.activiti.engine.impl.cfg;


import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntityManager;
import com.vmware.activiti.engine.impl.persistence.entity.EmploymentEntityManagerImpl;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntityManager;
import com.vmware.activiti.engine.impl.persistence.entity.TenantEntityManagerImpl;
import com.vmware.activiti.engine.impl.persistence.entity.data.EmploymentDataManager;
import com.vmware.activiti.engine.impl.persistence.entity.data.TenantDataManager;
import com.vmware.activiti.engine.impl.persistence.entity.data.impl.MybatisEmploymentDataManager;
import com.vmware.activiti.engine.impl.persistence.entity.data.impl.MybatisTenantDataManager;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.impl.persistence.entity.data.impl.*;

public abstract class ProcessEngineConfigurationImpl extends org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl {
    protected TenantDataManager tenantDataManager;
    protected EmploymentDataManager employmentDataManager;

    protected TenantEntityManager tenantEntityManager;
    protected EmploymentEntityManager employmentEntityManager;

    public void initDataManagers() {
        if (attachmentDataManager == null) {
            attachmentDataManager = new MybatisAttachmentDataManager(this);
        }
        if (byteArrayDataManager == null) {
            byteArrayDataManager = new MybatisByteArrayDataManager(this);
        }
        if (commentDataManager == null) {
            commentDataManager = new MybatisCommentDataManager(this);
        }
        if (deploymentDataManager == null) {
            deploymentDataManager = new MybatisDeploymentDataManager(this);
        }
        if (eventLogEntryDataManager == null) {
            eventLogEntryDataManager = new MybatisEventLogEntryDataManager(this);
        }
        if (eventSubscriptionDataManager == null) {
            eventSubscriptionDataManager = new MybatisEventSubscriptionDataManager(this);
        }
        if (executionDataManager == null) {
            executionDataManager = new MybatisExecutionDataManager(this);
        }
        if (groupDataManager == null) {
            groupDataManager = new MybatisGroupDataManager(this);
        }
        if (tenantDataManager == null) {
            tenantDataManager = new MybatisTenantDataManager(this);
        }
        if (historicActivityInstanceDataManager == null) {
            historicActivityInstanceDataManager = new MybatisHistoricActivityInstanceDataManager(this);
        }
        if (historicDetailDataManager == null) {
            historicDetailDataManager = new MybatisHistoricDetailDataManager(this);
        }
        if (historicIdentityLinkDataManager == null) {
            historicIdentityLinkDataManager = new MybatisHistoricIdentityLinkDataManager(this);
        }
        if (historicProcessInstanceDataManager == null) {
            historicProcessInstanceDataManager = new MybatisHistoricProcessInstanceDataManager(this);
        }
        if (historicTaskInstanceDataManager == null) {
            historicTaskInstanceDataManager = new MybatisHistoricTaskInstanceDataManager(this);
        }
        if (historicVariableInstanceDataManager == null) {
            historicVariableInstanceDataManager = new MybatisHistoricVariableInstanceDataManager(this);
        }
        if (identityInfoDataManager == null) {
            identityInfoDataManager = new MybatisIdentityInfoDataManager(this);
        }
        if (identityLinkDataManager == null) {
            identityLinkDataManager = new MybatisIdentityLinkDataManager(this);
        }
        if (jobDataManager == null) {
            jobDataManager = new MybatisJobDataManager(this);
        }
        if (timerJobDataManager == null) {
            timerJobDataManager = new MybatisTimerJobDataManager(this);
        }
        if (suspendedJobDataManager == null) {
            suspendedJobDataManager = new MybatisSuspendedJobDataManager(this);
        }
        if (deadLetterJobDataManager == null) {
            deadLetterJobDataManager = new MybatisDeadLetterJobDataManager(this);
        }
        if (membershipDataManager == null) {
            membershipDataManager = new MybatisMembershipDataManager(this);
        }
        if (employmentDataManager == null) {
            employmentDataManager = new MybatisEmploymentDataManager(this);
        }
        if (modelDataManager == null) {
            modelDataManager = new MybatisModelDataManager(this);
        }
        if (processDefinitionDataManager == null) {
            processDefinitionDataManager = new MybatisProcessDefinitionDataManager(this);
        }
        if (processDefinitionInfoDataManager == null) {
            processDefinitionInfoDataManager = new MybatisProcessDefinitionInfoDataManager(this);
        }
        if (propertyDataManager == null) {
            propertyDataManager = new MybatisPropertyDataManager(this);
        }
        if (resourceDataManager == null) {
            resourceDataManager = new MybatisResourceDataManager(this);
        }
        if (taskDataManager == null) {
            taskDataManager = new MybatisTaskDataManager(this);
        }
        if (userDataManager == null) {
            userDataManager = new MybatisUserDataManager(this);
        }
        if (variableInstanceDataManager == null) {
            variableInstanceDataManager = new MybatisVariableInstanceDataManager(this);
        }
    }

    public void initEntityManagers() {
        if (attachmentEntityManager == null) {
            attachmentEntityManager = new AttachmentEntityManagerImpl(this, attachmentDataManager);
        }
        if (byteArrayEntityManager == null) {
            byteArrayEntityManager = new ByteArrayEntityManagerImpl(this, byteArrayDataManager);
        }
        if (commentEntityManager == null) {
            commentEntityManager = new CommentEntityManagerImpl(this, commentDataManager);
        }
        if (deploymentEntityManager == null) {
            deploymentEntityManager = new DeploymentEntityManagerImpl(this, deploymentDataManager);
        }
        if (eventLogEntryEntityManager == null) {
            eventLogEntryEntityManager = new EventLogEntryEntityManagerImpl(this, eventLogEntryDataManager);
        }
        if (eventSubscriptionEntityManager == null) {
            eventSubscriptionEntityManager = new EventSubscriptionEntityManagerImpl(this, eventSubscriptionDataManager);
        }
        if (executionEntityManager == null) {
            executionEntityManager = new ExecutionEntityManagerImpl(this, executionDataManager);
        }
        if (groupEntityManager == null) {
            groupEntityManager = new GroupEntityManagerImpl(this, groupDataManager);
        }
        if (tenantEntityManager == null) {
            tenantEntityManager = new TenantEntityManagerImpl(this, tenantDataManager);
        }
        if (historicActivityInstanceEntityManager == null) {
            historicActivityInstanceEntityManager = new HistoricActivityInstanceEntityManagerImpl(this, historicActivityInstanceDataManager);
        }
        if (historicDetailEntityManager == null) {
            historicDetailEntityManager = new HistoricDetailEntityManagerImpl(this, historicDetailDataManager);
        }
        if (historicIdentityLinkEntityManager == null) {
            historicIdentityLinkEntityManager = new HistoricIdentityLinkEntityManagerImpl(this, historicIdentityLinkDataManager);
        }
        if (historicProcessInstanceEntityManager == null) {
            historicProcessInstanceEntityManager = new HistoricProcessInstanceEntityManagerImpl(this, historicProcessInstanceDataManager);
        }
        if (historicTaskInstanceEntityManager == null) {
            historicTaskInstanceEntityManager = new HistoricTaskInstanceEntityManagerImpl(this, historicTaskInstanceDataManager);
        }
        if (historicVariableInstanceEntityManager == null) {
            historicVariableInstanceEntityManager = new HistoricVariableInstanceEntityManagerImpl(this, historicVariableInstanceDataManager);
        }
        if (identityInfoEntityManager == null) {
            identityInfoEntityManager = new IdentityInfoEntityManagerImpl(this, identityInfoDataManager);
        }
        if (identityLinkEntityManager == null) {
            identityLinkEntityManager = new IdentityLinkEntityManagerImpl(this, identityLinkDataManager);
        }
        if (jobEntityManager == null) {
            jobEntityManager = new JobEntityManagerImpl(this, jobDataManager);
        }
        if (timerJobEntityManager == null) {
            timerJobEntityManager = new TimerJobEntityManagerImpl(this, timerJobDataManager);
        }
        if (suspendedJobEntityManager == null) {
            suspendedJobEntityManager = new SuspendedJobEntityManagerImpl(this, suspendedJobDataManager);
        }
        if (deadLetterJobEntityManager == null) {
            deadLetterJobEntityManager = new DeadLetterJobEntityManagerImpl(this, deadLetterJobDataManager);
        }
        if (membershipEntityManager == null) {
            membershipEntityManager = new MembershipEntityManagerImpl(this, membershipDataManager);
        }
        if (employmentEntityManager == null) {
            employmentEntityManager = new EmploymentEntityManagerImpl(this, employmentDataManager);
        }
        if (modelEntityManager == null) {
            modelEntityManager = new ModelEntityManagerImpl(this, modelDataManager);
        }
        if (processDefinitionEntityManager == null) {
            processDefinitionEntityManager = new ProcessDefinitionEntityManagerImpl(this, processDefinitionDataManager);
        }
        if (processDefinitionInfoEntityManager == null) {
            processDefinitionInfoEntityManager = new ProcessDefinitionInfoEntityManagerImpl(this, processDefinitionInfoDataManager);
        }
        if (propertyEntityManager == null) {
            propertyEntityManager = new PropertyEntityManagerImpl(this, propertyDataManager);
        }
        if (resourceEntityManager == null) {
            resourceEntityManager = new ResourceEntityManagerImpl(this, resourceDataManager);
        }
        if (tableDataManager == null) {
            tableDataManager = new TableDataManagerImpl(this);
        }
        if (taskEntityManager == null) {
            taskEntityManager = new TaskEntityManagerImpl(this, taskDataManager);
        }
        if (userEntityManager == null) {
            userEntityManager = new UserEntityManagerImpl(this, userDataManager);
        }
        if (variableInstanceEntityManager == null) {
            variableInstanceEntityManager = new VariableInstanceEntityManagerImpl(this, variableInstanceDataManager);
        }
    }

    public TenantDataManager getTenantDataManager() {
        return tenantDataManager;
    }

    public ProcessEngineConfigurationImpl setTenantDataManager(TenantDataManager tenantDataManager) {
        this.tenantDataManager = tenantDataManager;
        return this;
    }

    public EmploymentDataManager getEmploymentDataManager() {
        return employmentDataManager;
    }

    public ProcessEngineConfigurationImpl setEmploymentDataManager(EmploymentDataManager employmentDataManager) {
        this.employmentDataManager = employmentDataManager;
        return this;
    }

    public TenantEntityManager getTenantEntityManager() {
        return tenantEntityManager;
    }

    public ProcessEngineConfigurationImpl setTenantEntityManager(TenantEntityManager tenantEntityManager) {
        this.tenantEntityManager = tenantEntityManager;
        return this;
    }

    public EmploymentEntityManager getEmploymentEntityManager() {
        return employmentEntityManager;
    }

    public ProcessEngineConfigurationImpl setEmploymentEntityManager(EmploymentEntityManager employmentEntityManager) {
        this.employmentEntityManager = employmentEntityManager;
        return this;
    }
}