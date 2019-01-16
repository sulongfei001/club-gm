package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.bean.GlobalDictionaryBean;
import com.sevenXnetworks.treasure.dao.GlobalDictionaryDao;
import com.sevenXnetworks.treasure.entity.GlobalDictionaryEntity;
import com.sevenXnetworks.treasure.exception.ResourceException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.model.Validator;
import com.sevenXnetworks.treasure.service.DictionaryService;
import com.sevenXnetworks.treasure.vo.ConfigVo;
import com.sevenXnetworks.treasure.vo.GlobalDictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private GlobalDictionaryDao dictionaryDao;

    @Override
    public List<GlobalDictionaryVo> list() {
        List<GlobalDictionaryEntity> entityList = dictionaryDao.findAll();
        List<GlobalDictionaryVo> vos = new ArrayList<>();
        entityList.forEach(entity -> {
            vos.add(toVo(entity));
        });
        return vos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(GlobalDictionaryBean dictionaryBean) {
        GlobalDictionaryEntity entity = dictionaryDao.findOne(dictionaryBean.getId());
        Validator.notNull(entity, ResourceException.error(CustomerErrorConstants.DICTIONARY_NOT_EXIST));
        entity.setKey(dictionaryBean.getKey());
        entity.setValue(dictionaryBean.getValue());
        entity.setRemark(dictionaryBean.getRemark());
        dictionaryDao.saveOrUpdate(entity);
    }

    private GlobalDictionaryVo toVo(GlobalDictionaryEntity entity) {
        GlobalDictionaryVo vo = new GlobalDictionaryVo();
        vo.setId(entity.getId());
        vo.setKey(entity.getKey());
        vo.setValue(entity.getValue());
        vo.setRemark(entity.getRemark());
        return vo;
    }
}
