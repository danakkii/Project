package mini.project.pro.controller;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.pro.mapper.UserMapper;
import mini.project.pro.model.User;

//유저 관련된 요청을 처리하는 컨트롤러
// 회원가입, 로그인, 로그아웃 
@Controller
@RequestMapping("user") // http://localhost:8080/user
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("join")
    public String join() {
     
        return "/user/join";

        }

    @PostMapping("join")
    public String join(HttpSession session, User user) {
        userMapper.join(user);
        return "redirect:/join";
    }


    @GetMapping("login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("login")
    public String login(HttpSession session, User user) {
        // 로그인 시도하는 계정 정보
        String id = user.getUserId();
        String pw = user.getUserPw();
        // 로그인 시도하는 id값으로 해당하는 사용자의 비밀번호를 가져온다.
        String getPw = userMapper.getPw(id);
        if (getPw != null) {
            if (getPw.equals(pw)) {
                // DB에서 유정보를 가져와서 userData에 저장
                User userData = userMapper.selectUser(id);
                session.setAttribute("user", userData);
            }
        } else {
            session.setAttribute("user", null);
        }

        return "redirect:/main";
    }

    // 로그아웃 기능 구현 http://localhost:8080/user/logout
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/main";
    }
}
