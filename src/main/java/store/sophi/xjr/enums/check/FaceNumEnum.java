package store.sophi.xjr.enums.check;

import lombok.Getter;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 处理人脸入参枚举
 * @author: xjr
 * @create: 2020-06-11 09:34
 **/
@Getter
public enum FaceNumEnum {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NIGH("9"),
    TEN("10");

    private  String key="max_face_num";

    private String value;



    FaceNumEnum(String value){
        this.value=value;
    }

}
