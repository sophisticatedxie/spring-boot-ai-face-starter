package store.sophi.xjr.enums.check;

import lombok.Getter;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸检测可选参数枚举
 * @author: xjr
 * @create: 2020-06-08 11:00
 **/
@Getter
public enum FaceFieldEnum {

    AGE("age","年龄"),

    BEAUTY("beauty","美丑"),

    EXPRESSION("expression","表情"),

    FACE_SHAPE("face_shape","脸形"),

    GENDER("gender","性别"),

    GLASSES("glasses","眼镜"),

    LANDMARK("landmark","4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心"),

    LANDMARK72("landmark72","72个特征点位置"),

    LANDMARK150("landmark150","150个特征点位置"),

    RACE("race","人种"),

    QUALITY("quality","人脸质量信息"),

    EYE_STATUS("eye_status","双眼状态（睁开/闭合)"),

    EMOTION("emotion","情绪"),

    FACE_TYPE("face_type","真实人脸/卡通人脸");


    private String key="face_field";

    private String code;

    private String message;

    FaceFieldEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

}
