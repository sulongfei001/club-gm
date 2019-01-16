package com.sevenXnetworks.treasure.service;

import com.sevenXnetworks.treasure.bean.GlobalDictionaryBean;
import com.sevenXnetworks.treasure.vo.GlobalDictionaryVo;

import java.util.List;

public interface DictionaryService {
    List<GlobalDictionaryVo> list();

    void update(GlobalDictionaryBean dictionaryBean);
}
