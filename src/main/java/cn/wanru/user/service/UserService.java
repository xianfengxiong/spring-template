package cn.wanru.user.service;

import cn.wanru.user.entity.User;
import cn.wanru.user.repo.UserRepo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xxf
 * @since 1/1/17
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User merge(Long id) throws InvocationTargetException, IllegalAccessException {
        User user = userRepo.findOne(id);
        User des = new User();
        BeanUtils.copyProperties(des,user);
        des.setUsername("test");
        des.setId(id);

        return userRepo.save(des);
    }

}
