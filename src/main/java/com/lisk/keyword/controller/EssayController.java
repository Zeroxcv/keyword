package com.lisk.keyword.controller;

import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/essay")
public class EssayController {

    @Autowired
    private EssayService essayService;

//    @RequestMapping(value = "/essaylist/1" ,method= RequestMethod.GET)
//    //分页查询文章
//    public List<Essay> listEssays(){
//        Page page = new Page();
//        page.setStart(1000);
//        page.setCount(Page.getDefaultCount());
//        List<Essay> list;
//        list = essayService.listEssays(page);
//        System.out.println(list.size());
//        return list;
//    }
}
