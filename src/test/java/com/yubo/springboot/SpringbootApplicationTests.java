package com.yubo.springboot;

import com.google.gson.Gson;
import com.yubo.springboot.dao.RedisDao;
import com.yubo.springboot.message.producer.UserMoodProducer;
import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.modal.UserMood;
import com.yubo.springboot.service.AyUserService;
import com.yubo.springboot.service.MybatisAyUserService;
import com.yubo.springboot.service.UserMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

/*SpringRunner继承了SpringJunit4ClassRunner
 * 表明了使用的是SpringJunit4ClassRunner的执行器
 * 如果只是简单扼单元测试，可以去掉该注解
 * */
//@RunWith(SpringRunner.class)
/*boot项目所有的配置都会通过入口类去加载，而该注解可以引入入口类的配置*/
/*启动@Test的方法时，控制台打印的内容额项目启动时控制台打印的内容一样，则该注解引入了入口类的配置*/
@SpringBootTest
class SpringbootApplicationTests {
    /*通过jdbc连接数据库的工具，可以进行增删改查等操作*/
    /*@Resource
     * 自动注入：项目启动后，springboot会帮助我们创建一个JdbcTemplate实例
     * */
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private AyUserService ayUserService;

    @Test
    void contextLoads() {
        System.out.println("哈哈哈");
    }

    @Test
    public void testMysql() {
        /*sql语句要么全部大写，要么全部小写，不要混用*/
        String sql = "select * from ay_user";
        /*提供了增删改查的方法*/
        /*传递一个sql和RowMapper参数*/
        /*RowMapper：可以将查出来的每一行都封装成对应的java对象--mapRow方法*/
        List<AyUser> userList = jdbcTemplate.query(sql, (resultSet, i) -> {
            AyUser ayUser = new AyUser();
            ayUser.setId(resultSet.getString("id"));
            ayUser.setName(resultSet.getString("name"));
            ayUser.setPassword(resultSet.getString("password"));
            return ayUser;
        });
        userList.stream().forEach(ayUser -> {
            System.out.println(ayUser);
        });
    }

    @Test
    public void testJpaRepository() {
        List<AyUser> ayUserList = ayUserService.findAll();
        System.out.println(ayUserList);

        System.out.println(ayUserService.findById("1"));

        System.out.println(ayUserService.findByName("张三"));

        System.out.println(ayUserService.findByNameLike("%四%"));

        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        System.out.println(ayUserService.findByIdIn(ids));


        PageRequest pageRequest = PageRequest.of(0, 2);
        Page page = ayUserService.findAll(pageRequest);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());


    }

    /**
     * 测试出现异常：插入user的事务回滚。
     */
    @Test
    public void testTransactional() {
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("赵六");
        ayUser.setPassword("123456");
        System.out.println(ayUserService.save(ayUser));
    }

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 测试spring-boot集成redis：RedisTemplate
     */
    @Test
    public void testRedisTemplate() {
        // 添加键值对
//        redisTemplate.opsForValue().set("name", "value0");
        // 更新键值对
//        redisTemplate.opsForValue().set("name", "value1");
        // 查询键值对
//        String value = (String) redisTemplate.opsForValue().get("name");
//        System.out.println(value);
        // 删除键值对
        redisTemplate.delete("name");
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisDao redisDao;

    @Test
    public void testStringRedisTemplate() {
//        AyUser ayUser = new AyUser();
//        ayUser.setId("4");
//        ayUser.setName("赵六");
//        ayUser.setPassword("123456");
//        redisDao.add("user", ayUser);
//        System.out.println(redisDao.select("user"));
//        ayUser.setPassword("654321");
//        redisDao.update("user", ayUser);
//        System.out.println(redisDao.select("user"));
        redisTemplate.opsForList().range("all_user_list", 0, -1).stream().forEach(item -> System.out.println(new Gson().toJson(item)));
    }

    /**
     * 测试日志信息输出到文件中
     */
    @Test
    public void testLog4j2() {
        AyUser ayUser = new AyUser();
        ayUser.setId("1");
        ayUser.setName("张三");
        ayUser.setPassword("123456");
        ayUserService.delete(ayUser);
    }

    @Resource
    private MybatisAyUserService mybatisAyUserService;

    /**
     * mybatis測試
     */
    @Test
    public void getUserTest() {
        System.out.println(mybatisAyUserService.getUserById("1"));
    }

    @Resource
    private UserMoodService userMoodService;

    /**
     * 发说说测试
     */
    @Test
    public void userMoodTest() {
        UserMood userMood = new UserMood();
        userMood.setId(StringUtils.replace((UUID.randomUUID().toString()), "-", ""));
        userMood.setContent("我终于有微信了");
        userMood.setUserId("1");
        userMood.setPariseNum(0);
        userMood.setPublishTime(LocalDateTime.now());
        System.out.println(new Gson().toJson(userMoodService.save(userMood)));
    }

    @Resource
    private UserMoodProducer userMoodProducer;

    /**
     * activemq测试
     */
    @Test
    public void activeMqTest() {
        // 创建一个消息队列
        Destination destination = new ActiveMQQueue("user_mood_queue");
        userMoodProducer.sendMessage(destination, "呵呵");
    }

    /**
     * 将微信发说说添加到消息队列测试
     */
    @Test
    public void weixinshuoshuoTest() {
        UserMood userMood = new UserMood();
        userMood.setId(StringUtils.replace((UUID.randomUUID().toString()), "-", ""));
        userMood.setContent("哎，消息队列啊");
        userMood.setUserId("1");
        userMood.setPariseNum(0);
        userMood.setPublishTime(LocalDateTime.now());
        userMoodService.asynSave(userMood);
    }

    /**
     * 同步调用测试
     */
    @Test
    public void commonTest() {
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户");
        ayUserService.findAll();
        System.out.println("第二次查询所有用户");
        ayUserService.findAll();
        System.out.println("第三次查询所有用户");
        ayUserService.findAll();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) + "毫秒");
    }

    /**
     * 异步调用测试(启动类中开启@EnableAsync注解)
     * 速度明显比上面的同步调用快了
     */
    @Test
    public void asyncTest() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户");
        Future<List<AyUser>> list1 = ayUserService.findAsyncUserAll();
        System.out.println("第二次查询所有用户");
        Future<List<AyUser>> list2 = ayUserService.findAsyncUserAll();
        System.out.println("第三次查询所有用户");
        Future<List<AyUser>> list3 = ayUserService.findAsyncUserAll();
        while (true) {
            if (list1.isDone() && list2.isDone() && list3.isDone()) {
                break;
            }else{
                Thread.sleep(10);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) + "毫秒");
    }


}
