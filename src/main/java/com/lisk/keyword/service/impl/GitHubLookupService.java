package com.lisk.keyword.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.service.LabelService;
import com.lisk.keyword.util.Ltp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;


@Service
public class GitHubLookupService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);
    @Autowired
    EssayService essayService;

    @Autowired
    LabelService labelService;

    @Async("taskExecutor")
    public void executeAysncTask1(Integer i){
        logger.info("CustomMultiThreadingService ==> executeAysncTask1 method: 执行异步任务{} ", i);

    }

    @Async("taskExecutor")
    public Future<String> keywordData(String eText, Essay essay) throws UnsupportedEncodingException {
        logger.info("CustomMultiThreadingService ==> executeAysncTask1 method: 执行异步任务{} ", eText);
        String keywordData = Ltp.getLtpData(eText);
//        if (!keywordData.isEmpty())
//            System.out.println(keywordData);
        try {
            // 这个方法需要调用500毫秒

            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息汇总
        return new AsyncResult<>(String.format(keywordData));

//            if (keywordData.isEmpty())
//                return null;
//            //解析返回的JSON数据
//            JSONObject jsonObject = JSONObject.fromObject(keywordData);
//            if (jsonObject.isEmpty())
//                return null;
//            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ke");
//            return jsonArray;
//        return null;

    }
}
