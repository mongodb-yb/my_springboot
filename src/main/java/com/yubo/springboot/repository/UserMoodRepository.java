package com.yubo.springboot.repository;

import com.yubo.springboot.modal.UserMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/3 11:10
 */
public interface UserMoodRepository extends JpaRepository<UserMood, String> {


}
