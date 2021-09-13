package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author wz
 * @Date 2021/8/24 1:54
 * @Version 1.0
 **/

public interface UserDao {
    /**
    * @author WZ
    * @Description 根据用户名查询用户信息
    * @Date 1:58 2021/8/24
    * @Param [java.lang.String]
    * @return cn.itcast.travel.domain.User
    */
    
    public User findByUserName(String userName);
    /**
    * @author WZ
    * @Description 保存此用户
    * @Date 1:58 2021/8/24
    * @Param [cn.itcast.travel.domain.User]
    * @return void
    */
    public void save(User user);

    /**
    * @author WZ
    * @Description 通过code查询用户
    * @Date 17:00 2021/9/11
    * @Param [java.lang.String]
    * @return cn.itcast.travel.domain.User
    */

    public User findByUserCode(String code);

    /**
    * @author WZ
    * @Description 修改用户的激活信息
    * @Date 17:00 2021/9/11
    * @Param [cn.itcast.travel.domain.User]
    * @return void
    */

    public void changeStatus(User user);

    /**
    * @author WZ
    * @Description 通过账号密码查询用户信息
    * @Date 23:32 2021/9/12
    * @Param [cn.itcast.travel.domain.User]
    * @return cn.itcast.travel.domain.User
    */
    
    User findByUserNameAndrPassword(User user);
}
