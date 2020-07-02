package com.yubo.springboot.service;

import com.yubo.springboot.modal.AyUser;

/**
 * @author yubo
 * @version V1.0
 * @description 邮件服务service
 * @date 2020/7/2 11:06
 */
public interface MailService {

    boolean sendMail(AyUser ayUser) throws Exception;
}
