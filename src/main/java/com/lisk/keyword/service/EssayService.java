package com.lisk.keyword.service;

import com.baomidou.mybatisplus.service.IService;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.util.Page;

import java.util.List;

public interface EssayService extends IService<Essay> {
    List<Essay> listEssays(Page page);
    List<Essay> findAll();
    List<Essay> queryAllByLabel(String label);
}
