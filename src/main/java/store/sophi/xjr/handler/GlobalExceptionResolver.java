package store.sophi.xjr.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import store.sophi.xjr.exception.ApiException;
import store.sophi.xjr.vo.ResultVO;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 全局异常捕获
 * @author: xjr
 * @create: 2020-06-05 10:22
 **/
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionResolver {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResultVO apiExceptionHandler(ApiException ex){
        return ResultVO.failure(ex.getMessage());
    }

}
