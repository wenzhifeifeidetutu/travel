package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author wz
 * @Date 2021/8/24 1:55
 * @Version 1.0
 **/

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
    * @author WZ
    * @Description 通过名字查询用户
    * @Date 16:49 2021/9/11
    * @Param [java.lang.String]
    * @return cn.itcast.travel.domain.User
    */
    
    @Override
    public User findByUserName(String userName) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where username = ?";
//            System.out.println(userName);
            //执行sql
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
    * @author WZ
    * @Description 保存用户信息类
    * @Date 16:48 2021/9/11
    * @Param [cn.itcast.travel.domain.User]
    * @return void
    */
    
    @Override
    public void save(User user) {
        //定义sql
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email,status, code) " +
                "values(?,?,?,?,?,?,?,?,? )";
        //执行
        jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());


    }

    /**
    * @author WZ
    * @Description 通过code查询用户信息
    * @Date 17:01 2021/9/11
    * @Param [java.lang.String]
    * @return cn.itcast.travel.domain.User
    */

    @Override
    public User findByUserCode(String code) {
        User user = null;
        try {
            //sql
            String sql = "select * from  tab_user where code = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
    * @author WZ
    * @Description 修改当前用户的激活信息
    * @Date 17:02 2021/9/11
    * @Param [cn.itcast.travel.domain.User]
    * @return void
    */

    @Override
    public void changeStatus(User user) {

        String sql = "update tab_user set status = 'Y' where uid = ? ";
        jdbcTemplate.update(sql, user.getUid());


    }

    /**
    * @author WZ
    * @Description 通过账号密码查询用户信息
    * @Date 23:02 2021/9/12
    * @Param [cn.itcast.travel.domain.User]
    * @return cn.itcast.travel.domain.User
    */

    @Override
    public User findByUserNameAndrPassword(User user) {
        User user1 = null;
        String sql =  "select * from tab_user where username = ? and password = ?";
        try {
            user1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(), user.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return user1;
    }
}
