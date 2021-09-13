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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @ClassName RegiestServlet
 * @Description TODO
 * @Author wz
 * @Date 2021/8/24 0:30
 * @Version 1.0
 **/
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断flag成功还是失败，返回相应的信息
        ResultInfo resultInfo = new ResultInfo();

        //先获取验证码，判断验证码是否正确
        String checkCode = req.getParameter("check");
        //在session里面获取存入的check
        HttpSession session =  req.getSession();
        String sessionCheckCode = (String) session.getAttribute("CHECKCODE_SERVER");
        //获取之后，必须要清空此session 防止获取验证码有问题
        session.removeAttribute("CHECKCODE_SERVER");

        if (sessionCheckCode != null && sessionCheckCode.equalsIgnoreCase(checkCode)) {
            //验证码正确
            //获取所有的参数
            Map<String, String[]> requestMaps = req.getParameterMap();
            //封装对象
            User user = new User();
            try {
                BeanUtils.populate(user, requestMaps);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //调用service
            UserService userService = new UserServiceimpl();
            boolean flag = userService.regiest(user);

            if (flag) {
                //成功
                resultInfo.setFlag(true);


            }else {
                //失败
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("注册失败");
            }


        }else {
            //验证码验证失败
            resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }

        //将resultinfo格式化json传回去
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson =  objectMapper.writeValueAsString(resultInfo);
        //将json写回客户端
        resp.setContentType("application/json;charset=utf-8");
        resp.getOutputStream().write(resultJson.getBytes());





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
