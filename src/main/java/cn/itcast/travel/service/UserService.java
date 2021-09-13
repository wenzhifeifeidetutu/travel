package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author wz
 * @Date 2021/8/24 1:39
 * @Version 1.0
 **/

public interface UserService {

    /**
    * @author WZ
    * @Description 注册用户
    * @Date 2:19 2021/8/24
    * @Param [cn.itcast.travel.domain.User]
    * @return boolean
    */

    boolean regiest(User user);


    boolean active(String code);

    User login(User user);

}
