package cn.edu.bupt.ch2_1.controller;

import cn.edu.bupt.ch2_1.common.Result;
import cn.edu.bupt.ch2_1.entity.Blog;
import cn.edu.bupt.ch2_1.repository.BlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "BlogController v1",description = "利用自定义消息格式的用户信息控制器")
@RequestMapping("/v1/blogs")
@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @ApiOperation(value="发表新的博客",notes = "创建成功，返回状态码201")
    @PostMapping(path= "/", consumes="application/json", produces = "application/json")
    public Result<?>  add(@RequestBody Blog blog){
        blog=blogRepository.save(blog);
        return Result.ok(blog);
    }

    @ApiOperation(value="根据id删除blog",notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @ApiResponses({@ApiResponse(code=204,message = "删除成功")})
    @DeleteMapping(path= "/{sid}")
    public Result<?> delete(@PathVariable  Long sid){
        Optional<Blog> result = blogRepository.findById(sid);
        if(result.isPresent()){
            blogRepository.deleteById(sid);
            return Result.ok();
        }else{
            return Result.error(204,"该blog不存在，删除失败");
        }
    }

    @ApiOperation(value="根据id更新blog",notes = "如果不存在，则返回状态码404")
    @PutMapping(path= "/{sid}",consumes="application/json", produces = "application/json")
    public Result<?> update(@PathVariable Long sid , @RequestBody Blog blog){
        Optional<Blog> result = blogRepository.findById(sid);
        if(result.isPresent()){
            Blog currentBlog = result.get();
            currentBlog.setContent(blog.getContent());
            blogRepository.save(currentBlog);
            return Result.ok(currentBlog);
        }else{
            return Result.error(404,"该blog不存在");
        }
    }
}
