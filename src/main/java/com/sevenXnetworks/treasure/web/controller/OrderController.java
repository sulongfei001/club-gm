package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.service.OrderService;
import com.sevenXnetworks.treasure.vo.UserDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author sulongfei
 * @Date 18-11-9 下午1:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/userData", method = RequestMethod.GET)
    public ResponseEntity<Object> userData() {
        List<UserDataVo> userDataVos = orderService.userData();
        return ResponseEntity.ok(userDataVos);
    }
}
