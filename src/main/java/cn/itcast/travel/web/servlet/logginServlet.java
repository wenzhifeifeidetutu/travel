package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @ClassName logginServlet
 * @Description TODO 登录方法
 * @Author wz
 * @Date 2021/9/12 22:46
 * @Version 1.0
 **/

@WebServlet("/loginServlet")
public class logginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceimpl();
        ObjectMapper objectMapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        //获取参数
        Map<String, String[]> stringMap = req.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, stringMap);
            //通过service进行登录
            User resultUser = userService.login(user);
            if (resultUser == null) {
                //找不到这个账号密码
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号密码错误! 请重新登录");
            }else {
                //账号密码正确，判断是否激活
                if ("Y".equals(resultUser.getStatus())) {
                    //已经激活
                    resultInfo.setFlag(true);
                    //存到session里面
                    req.getSession().setAttribute("user",resultUser.getName());
                }else {
                    //未激活，提示用户激活账号在登录
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("此账号未激活，请激活后重新登录！");
                }
            }

            //返回数据
            String resultJson = objectMapper.writeValueAsString(resultInfo);
            //返回
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(resultJson);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
