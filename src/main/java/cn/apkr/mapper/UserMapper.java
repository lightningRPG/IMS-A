package cn.apkr.mapper;

import cn.apkr.domain.User;

import java.util.List;

public interface UserMapper {
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
}
