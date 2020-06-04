package store.sophi.xjr.util;

import cn.hutool.core.codec.Base64Encoder;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import store.sophi.xjr.enums.ResultEnum;
import store.sophi.xjr.exception.ApiException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸操作工具类
 * @author: xjr
 * @create: 2020-05-31 01:07
 **/
@Slf4j
@Getter
@Setter
public class AiFaceTemplate implements InitializingBean {



    @Autowired(
            required = false
    )
    protected AipFace aipFace;

    protected final String IMAGE_TYPE = "BASE64";

    protected  final HashMap<String, String> ADD_USER_OPTIONS = new HashMap<String, String>(4);

    protected  final HashMap<String, String> SEARCH_USER_OPTIONS = new HashMap<String, String>(3);

    protected  final HashMap<String, String> CHECK_USER_OPTIONS = new HashMap<String, String>(4);


    /**
     *
     * 人脸对比，返回相似度
     * @param img1: base64
     * @param img2: base64
     * @author xiejiarong
     * @date 2020年06月03日 9:24
     */
    public String compare(String img1, String img2) {
        MatchRequest req1 = new MatchRequest(img1, "BASE64");
        MatchRequest req2 = new MatchRequest(img2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList();
        requests.add(req1);
        requests.add(req2);
        JSONObject res = this.aipFace.match(requests);
        return res.toString(2);
    }

    /**
     *
     * 人脸对比，返回相似度
     * @param img1: 图片流
     * @param  img2 图片流
     * @author xiejiarong
     * @date 2020年06月03日 9:25
     */
    public String compare(MultipartFile img1,MultipartFile img2){
        String[] imgs=stream2Base64(img1,img2);
        return this.compare(imgs[0],imgs[1]);
    }

    /**
     *
     *人脸检测
     * @param base64FaceImg:  人脸图片base64
     * @return String
     * @author xiejiarong
     * @date 2020年06月04日 18:32
     */
    public String checkFace(String base64FaceImg) {
        HashMap<String, String> options = new HashMap();
        options.put("face_field", "age");
        options.put("liveness_control", "LOW");
        return this.aipFace.detect(base64FaceImg, "BASE64", options).toString(2);
    }

    public String checkFace(MultipartFile img) {
       String imgBase64=this.stream2Base64(img);
       return this.checkFace(imgBase64);
    }

    protected java.lang.String stream2Base64(MultipartFile file) {
        InputStream inputStream=null;
        byte[] strByte=null;
        try {
            inputStream = file.getInputStream();
            strByte = new byte[inputStream.available()];
            inputStream.read(strByte);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Base64Encoder.encode(strByte);
    }

    protected  String[] stream2Base64(MultipartFile ... files){
        List<MultipartFile> list=Arrays.asList(files);
        return list.stream().map(this::stream2Base64).collect(Collectors.toList()).toArray(new String[files.length]);
    }

    public void checkAip() throws ApiException {
        if (this.aipFace == null) {
            throw new ApiException(ResultEnum.NOT_INITIALIZING);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        log.info("人脸操作工具类初始化结束,注入人脸客户端信息：{}", this.aipFace);
    }

    public void init(){
        SEARCH_USER_OPTIONS.put("quality_control", "LOW");
        SEARCH_USER_OPTIONS.put("liveness_control", "NONE");
        SEARCH_USER_OPTIONS.put("match_threshold", "85");
        ADD_USER_OPTIONS.put("quality_control", "LOW");
        ADD_USER_OPTIONS.put("liveness_control", "NONE");
        ADD_USER_OPTIONS.put("action_type", "REPLACE");
        CHECK_USER_OPTIONS.put("quality_control", "LOW");
        CHECK_USER_OPTIONS.put("liveness_control", "NONE");
        CHECK_USER_OPTIONS.put("match_threshold", "85");
    }


}
