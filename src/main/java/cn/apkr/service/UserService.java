package cn.apkr.service;

import cn.apkr.config.MyBatisConfig;
import cn.apkr.domain.User;
import cn.apkr.mapper.UserMapper;

import java.util.List;


public interface UserService {

    /**
     * 查询指定用户信息
     * @param user
     * @return
     */
    List<User> selectUser(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectAll();

    /**
     * 通过用户ID查找用户信息
     * @param uid
     * @return
     */
    User selectById(String uid);

    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);

    Boolean insertUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);

    Boolean deleteAll();

    Boolean deleteById(String uid);

    Boolean deleteByUsername(String username);

    // 注册
    public Boolean signIn(String username, String password);

    // 登录
    public Boolean signUp(String username, String password);

    public Boolean checkLogin(short loginState);
}
