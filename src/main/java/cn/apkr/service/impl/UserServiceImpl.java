package cn.apkr.service.impl;

import cn.apkr.domain.User;
import cn.apkr.mapper.UserMapper;
import cn.apkr.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(String uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Boolean insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Boolean deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public Boolean deleteAll() {
        return userMapper.deleteAll();
    }

    @Override
    public Boolean deleteById(String uid) {
        return userMapper.deleteById(uid);
    }

    @Override
    public Boolean deleteByUsername(String username) {
        return userMapper.deleteByUsername(username);
    }

    // 注册
    public Boolean signIn(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 验证账户和密码
        return !userMapper.selectUser(user).isEmpty();
    }

    // 登录
    public Boolean signUp(String username, String password) {
        User tempUser = new User();
        String id = String.format("%016x", System.currentTimeMillis());
        tempUser.setUsername(username);
        if (userMapper.selectUser(tempUser).isEmpty()) {
            tempUser.setUid(id);
            tempUser.setPassword(password);
            return userMapper.insertUser(tempUser);
        } else {
            return false;
        }
    }

    public Boolean checkLogin(short loginState) {


        return false;
    }
}
