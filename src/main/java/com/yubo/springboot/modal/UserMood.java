package com.yubo.springboot.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yubo
 * @version V1.0
 * @description 用户发表的说说实体
 * @date 2020/7/3 11:06
 */
@Entity
@Table(name = "user_mood")
public class UserMood implements Serializable {
    private static final long serialVersionUID = -2570874685790080196L;
    @Id
    private String id;
    private String content;
    private String userId;
    private Integer pariseNum;
    private LocalDateTime publishTime;

    public UserMood() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPariseNum() {
        return pariseNum;
    }

    public void setPariseNum(Integer pariseNum) {
        this.pariseNum = pariseNum;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserMood{");
        sb.append("id='").append(id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", pariseNum=").append(pariseNum);
        sb.append(", publishTime=").append(publishTime);
        sb.append('}');
        return sb.toString();
    }
}
