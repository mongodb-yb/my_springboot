package com.yubo.springboot.modal;

import java.io.Serializable;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/2 16:11
 */
public class User implements Serializable {
    private static final long serialVersionUID = 261660138545961971L;
    private String id;

    private String name;

    private String password;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AyUser{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
