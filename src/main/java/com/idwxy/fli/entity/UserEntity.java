package com.idwxy.fli.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

    // 用户名
    private String userName;
    // 用户密码
    private String password;

    // 构造函数
    public UserEntity(){
        super();
    }

    public UserEntity(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "User [username=" + userName + ",password=" + password + "]";
    }

    // 重写 hashCode 方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    // 重写 equals 方法，便于直接比较 user 对象
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        // 强转
        UserEntity other = (UserEntity) obj;
        if (password == null) {
            if (other.password == null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        }else if (!userName.equals(other.userName))
            return false;
        return true;
    }
}
