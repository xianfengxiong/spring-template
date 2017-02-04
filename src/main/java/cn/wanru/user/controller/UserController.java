package cn.wanru.user.controller;

import cn.wanru.user.entity.User;
import cn.wanru.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xxf
 * @since 12/26/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user){
        System.out.println(this.getClass().getName());
        return user.toString();
    }

    @RequestMapping("/register")
    public User register(User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/merge")
    public User merge(Long id) throws InvocationTargetException, IllegalAccessException {
        return userService.merge(id);
    }

}
