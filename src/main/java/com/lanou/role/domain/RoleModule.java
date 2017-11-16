package com.lanou.role.domain;

/**
 * Created by dllo on 17/11/16.
 */
public class RoleModule {
    private int role_id;
    private int module_id;

    public RoleModule(int role_id, int module_id) {
        this.role_id = role_id;
        this.module_id = module_id;
    }

    public RoleModule() {
    }

    public int getRole_id() {

        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }
}
