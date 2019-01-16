package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.bean.GoodsBean;
import com.sevenXnetworks.treasure.service.GoodsService;
import com.sevenXnetworks.treasure.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 16:50
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> list() {
        List<GoodsVo> list = goodsService.list();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable long id) {
        GoodsVo vo = goodsService.get(id);
        return ResponseEntity.ok(vo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody GoodsBean goodsBean) {
        goodsBean.setId(id);
        goodsService.update(goodsBean);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable long id) {
        goodsService.delete(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/examine/{result}/{ids}",method = RequestMethod.PUT)
    public ResponseEntity<Object> batchExamine(@PathVariable Byte result, @PathVariable Long[] ids) {
        goodsService.batchExamine(result, Arrays.asList(ids));
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public ResponseEntity<Object> passList() {
        List<GoodsVo> list = goodsService.passList();
        return ResponseEntity.ok(list);
    }
}
