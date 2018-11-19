package com.erictao.aegis.api.localcache;

import lombok.Data;

import java.util.HashMap;

/**
 * @author TJX
 * @Title: CacheMap
 * @date 2018/7/2919:51
 */
@Data
public class CacheMap extends HashMap<String, Long> {

    private Long timeout;

    public CacheMap(Long timeout) {
        super();
        this.timeout = timeout;
    }

    public boolean isExits(String key) {
        Long v = super.get(key);
        if (v == null || System.currentTimeMillis() - v > timeout) {
            super.remove(key);
            return false;
        } else {
            return true;
        }
    }
}
