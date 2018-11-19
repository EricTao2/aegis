package com.erictao.aegis.aegisdemo;

import org.springframework.stereotype.Service;

/**
 * @author TJX
 * @Title: DemoService
 * @date 2018/7/2716:52
 */
@Service
public class DemoService {


    public String test(String one, String two) {
        return "test" + one + two;
    }
}
