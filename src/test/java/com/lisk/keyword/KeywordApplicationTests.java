package com.lisk.keyword;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.service.LabelService;
import com.lisk.keyword.service.impl.GitHubLookupService;
import com.lisk.keyword.util.Ltp;
import lombok.SneakyThrows;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.codec.EncodingException;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@SpringBootTest
class KeywordApplicationTests {

    @Autowired
    GitHubLookupService gitHubLookupService;
    //文章
    @Autowired
    EssayService essayService;

    @Autowired
    LabelService labelService;
    @Test
    void contextLoads() {
        for (int i = 0; i < 20; i++) {
            gitHubLookupService.executeAysncTask1(i);
        }
    }

    @Test
    void testAllData() throws Exception {
        List<Essay> list = essayService.findAll();
        DataConversion(list);
    }
    @Transactional
    public void DataConversion(List<Essay> list) throws Exception {

        List<JSONArray> keywordLists = new LinkedList<>();
        Map<String, Object> map = new HashMap<>();
        List<Essay> essayList = new LinkedList<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            Essay essay = list.get(i);
            String eText = essay.getTextContent().replaceAll("\n", "");
            Future<String> future=  gitHubLookupService.keywordData(eText, essay);
            futures.add(future);
//            keywordLists.add(jsonArray);
//            essayList.add(essay);

        }
        List<String> response = new ArrayList<>();

        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        System.out.println(response.toString());
//        for (int i = 0;i < keywordLists.size(); i++){
//            JSONArray jsonArray = keywordLists.get(i);
//            Essay essay = essayList.get(i);
//            try {
//            for (int j = 0; j < jsonArray.size(); j++) {
//
//                double score = Double.valueOf(jsonArray.getJSONObject(j).getString("score"));
//                if (score >= 0.560) {
//                    EntityWrapper ew = new EntityWrapper();
//                    ew.setSqlSelect("id,label_name,pdarticle_id");
//                    ew.eq("label_name", jsonArray.getJSONObject(j).getString("word"));
//                    List<Label> labelLists = labelService.selectList(ew);
//                    if (labelLists.size() > 0) {
//
//                        for (int k = 0; k < labelLists.size(); k++) {
//                            //关系表
//                            labelService.insertPdarticleLabel(labelLists.get(k).getId(), essay.getId(), score);
//                        }
//                    } else {
//                        Label label = new Label();
//                        label.setPdarticleId(essay.getId());
//                        label.setLabelName(jsonArray.getJSONObject(j).getString("word"));
//                        labelService.insert(label);
//                        //关系表
//                        labelService.insertPdarticleLabel(label.getId(), essay.getId(), score);
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
//        }


}
    @Test
    public void TestThread(){
        List<Essay> lists = essayService.findAll();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        for (Essay essay : lists) {


        }
    }
}
