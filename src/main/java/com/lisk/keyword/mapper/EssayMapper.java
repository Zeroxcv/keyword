package com.lisk.keyword.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EssayMapper extends BaseMapper<Essay> {
    List<Essay> listEssays(Page page);
    List<Essay> findAll();
    List<Essay> queryAllByLabel(String label);

    Integer getTotal(Essay essay);

    List<Essay> getEssayListPage(Essay essay);
}
