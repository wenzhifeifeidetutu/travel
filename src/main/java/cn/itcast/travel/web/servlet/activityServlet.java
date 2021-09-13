package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName activityServlet
 * @Description TODO
 * @Author wz
 * @Date 2021/9/11 16:51
 * @Version 1.0
 **/

@WebServlet("/activityServlet")
public class activityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置发送编码防止乱码

        UserService userService = new UserServiceimpl();
        //获取激活码
        String code = req.getParameter("code");
        if (code != null && !code.isEmpty()) {
            //调用激活
            boolean flag = userService.active(code);
            if (flag) {
                //激活成功
                resp.getWriter().write("激活成功! 点击登录");
            }else {
                resp.getWriter().write("激活失败，请检测网络！！！");
            }

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
