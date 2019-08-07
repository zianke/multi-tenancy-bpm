package com.vmware.activiti.engine.identity;

import java.io.Serializable;

public interface Tenant extends Serializable {

    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getType();

    void setType(String string);
}
