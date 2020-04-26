package com.yubo.springboot;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.service.AyUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public void testTransactional(){
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("赵六");
        ayUser.setPassword("123456");
        System.out.println(ayUserService.save(ayUser));
    }

}
