package com.sevenXnetworks.treasure.rest.task;

import com.sevenXnetworks.treasure.rest.interfaces.SyncDataIF;
import com.sevenXnetworks.treasure.rest.response.DataRes;
import com.sevenXnetworks.treasure.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/21 15:17
 * @Version 1.0
 */
@Component
public class DataTask {
    @Autowired
    private SyncService syncService;

    @Autowired
    @Qualifier("syncDataIF")
    private SyncDataIF syncDataIF;

    @Scheduled(cron = " 0 0 3 * * ?")
    public void sync() {
        String url = "https://dt.9hou.me/westmpdev/1/game/queryConfig";
        DataRes dataRes = syncDataIF.get(url);
        System.out.println(dataRes);
        syncService.syncData(dataRes);
    }
}
