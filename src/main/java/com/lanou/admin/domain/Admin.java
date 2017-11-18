package com.lanou.admin.domain;

import com.lanou.role.domain.Role;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public class Admin {
    private int admin_id;
    @Length(min = 1,max = 30,message = "30长度的字母、数字和下划线")
    private String admin_code;
    @Length(min = 1,max = 30,message = "30长度的字母、数字和下划线")
    private String password;
    @Length(min = 1,max = 30,message = "30长度的字母、数字和下划线")
    private String name;

    private String telephone;
    private String email;
    private Timestamp enrolldate;
    @Length(min = 1,max = 30,message = "30长度的字母、数字和下划线")
    private String repassword;
    private String verifyCode;


    /**
     * 管理员拥有的角色  一对多
     */
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Admin() {
    }

    public Admin(int admin_id, String admin_code, String password, String name, String telephone, String email, Timestamp enrolldate) {

        this.admin_id = admin_id;
        this.admin_code = admin_code;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.enrolldate = enrolldate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_code='" + admin_code + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", enrolldate=" + enrolldate +
                '}';
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_code() {
        return admin_code;
    }

    public void setAdmin_code(String admin_code) {
        this.admin_code = admin_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(Timestamp enrolldate) {
        this.enrolldate = enrolldate;
    }
}
