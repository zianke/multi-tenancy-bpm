package com.vmware.activiti.engine.impl;

import com.vmware.activiti.engine.identity.Tenant;
import com.vmware.activiti.engine.identity.NativeTenantQuery;
import org.activiti.engine.impl.AbstractNativeQuery;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import java.util.List;
import java.util.Map;

public class NativeTenantQueryImpl extends AbstractNativeQuery<NativeTenantQuery, Tenant> implements NativeTenantQuery {

  private static final long serialVersionUID = 1L;

  public NativeTenantQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public NativeTenantQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  // results ////////////////////////////////////////////////////////////////

  public List<Tenant> executeList(CommandContext commandContext, Map<String, Object> parameterMap, int firstResult, int maxResults) {
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().findTenantsByNativeQuery(parameterMap, firstResult, maxResults);
  }

  public long executeCount(CommandContext commandContext, Map<String, Object> parameterMap) {
    return ((com.vmware.activiti.engine.impl.interceptor.CommandContext) commandContext).getTenantEntityManager().findTenantCountByNativeQuery(parameterMap);
  }

}