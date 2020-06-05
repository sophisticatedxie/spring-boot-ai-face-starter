package store.sophi.xjr.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 结果视图对象
 * @author: xjr
 * @create: 2020-06-05 10:25
 **/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String error;

    private String message;

    private T data;

    private String token;

    public static <T> ResultVO<T> success(String message) {
        return ResultVO.<T>builder().code(200).message(message).build();
    }

    public static <T> ResultVOBuilder<T> success() {
        return ResultVO.<T>builder().code(200);
    }

    public static <T> ResultVO<T> failure(String message) {
        return ResultVO.<T>builder().code(500).message(message).build();
    }

    public static <T> ResultVO<T> failure(Integer code, String message) {
        return ResultVO.<T>builder().code(code).message(message).build();
    }

    public static <T> ResultVOBuilder<T> failure() {
        return ResultVO.<T>builder().code(500);
    }

    public static <T> ResultVO<T> data(T data) {
        return ResultVO.<T>success().data(data).build();
    }

}