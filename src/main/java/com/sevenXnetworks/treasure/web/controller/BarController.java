package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.bean.BarBean;
import com.sevenXnetworks.treasure.bean.StatisticsBean;
import com.sevenXnetworks.treasure.rest.task.DataTask;
import com.sevenXnetworks.treasure.service.BarService;
import com.sevenXnetworks.treasure.vo.BarStatisticsVo;
import com.sevenXnetworks.treasure.vo.BarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/bar")
public class BarController extends BaseController {
    @Autowired
    private BarService barService;
    @Autowired
    private DataTask dataTask;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> list() {
        List<BarVo> list = barService.list();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public ResponseEntity<Object> passList() {
        List<BarVo> list = barService.passList();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody BarBean barBean) {
        verifyBean(barBean);
        barService.create(barBean);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable long id) {
        BarVo vo = barService.get(id);
        return ResponseEntity.ok(vo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody BarBean barBean) {
        verifyBean(barBean);
        barBean.setId(id);
        barService.update(barBean);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable long id) {
        barService.delete(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/examine/{result}/{barIds}", method = RequestMethod.PUT)
    public ResponseEntity<Object> batchExamine(@PathVariable Byte result, @PathVariable Long[] barIds) {
        barService.batchExamine(result, Arrays.asList(barIds));
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.PATCH)
    public ResponseEntity<Object> statistics(@RequestBody StatisticsBean bean) {
        List<BarStatisticsVo> statisticsVos = barService.dayStatistics(bean);
        return ResponseEntity.ok(statisticsVos);
    }

    @RequestMapping(value = "/syncData", method = RequestMethod.PATCH)
    public ResponseEntity<Object> syncData() {
        dataTask.sync();
        return ResponseEntity.ok(null);
    }

    private void verifyBean(BarBean barBean) {

    }
}
