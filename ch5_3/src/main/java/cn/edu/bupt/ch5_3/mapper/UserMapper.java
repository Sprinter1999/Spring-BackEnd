package cn.edu.bupt.ch5_3.mapper;

import cn.edu.bupt.ch5_3.entity.Comment;
import cn.edu.bupt.ch5_3.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {

}
