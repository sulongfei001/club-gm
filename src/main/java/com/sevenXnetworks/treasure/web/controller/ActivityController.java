package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.bean.ActivityBean;
import com.sevenXnetworks.treasure.service.ActivityService;
import com.sevenXnetworks.treasure.vo.ActivityConfigVo;
import com.sevenXnetworks.treasure.vo.ActivityVo;
import com.sevenXnetworks.treasure.vo.HistoryActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/31 15:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> list() {
        List<ActivityVo> list = activityService.list();
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody ActivityBean activityBean) {
        verifyBean(activityBean);
        activityService.create(activityBean);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable long id) {
        ActivityVo vo = activityService.get(id);
        return ResponseEntity.ok(vo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody ActivityBean activityBean) {
        verifyBean(activityBean);
        activityBean.setId(id);
        activityService.update(activityBean);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable long id) {
        activityService.delete(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}/onSale", method = RequestMethod.PUT)
    public ResponseEntity<Object> onSale(@PathVariable long id, @RequestParam Byte onSale) {
        activityService.onSale(id, onSale);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{id}/config", method = RequestMethod.GET)
    public ResponseEntity<Object> config(@PathVariable long id) {
        ActivityConfigVo vo = activityService.config(id);
        return ResponseEntity.ok(vo);
    }

    @RequestMapping(value = "/{id}/config", method = RequestMethod.PUT)
    public ResponseEntity<Object> configWin(@PathVariable long id, @RequestParam String cellPhone) {
        activityService.configWin(id, cellPhone);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ResponseEntity<Object> history() {
        List<HistoryActivityVo> activityVos = activityService.history();
        return ResponseEntity.ok(activityVos);
    }

    private void verifyBean(ActivityBean activityBean) {
    }
}
