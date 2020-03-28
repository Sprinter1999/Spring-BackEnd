package cn.edu.bupt.ch2_1.repository;

import cn.edu.bupt.ch2_1.entity.User;
import cn.edu.bupt.ch2_1.repository.NoPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    NoPassword findByUid(Long uid);
}
