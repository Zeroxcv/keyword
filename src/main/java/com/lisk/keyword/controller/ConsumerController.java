package com.lisk.keyword.controller;

import com.lisk.keyword.util.Ltp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

public class ConsumerController {
    @RequestMapping(value = "consumer/keyword" ,method= RequestMethod.POST)
    public String getKeyword(@RequestParam("text")String text) throws UnsupportedEncodingException {
//        System.out.println(text);
        String keywordData;
        keywordData = Ltp.getLtpData(new String(text));
//        JSONArray jsonArray = JSONArray.fromObject(keywordData);
//        for(int i=0; i<jsonArray.size();i++){
//            System.out.println((jsonArray.getJSONObject(i).getString("code")));
//        }
        //解析返回的JSON数据
        JSONObject jsonObject = JSONObject.fromObject(keywordData);
        System.out.println(jsonObject.getString("data"));
        JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(jsonObject.getString("data")).getString("ke"));
        for(int i = 0; i < jsonArray.size(); i++){
            System.out.println(jsonArray.getJSONObject(i).getString("word")+" " +jsonArray.getJSONObject(i).getString("score"));

        }
        //返回JSON数据

        return JSONObject.fromObject(jsonObject.getString("data")).getString("ke");
    }
}
