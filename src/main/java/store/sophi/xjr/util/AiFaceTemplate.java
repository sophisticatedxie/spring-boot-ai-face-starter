package store.sophi.xjr.util;

import cn.hutool.core.codec.Base64Encoder;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import store.sophi.xjr.enums.ResultEnum;
import store.sophi.xjr.exception.ApiException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸操作工具类
 * @author: xjr
 * @create: 2020-05-31 01:07
 **/
@Slf4j
public class AiFaceTemplate implements InitializingBean {



    @Autowired(
            required = false
    )
    AipFace aipFace;
    protected final String IMAGE_TYPE = "BASE64";
    protected  final HashMap<String, String> ADD_USER_OPTIONS = new HashMap(4);
    protected  final HashMap<String, String> SEARCH_USER_OPTIONS = new HashMap(3);
    protected  final HashMap<String, String> CHECK_USER_OPTIONS = new HashMap(4);


    protected String compareWithTwoImg(String img1, String img2) {
        MatchRequest req1 = new MatchRequest(img1, "BASE64");
        MatchRequest req2 = new MatchRequest(img2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList();
        requests.add(req1);
        requests.add(req2);
        JSONObject res = this.aipFace.match(requests);
        return res.toString(2);
    }

    protected String checkFace(String base64FaceImg) {
        HashMap<String, String> options = new HashMap();
        options.put("face_field", "age");
        options.put("liveness_control", "LOW");
        return this.aipFace.detect(base64FaceImg, "BASE64", options).toString(2);
    }

    protected String stream2Base64(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        byte[] strByte = new byte[inputStream.available()];
        inputStream.read(strByte);
        return Base64Encoder.encode(strByte);
    }

    protected  String[] stream2Base64(MultipartFile file, MultipartFile file2) throws Exception {
        InputStream inputStream1 = file.getInputStream();
        InputStream inputStream2 = file2.getInputStream();
        byte[] strByte1 = new byte[inputStream1.available()];
        byte[] strByte2 = new byte[inputStream2.available()];
        inputStream1.read(strByte1);
        inputStream2.read(strByte2);
        String[] res = new String[]{Base64Encoder.encode(strByte1), Base64Encoder.encode(strByte2)};
        return res;
    }

    protected void checkAip() throws ApiException {
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
