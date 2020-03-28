package cn.edu.bupt.ch2_1.repository;

import cn.edu.bupt.ch2_1.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Optional<Blog>> findAllByUid(long uid);

    Optional<Blog> findByUid(long uid);
}
