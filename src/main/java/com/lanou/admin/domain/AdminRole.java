package com.lanou.admin.domain;

/**
 * Created by dllo on 17/11/17.
 */
public class AdminRole {
    private int admin_id;
    private int role_id;

    public AdminRole(int admin_id, int role_id) {
        this.admin_id = admin_id;
        this.role_id = role_id;
    }

    public AdminRole() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getAdmin_id() {

        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
