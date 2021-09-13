package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @ClassName UserServiceimpl
 * @Description TODO
 * @Author wz
 * @Date 2021/8/24 1:41
 * @Version 1.0
 **/

public class UserServiceimpl implements UserService {
    private UserDao userDao = new UserDaoImpl();


    @Override
    public boolean regiest(User user) {
        User user1 = userDao.findByUserName(user.getUsername());
        if (user1 == null) {
            //保存用户之前需要设置code和激活状态
            user.setStatus("N");
            user.setCode(UuidUtil.getUuid());
            userDao.save(user);
            //发送邮件
            String content = "请点击<a href=\"http://localhost/travel/activityServlet?code="+user.getCode()+"\">激活邮箱</a>";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean emailok = MailUtils.sendMail(user.getEmail(), content,"激活邮件");
                    System.out.println("邮件发送成功 "+emailok);
                }
            }).start();
            //返回注册成功
            return true;
        }else {
            return false;
        }



    }

    /**
    * @author WZ
    * @Description 激活用户,给用户发激活邮件
    * @Date 16:53 2021/9/11
    * @Param [java.lang.String]
    * @return boolean
    */

    @Override
    public boolean active(String code) {
        if (code != null && !code.isEmpty()) {
            User user =  userDao.findByUserCode(code);
            if (user != null) {
                //找到这个用户，那么进行修改status激活状态
                userDao.changeStatus(user);
                //返回激活成功
                return true;
            }
        }
        return false;
    }

    /**
    * @author WZ
    * @Description 通过账号密码进行查询用户信息
    * @Date 22:57 2021/9/12
    * @Param [cn.itcast.travel.domain.User]
    * @return cn.itcast.travel.domain.User
    */


    @Override
    public User login(User user) {

        User user1 = userDao.findByUserNameAndrPassword(user);

        return user1;
    }
}
