package com.vmware.activiti.engine.delegate.event.impl;

import com.vmware.activiti.engine.delegate.event.ActivitiEmploymentEvent;
import com.vmware.activiti.engine.delegate.event.ActivitiEventType;

public class ActivitiEventBuilder extends org.activiti.engine.delegate.event.impl.ActivitiEventBuilder {
  public static ActivitiEmploymentEvent createEmploymentEvent(ActivitiEventType type, String tenantId, String userId) {
    ActivitiEmploymentEventImpl newEvent = new ActivitiEmploymentEventImpl(type);
    newEvent.setUserId(userId);
    newEvent.setTenantId(tenantId);
    return newEvent;
  }
}