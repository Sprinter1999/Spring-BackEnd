package cn.edu.bupt.ch2_1.repository;

import cn.edu.bupt.ch2_1.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUid(Long uid);

    @Transactional
    public void deleteByUidAndFid(Long uid,Long fid);

    Optional<Friend> findByUidAndFid(Long uid,Long fid);
}
