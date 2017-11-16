package com.lanou.role.domain;

import com.lanou.module.domain.Module;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public class Role {
    private int role_id;
    private String name;

    /**
     * 该角色下对应的权限集合  一对多
     */
    private List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Role(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", name='" + name + '\'' +
                '}';
    }

    public Role() {

    }

    public Role(int role_id, String name, List<Module> modules) {
        this.role_id = role_id;
        this.name = name;
        this.modules = modules;
    }

    public int getRole_id() {

        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
