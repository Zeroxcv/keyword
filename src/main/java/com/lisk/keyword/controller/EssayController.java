package com.lisk.keyword.controller;

import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.repository.UserRepository;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.service.UserService;
import com.lisk.keyword.util.Ltp;
import com.lisk.keyword.util.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "")
public class EssayController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    //查词所有用户
    @Autowired
    EssayService essayService;
    //入口
    @GetMapping("")
    public ModelAndView userList(Model model)throws UnsupportedEncodingException {
        model.addAttribute("title","用户管理");
        return  new ModelAndView("user/index","userModel",model);

    }
    //调用讯飞提取关键词接口
    @ResponseBody
    @RequestMapping(value = "/keyword" ,method=RequestMethod.POST)
    public String getKeyword(Model model ,@RequestParam("text")String text) throws UnsupportedEncodingException{
        System.out.println(text);
        String keywordData;
        keywordData = Ltp.getLtpData(text);
        //传过来的数据可能没有
        if(keywordData.equals(""))
            return "error";
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
            return JSONObject.fromObject(jsonObject.getString("data")).getString("ke");
        }
        else{
            return "error";
        }

    }

    @RequestMapping(value = "/essay")
    public String Essay(){
        return "essay/essay";
    }

    //分页查询文章
    @ResponseBody
    @RequestMapping(value = "/essay/list/{start}" ,method=RequestMethod.GET)
    public Map listEssays(@PathVariable("start")Integer start){
        Page page = new Page();
        start = start * 10 ;
        page.setStart(start);
        page.setCount(Page.getDefaultCount());
        List<Essay> list;

        list = essayService.listEssays(page);
        Map map = new HashMap<>();
        map.put("list",list);
        map.put("page",page);
        return map;
    }
    @RequestMapping(value = "/extractSearch",method = RequestMethod.GET)
    public ModelAndView extractSearch(Model model){
        return  new ModelAndView("essay/extractSearch","essayModel",model);
    }
    @ResponseBody
    @RequestMapping(value = "/essay/findAll" ,method=RequestMethod.GET)
    public List<Essay> findAll(){
        return essayService.findAll();
    }
        @GetMapping(value = "/essay/add")
    public String addEssay(){
        return "essay/add";
    }

    @ResponseBody
    @RequestMapping(value = "/essay/addEssay" ,method=RequestMethod.POST)
    public String add(String title,String textContent){
        Essay essay = new Essay();
        System.out.println(title+textContent);
        essay.setTitle(title);
        essay.setTextContent(textContent);
        essayService.insert(essay);
        return "sesses";
    }

}
