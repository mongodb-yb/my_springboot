package com.yubo.springboot.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/4/23 14:30
 */
/*表示是一个持久化的POJO*/
@Entity
/*表示该pojo映射到哪张数据库的表*/
@Table(name = "ay_user")
public class AyUser implements Serializable {
    private static final long serialVersionUID = -4458976674107560730L;
    /*声明该字段为主键*/
    @Id
    private String id;

    private String name;

    private String password;

    public AyUser() {
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
