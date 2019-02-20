package com.erictao.aegis.api.localcache;

import com.erictao.aegis.api.properties.AegisApiProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author TJX
 * @Title: CacheMap
 * @date 2018/7/2919:51
 */
@Data
public class CacheSet extends ConcurrentHashMap<String, Long>  {

    private Long timeout;

    private Long addCount = 0L;

    private Long cleanThreshold = 1000L;

    private ExecutorService singleThreadExecutor = new ThreadPoolExecutor(1
            , 1
            , 0L
            , TimeUnit.MILLISECONDS
            , new LinkedBlockingQueue<Runnable>(1)
            , new ThreadPoolExecutor.AbortPolicy());

    public CacheSet(Long timeout) {
        super();
        this.timeout = timeout;
    }

    public void add(String key) {
        super.putIfAbsent(key, System.currentTimeMillis());
        addCount++;
        if (addCount >= cleanThreshold) {
            CacheSet cacheSet = this;
            singleThreadExecutor.execute(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Iterator<Entry<String, Long>> it = cacheSet.entrySet().iterator();
                         while (it.hasNext()) {
                             Entry<String, Long> entry = it.next();
                             if (System.currentTimeMillis() - entry.getValue() > timeout) {
                                 it.remove();
                             }
                         }
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
            });
        }
    }
    public boolean contains(String key) {
        Long v = super.get(key);
        if (v == null || System.currentTimeMillis() - v > timeout) {
            super.remove(key);
            return false;
        } else {
            return true;
        }
    }
}
