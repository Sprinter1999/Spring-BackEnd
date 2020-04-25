package cn.edu.bupt.ch5_3.service.impl;

import cn.edu.bupt.ch5_3.entity.Comment;
import cn.edu.bupt.ch5_3.entity.Product;
import cn.edu.bupt.ch5_3.mapper.CommentMapper;
import cn.edu.bupt.ch5_3.mapper.ProductMapper;
import cn.edu.bupt.ch5_3.service.ICommentService;
import cn.edu.bupt.ch5_3.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
