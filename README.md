<p align="center">
    <a href="https://lets-blade.com"><img src="https://raw.githubusercontent.com/EricTao2/images-repository/master/aegis/u%3D3539311380%2C3414701668%26fm%3D27%26gp%3D0.jpg" width="650"/></a>
</p>
<p align="center">Based on <code>Java8</code> + <code>Spring Boot</code> to create <b>Http Request</b> interception and verification framework 😋</p>
<p align="center">Spend <b>10 minutes</b> to learn it to do something, it will help you check Http Request parameters.</p>
<p align="center">It can defend <b>replay attack</b>, check <b>parameters are modified(digital signature)</b>, check <b>request is timed out</b></p>
<p align="center">
    🐾 <a href="#quick-start" target="_blank">Quick Start</a> |
    🌚
    🇨🇳 <a href="README_CN.md">简体中文</a>
</p>
<p align="center">
</a>
    <a href="#"><img src="https://img.shields.io/maven-central/v/com.bladejava/blade-mvc.svg?style=flat-square"></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
</p>

***

## What Is Aegis?

`Aegis` is a pursuit of simple framework, so that uses the latest technology, intercepts every http request,  then checks the parameters, and you can change every check strategy if you wanted.
If you like to try something interesting, I believe you will love it.
If you think this item is good can [star](https://github.com/EricTao2/aegis) support it :blush:

## Quick Start

Run with `Maven`：

Create a basic `Maven` project

```xml
<dependency>
    <groupId>com.github.com.erictao2</groupId>
    <artifactId>aegis-api</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

> You must use Spirng Boot in you porject.

Then add annotation in you `Controller` or `Method`, please refer to the documentation for specific annotation usage!

+ [Aegis Demos](https://github.com/lets-blade/blade-demos)

## Contents

- [**`Aegis Annotation`**](#aegis-annotation)
    - [**`DefendModify`**](#defendmodify)
    - [**`DefendTimeout`**](#defendtimeout)
    - [**`DefendReplay`**](#defendreplay)

## Aegis Annotation
Now Aegis has 3 annotations for you to use

### DefendModify
This annotation will help you verify the signature in the request, if some one modifyed you request parameters, aegis will reject this request.let's see:
```
@RestController
// You also can use annotation in Controller class
//@DefendModify
public class DemoController {

    @GetMapping("/1")
    //@DefendModify(name ="reqSign", algorithm = "md5", key = "aegis-key")
    @DefendModify
    public String test1(){
        return "test1-demo" ;
    }
}
```
you `URL` must like this
`http://localhost:8080/1?parameterOne=oneoneone&reqSign=f4da2ed1dca4bfd481d83cfb89f12ab6`

In the URL
`reqSign` = (Use some algorithm encryption for others paramets, default is MD5 )

### DefendTimeout
This annotation will help you verify request is timed out.let's see: 
```
@RestController
// You also can use annotation in Controller class
//@DefendTimeout
public class DemoController {

    @GetMapping("/2")
    //@DefendTimeout(name ="timestamp", timeout = 1000, timeUnit = {TimeUnit.MILLISECONDS})
    @DefendTimeout
    public String test2(){
        return "test2-demo" ;
    }
}
```
you `URL` must like this
`http://localhost:8080/2?timestamp=1550653175000`

### DefendReplay
This annotation will help you defend replay attack.let's see: 
```
@RestController
// You also can use annotation in Controller class
//@DefendReplay
public class DemoController {
    @GetMapping("/3")
    //@DefendReplay(name = "reqNo", prefix = "aegisReqNo", timeout = 1000 , timeUnit = {TimeUnit.MILLISECONDS})
    @DefendReplay
    public String test3(){
        return "test3-demo" ;
    }


}
```
you `URL`must like this
`http://localhost:8080/3?ReqNo=ds1&timestamp=1533205566000`

When you send a request with parameter of `reqNo`, and Other request already send same `reqNo`, if the time difference(`timestamp`) between the two requests is less than the set time(`timeout` in the annotation), last request will be rejected.

## Contact

- Mail: 823222209@qq.com

## Licenses

Please see [Apache License](LICENSE)
