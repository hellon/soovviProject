package com.jovision.soovvi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.jovision.soovvi.modelBean.RootData;
import com.jovision.soovvi.utils.HttpsUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


/**
 * Created by Administrator on 2017/8/8 0008.
 */


@RestController
@RequestMapping(path = "/youzan")
public class YouZanController {


    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * 统一异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Map<String,RootData> err(Exception ex){
        log.error("获取信息失败",ex);
        RootData root = new RootData("false","获取信息失败",Maps.newHashMap(),"1");
        return ImmutableMap.of("root",root);
    }


    /**
     * 统一参数处理方法
     * @param client_id
     * @param client_secret
     * @param open_user_id
     * @return
     */
    @ModelAttribute("map")
    public Map<String,String> paramMap(String client_id, String client_secret, @RequestParam(defaultValue = "123456") String open_user_id) {
        return ImmutableMap.of("client_id", client_id,"client_secret",client_secret,"open_user_id",open_user_id);
    }



    /**
     * 根据传入的字符串对其进行转换为map对象
     * @param json 转换为Map对象的json字符串
     * @return  转换成功的map对象
     * @throws IOException
     */
    private  Map<String,RootData> strToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RootData root = new RootData();
        Map<String,Object> map = mapper.readValue(json, Map.class);
        root.setData(map.get("data"));
        root.setErrorCode(map.get("code")+"");
        root.setMsg(map.get("msg")+"");
        root.setResult("true");
        return ImmutableMap.of("root",root);
    }


    /**
     *  对远程获取的字符串进行对象转换
     * @param methodName 远程需要调用的方法名
     * @param map 远程调用需要的参数
     * @return 转成成功的map对象
     */
    private Map<String,RootData> returnMap(String methodName,Map<String,String> map) throws Exception {
        String url = "https://uic.youzan.com/sso/open/" + methodName;

        String resp = HttpsUtils.post(url,
                ImmutableMap.of("Content-Type", "application/x-www-form-urlencoded"),
                map,
                null);
        return strToMap(resp);
    }



    @RequestMapping(path = "/initToken",method = RequestMethod.GET)
    public Map<String,RootData> initToken(Map<String,Map<String,String>> model) throws Exception {
        return returnMap("initToken",model.get("map"));
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public Map<String,RootData> login(Map<String,Map<String,String>> model) throws Exception {
        return returnMap("login",model.get("map"));
    }

    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public Map<String,RootData>  logout(Map<String,Map<String,String>> model) throws Exception {
        return returnMap("logout",model.get("map"));
    }
}
