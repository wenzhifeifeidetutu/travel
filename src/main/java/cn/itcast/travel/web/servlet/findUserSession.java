package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName findUserSession
 * @Description TODO
 * @Author wz
 * @Date 2021/9/12 23:54
 * @Version 1.0
 **/

@WebServlet("/findUserSession")
public class findUserSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //找session中的User对象
        HttpSession session = req.getSession();
        String  username = (String) session.getAttribute("user");
        if (username != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            resp.setContentType("application/json;charset=utf-8");
            Map<String, String> map = new HashMap<String,String>();
            map.put("name", username);
            objectMapper.writeValue(resp.getOutputStream(), map);
            System.out.println(username);
        }else {
            System.out.println("找不到用户信息");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
