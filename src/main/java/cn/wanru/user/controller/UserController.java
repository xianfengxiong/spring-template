package cn.wanru.user.controller;

import cn.wanru.user.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxf
 * @since 12/26/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public String sayHello(User user){
        return user.toString();
    }
}
