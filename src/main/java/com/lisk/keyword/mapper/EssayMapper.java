package com.lisk.keyword.mapper;

import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EssayMapper {
    List<Essay> listEssays(Page page);
}
