package cn.edu.bupt.ch5_3.controller;

import cn.edu.bupt.ch5_3.common.Result;
import cn.edu.bupt.ch5_3.entity.Comment;
import cn.edu.bupt.ch5_3.entity.Product;
import cn.edu.bupt.ch5_3.service.ICommentService;
import cn.edu.bupt.ch5_3.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品及评论接口（Service版）")
@RestController
@RequestMapping("/api/v2/product")
public class ProductController2 {
    private IProductService productService;
    private ICommentService commentService;

    public ProductController2(IProductService productService, ICommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    @ApiOperation(value = "获取商品列表", notes = "返回值为全部的商品列表")
    @GetMapping(path = "/", produces = "application/json")
    public Result<?> listAll() {
        List<Product> products = productService.list();
        return Result.ok(products);
    }


    @ApiOperation(value = "添加新的商品", notes = "创建成功，返回状态码201")
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Result<?>  add(@RequestBody Product product) {
          productService.save(product);
        return Result.ok(product);
    }

    @ApiOperation(value = "根据id删除商品", notes = "如果不存在，则返回状态码404，如果存在且删除成功，则返回状态码204")
    @ApiResponses({@ApiResponse(code = 204, message = "删除成功")})
    @DeleteMapping(path = "/{prodId}")
    public Result<?> delete(@PathVariable Long prodId) {
        boolean res = productService.removeById(prodId);
        if (res) {
            return Result.ok();
        } else {
            return Result.error(204, "该商品不存在");
        }
    }

    @ApiOperation(value = "获取商品评论列表", notes = "返回值为商品的评论列表")
    @GetMapping(path = "/{prodId}/comment/", produces = "application/json")
    public Result<?> commentListAll(@PathVariable int prodId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", prodId);
        List<Comment> comments = commentService.list(queryWrapper);
        if (comments!= null) {
            return Result.ok(comments);
        } else {
            return Result.error(204, "评论不存在");
        }
    }

    @ApiOperation(value = "为商品添加评论", notes = "创建成功，返回状态码201")
    @PostMapping(path = "/{prodId}/comment/", consumes = "application/json", produces = "application/json")
    public Result<?>  commentAdd(@PathVariable Long prodId, @RequestBody Comment comment) {
        Product product = productService.getById(prodId);
        if (product != null) {
            comment.setProductId(prodId);
            commentService.save(comment);
            return Result.ok(comment);
        } else {
            return Result.error(204, "该商品不存在");
        }
    }
}
