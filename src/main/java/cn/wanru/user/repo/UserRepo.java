package cn.wanru.user.repo;

import cn.wanru.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xxf
 * @since 2/4/17
 */
public interface UserRepo extends JpaRepository<User,Long> {
}
