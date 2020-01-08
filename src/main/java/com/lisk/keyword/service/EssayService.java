package com.lisk.keyword.service;

import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.util.Page;

import java.util.List;

public interface EssayService {
    List<Essay> listEssays(Page page);
}
