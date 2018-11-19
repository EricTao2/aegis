package com.erictao.aegis.aegisdemo;

import com.erictao.aegis.api.annotation.DefendModify;
import com.erictao.aegis.api.annotation.DefendTimeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TJX
 * @Title: DemoController
 * @date 2018/7/2713:30
 */
@RestController
@DefendModify
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/1")
    /*@DefendReplay
    @DefendTimeout*/
    @DefendModify
    public String index(){

        demoService.test("123", "321");
        return "get-demo" ;
    }

    @PostMapping("/2")
    //@DefendReplay
    @DefendTimeout
    @DefendModify
    public String post(){

        demoService.test("123", "321");
        return "post-demo" ;
    }


}
