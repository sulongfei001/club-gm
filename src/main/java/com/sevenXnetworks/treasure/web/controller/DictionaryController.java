package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.bean.GlobalDictionaryBean;
import com.sevenXnetworks.treasure.service.DictionaryService;
import com.sevenXnetworks.treasure.vo.GlobalDictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dictionaries")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> list() {
        List<GlobalDictionaryVo> list = dictionaryService.list();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/{dicId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable long dicId, @RequestBody GlobalDictionaryBean dictionaryBean) {
        dictionaryBean.setId(dicId);
        dictionaryService.update(dictionaryBean);
        return ResponseEntity.ok(null);
    }
}
