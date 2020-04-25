package cn.edu.bupt.ch5_3.mapper;

import cn.edu.bupt.ch5_3.entity.Comment;
import cn.edu.bupt.ch5_3.entity.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FriendMapper extends BaseMapper<Friend> {

}
