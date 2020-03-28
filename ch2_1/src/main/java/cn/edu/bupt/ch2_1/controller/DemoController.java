package cn.edu.bupt.ch2_1.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "DemoController",description = "用来测试swagger注解的控制器")
@RequestMapping("/api/demo")
@RestController
public class DemoController {

    @ApiOperation(value="一个hello world API",notes = "返回值为hello+user_name")
    @ApiImplicitParam(name = "user_name", value = "say hello 对象的名字",required = true)
    @GetMapping(value = "/hello")
    public String hello(String user_name)
    {
        return "hello " + user_name;
    }

    @ApiOperation(value="除法计算API",notes = "返回值为商的整数部分，当除数为0时返回400状态码")
    @ApiImplicitParams({@ApiImplicitParam(name = "a", value = "被除数a",required = true),
            @ApiImplicitParam(name = "b", value = "除数b",required = true),
    })
    @ApiResponses({@ApiResponse(code=400,message = "除数不能为0")})
    @GetMapping(value = "/div/{a}/{b}")
    public ResponseEntity<String> div(@PathVariable Long a, @PathVariable  Long b)
    {
        try {
            Long div = a/b;
            return new ResponseEntity<String>("the div is：" + String.valueOf(div), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
        }
    }
}
