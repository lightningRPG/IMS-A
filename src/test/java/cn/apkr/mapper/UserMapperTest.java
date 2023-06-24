//package cn.apkr.mapper;
//
//import cn.apkr.config.MyBatisConfig;
//import cn.apkr.config.SpringConfig;
//import cn.apkr.domain.User;
//import cn.apkr.service.UserService;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class UserMapperTest {
//    private final SqlSessionFactory sessionFactory = (SqlSessionFactory) SpringConfig.ctx.getBean("sessionFactory");
//    private final SqlSession sqlSession = sessionFactory.openSession(true);
//    private final UserMapper userService = sqlSession.getMapper(UserMapper.class);
//
//    @Test
//    public void testSelectUser() {
//        User user = new User();
//        user.setUid("test123456");
//        user.setUsername("1234");
//        user.setPassword("1234");
//        List<User> users = userService.selectUser(user);
//        Assert.assertNotNull(users);
//        System.out.println(users);
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<User> users = userService.selectAll();
//        Assert.assertNotNull(users);
//        System.out.println(users);
//    }
//
//    @Test
//    public void testSelectById() {
//        User user = userService.selectById("test123456");
//        Assert.assertNotNull(user);
//        System.out.println(user);
//    }
//
//    @Test
//    public void testSelectByUsername() {
//        User user = userService.selectByUsername("test1");
//        Assert.assertNotNull(user);
//        System.out.println(user);
//    }
//
//    @Test
//    public void testInsertUser() {
//        User user = new User();
//        user.setUid("1111");
//        user.setUsername("1111");
//        user.setPassword("1111");
//        Assert.assertTrue(userService.insertUser(user));
//        sqlSession.rollback();
//
//    }
//
//    @Test
//    public void testUpdateUser() {
//        User user = new User();
//        user.setUid("test123456");
//        user.setUsername("1234");
//        user.setPassword("1234");
//        Assert.assertTrue(userService.updateUser(user));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteUser() {
//        User user = new User();
//        user.setUid("1111");
//        user.setUsername("1111");
//        user.setPassword("1111");
//        Assert.assertTrue(userService.deleteUser(user));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteAll() {
//        Assert.assertTrue(userService.deleteAll());
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteById() {
//        Assert.assertTrue(userService.deleteById("test123456"));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteByUsername() {
//        Assert.assertTrue(userService.deleteByUsername("test2"));
//        sqlSession.rollback();
//    }
//}
