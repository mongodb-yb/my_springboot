package com.yubo.springboot.service.impl;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.service.AyUserService;
import com.yubo.springboot.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/2 11:07
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LogManager.getLogger(MailServiceImpl.class);

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private AyUserService ayUserService;
    @Resource
    private Configuration freemarkerConfiguration;

    /*@Value注解用于获取application.properties中的属性值*/
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendMail(AyUser ayUser) throws Exception {
        if (ayUser == null) {
            return false;
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        // 邮件带附件时，使用如下构造
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        messageHelper.setFrom(from);
        messageHelper.setSubject("亲子鉴定验证");
//        messageHelper.setTo("ckbianbian@aliyun.com");
        messageHelper.setTo("ckbianbian@aliyun.com");
        // 邮件附件
        File file = new File("C:\\Users\\BoBa\\Desktop\\亲子鉴定结果.jpg");
        messageHelper.addAttachment("亲子鉴定结果", file);

        Template template = freemarkerConfiguration.getTemplate("send_template.ftl");
        Map<String, Object> modal = new HashMap<>();
        modal.put("date", new Date());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, modal);
        messageHelper.setText(text, true);
        javaMailSender.send(message);
        return true;
    }
}
