package com.lanou.module.domain;

/**
 * Created by dllo on 17/11/16.
 */
public class Module {
    private int module_id;
    private String name;

    public Module() {
    }

    public int getModule_id() {
        return module_id;
    }

    public Module(int module_id, String name) {
        this.module_id = module_id;
        this.name = name;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Module{" +
                "module_id=" + module_id +
                ", name='" + name + '\'' +
                '}';
    }
}
