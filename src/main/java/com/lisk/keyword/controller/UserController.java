package com.lisk.keyword.controller;

import com.lisk.keyword.pojo.User;
import com.lisk.keyword.repository.UserRepository;
import com.lisk.keyword.service.UserService;
import com.lisk.keyword.util.Ltp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    //查词所有用户
    //入口
    @GetMapping("/userlist")
    public ModelAndView userList(Model model)throws UnsupportedEncodingException {
//        model.addAttribute("userList",userRepository.userList());
//        model.addAttribute("title","用户管理");
//        return  new ModelAndView("user/list","userModel",model);
        model.addAttribute("userList",userService.list());
        model.addAttribute("title","用户管理");
        return  new ModelAndView("user/list","userModel",model);

    }
    @RequestMapping(value = "/userlist/keyword" ,method=RequestMethod.POST)
    public String getKeyword(Model model ,@RequestParam("text")String text) throws UnsupportedEncodingException{
        System.out.println(text);
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
            System.out.println(jsonArray.getJSONObject(i).getString("word"));
        }
        //返回JSON数据
        model.addAttribute("keywordData",keywordData);
        return JSONObject.fromObject(jsonObject.getString("data")).getString("ke");
    }

    //根据id 查询用户
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        User user= userRepository.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("title","查看用户");
        return new ModelAndView("user/view" ,"userModel",model);
    }

    //获取创建表单页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }

    //保存用户
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        user =userRepository.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/user/userlist");
    }

    //根据id删除用户
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        userRepository.deleteUser(id);
        return new ModelAndView("redirect:/user/userlist");
    }

    //修改用户界面
    @GetMapping(value = "edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id, Model model){
        User user =userRepository.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("title","编辑用户");
        return new ModelAndView("user/form" ,"userModel",model);
    }
}
