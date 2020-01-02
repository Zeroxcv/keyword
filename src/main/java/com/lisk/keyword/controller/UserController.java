package com.lisk.keyword.controller;

import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.User;
import com.lisk.keyword.repository.UserRepository;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.service.UserService;
import com.lisk.keyword.util.Ltp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    //查词所有用户
    @Autowired
    private EssayService essayService;
    //入口
    @GetMapping("/userlist")
    public ModelAndView userList(Model model)throws UnsupportedEncodingException {
//        model.addAttribute("userList",userRepository.userList());
//        model.addAttribute("title","用户管理");
//        return  new ModelAndView("user/list","userModel",model)
        model.addAttribute("title","用户管理");
        return  new ModelAndView("user/list","userModel",model);

    }
    //调用讯飞提取关键词接口
    @RequestMapping(value = "/userlist/keyword" ,method=RequestMethod.POST)
    public String getKeyword(Model model ,@RequestParam("text")String text) throws UnsupportedEncodingException{
        System.out.println(text);
        String keywordData;
        keywordData = Ltp.getLtpData(text);
        //解析返回的JSON数据
        JSONObject jsonObject = JSONObject.fromObject(keywordData);
        //{"code":"0",
        // "data":{"ke":[{"score":"0.907","word":"关键词"},{"score":"0.867","word":"提取"}]},
        // "desc":"success",
        // "sid":"ltp003889ed@dx23571166478fa00100"}
        if("success".equals(jsonObject.getString("desc"))){
            System.out.println("success");
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ke");
            for(int i = 0; i < jsonArray.size(); i++){
                System.out.println(jsonArray.getJSONObject(i).getString("word"));
            }
            //返回JSON数据
            //model.addAttribute("keywordData",keywordData);
            return JSONObject.fromObject(jsonObject.getString("data")).getString("ke");
        }
        else{
            return "error";
        }

    }

    @RequestMapping(value = "/userlist/1" ,method=RequestMethod.GET)
    //查询文章的关键词
    public List<Essay> listEssays(){

        List<Essay> list;
        list = essayService.listEssays();
        return list;
    }
}
