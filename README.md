                                               

## Spring-Boot-Ai-Face-Starter
![输入图片说明](https://img.shields.io/badge/license-Apache--2.0-blue "在这里输入图片标题") ![输入图片说明](https://img.shields.io/badge/maven-1.0.0-green "在这里输入图片标题") ![输入图片说明](https://img.shields.io/badge/Prs-welcome-red "在这里输入图片标题")

   :smile:  :smile:  :smile: 

  本项目以springboot2.0.2.RELEASE为基础，结合百度ai人工智能的人脸识别java sdk，封装其底层复杂参数传递，将其进行"springboot"化，在学习和使用本项目之前建议对于springboot2.x 以及java有一定的基础.

   **学习前**
   1. [springboot学习](http://blog.didispace.com/spring-boot-learning-2x/) 
   2. [百度人脸识别java-sdk在线文档](https://cloud.baidu.com/doc/FACE/s/8k37c1rqz)

   **使用方法** 

   1. 初始化spring-boot项目，建议2.0以上版本。

   2. 导入项目maven依赖(gradle玩家可以前往maven中仓寻找对应的依赖写法)

 ```
 <dependency>
     <groupId>store.sophi.xjr</groupId>
     <artifactId>spring-boot-ai-face-starter</artifactId>
     <version>1.0.0.RELEASE</version>
</dependency>
```
   3.SpringBoot启动类上标注@EnableAiFace注解，可通过此注解开关人脸组件功能。
     

```
@SpringBootApplication
@EnableAiFace
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class,args);
    }

}
```


  4.配置文件写法(需要提前在百度开发者平台申请自己的应用账号信息)

```
ai:
  face:
    apiKey: 
    appId: 
    secretKey: 

```

  5.默认情况自动注入AiFaceTemplate工具类，目前实现功能人脸对比、人脸搜索、人脸添加，后续将不断更新，欢迎大家在评论区提意见或者PR。


  6.如果需要自己扩展该人脸功能，请自行创建类继承AiFaceTemplate，配合spring注册bean注解使用，自动拥有人脸操作客户端工具，具体扩展可以参考上面的百度api文档，最后在启动类注解中指明你的自定义扩展实现，即可在项目中使用。


    
   开源不易，各位developer给颗星哈。

   欢迎发起PR，一起来完善这个项目。  
 
