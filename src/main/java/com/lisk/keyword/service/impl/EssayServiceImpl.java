package com.lisk.keyword.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lisk.keyword.mapper.EssayMapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.service.EssayService;
import com.lisk.keyword.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayServiceImpl extends ServiceImpl<EssayMapper, Essay> implements EssayService {
    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<Essay> listEssays(Page page) {
        return essayMapper.listEssays(page);
    }

    @Override
    public List<Essay> findAll() {
        return essayMapper.findAll();
    }

    @Override
    public List<Essay> queryAllByLabel(String label) {
        return essayMapper.queryAllByLabel(label);
    }


}
