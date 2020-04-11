package com.lisk.keyword.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.repository.UserRepository;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.service.LabelService;
import com.lisk.keyword.service.UserService;
import com.lisk.keyword.util.Ltp;
import com.lisk.keyword.util.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "")
public class EssayController {

    @Autowired
    private UserRepository userRepository;

    //文章
    @Autowired
    EssayService essayService;

    @Autowired
    LabelService labelService;

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
    public List<Essay> findAll()throws Exception{
        List<Essay> list = essayService.findAll();
//        DataConversion(list);
        return list;

    }
    @Transactional
    public void DataConversion(List<Essay> list){
        for(int i = 0;i < list.size();i++){
            Essay essay = list.get(i);
            String eText = essay.getTextContent().replaceAll("\n","");

            try {


            String keywordData = Ltp.getLtpData(eText);
            if(keywordData.isEmpty())
                    break;
                //解析返回的JSON数据
                JSONObject jsonObject = JSONObject.fromObject(keywordData);
                if(jsonObject.isEmpty())
                break;
                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ke");
                for(int j = 0; j < jsonArray.size(); j++){
                    Label label = new Label();
                    label.setPdarticleId(essay.getId());
                    label.setLabelName(jsonArray.getJSONObject(j).getString("word"));
                    EntityWrapper ew = new EntityWrapper();
                    ew.setSqlSelect("id,label_name,pdarticle_id");
                    ew.eq("label_name",label.getLabelName());
                    List<Label> labelLists = labelService.selectList(ew);
                    if(labelLists.size() > 0){

                        for(int k = 0;k < labelLists.size() ;k++){
                            //关系表
                            labelService.insertPdarticleLabel(labelLists.get(k).getId(),essay.getId());
                        }
                    }
                    else {
                        labelService.insert(label);
                            //关系表
                            labelService.insertPdarticleLabel(label.getId(),essay.getId());
                    }

                }            }
            catch (Exception e){
            }


        }
    }

    @GetMapping(value = "/essay/add")
    public String addEssay(){
        return "essay/add";
    }

    //添加文章
    @ResponseBody
    @RequestMapping(value = "/essay/addEssay" ,method=RequestMethod.POST)
    public String add(String title,String textContent) throws UnsupportedEncodingException{
        Essay essay = new Essay();
        essay.setTitle(title);
        essay.setTextContent(textContent);

        //生成日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            essay.setPublishedDate(sdf.parse(sdf.format(new Date())));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        boolean start = essayService.insert(essay);
        //提取文章中的关键词
        if (start){
            String keywordData = Ltp.getLtpData(essay.getTextContent());
            //传过来的数据可能没有
            if(keywordData.equals(""))
                return "error";
            //解析返回的JSON数据
            JSONObject jsonObject = JSONObject.fromObject(keywordData);
            if("success".equals(jsonObject.getString("desc"))){
                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ke");
                //遍历关键词，并添加到数据中
                for(int i = 0; i < jsonArray.size(); i++){
                    String keyword = jsonArray.getJSONObject(i).getString("word");
                    Label label = new Label();
                    label.setLabelName(jsonArray.getJSONObject(i).getString("word"));
                    label.setPdarticleId((essay.getId()));

                    EntityWrapper ew = new EntityWrapper();
                    ew.setSqlSelect("id,label_name,pdarticle_id");
                    ew.eq("label_name",label.getLabelName());
                    //关键词表中是否存在
                    List<Label> labelList = labelService.selectList(ew);
                    if(labelList.size() == 0){
                        labelService.insert(label);
                        labelService.insertPdarticleLabel(label.getId(),essay.getId());
                    }
                    else{
                        labelService.insertPdarticleLabel(labelList.get(0).getId(),essay.getId());
                    }


                }
                //返回成功
                return "success";
            }
            else{
                return "error";
            }
        }
        else
            return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/essay/queryContent",method = RequestMethod.GET)
    public List<Essay> queryContent(String selectRange,String selectContent) {

        if ("全文".equals(selectRange)) {
            EntityWrapper ew = new EntityWrapper();
            ew.setSqlSelect("id,text_label,title,text_content,published_date,data_sources");
            ew.like("text_content",selectContent);
            List<Essay> list = essayService.selectList(ew);
            System.out.println(list.size());
            return list;
        }
        if ("关键词".equals(selectRange)) {
            List<Essay> list = essayService.queryAllByLabel(selectContent);
            return list;
            }
        return null;
        }


}
