package com.sevenXnetworks.treasure.rest.interfaces;

import com.sevenXnetworks.treasure.rest.response.DataRes;
import org.springframework.stereotype.Component;


@Component("syncDataIF")
public class SyncDataIF extends AbstractSyncIF<DataRes> {
    public DataRes get(String url) {
        return super.post(url, null, DataRes.class);
    }
}
