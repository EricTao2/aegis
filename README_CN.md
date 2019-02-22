<p align="center">
    <a href="https://lets-blade.com"><img src="https://raw.githubusercontent.com/EricTao2/images-repository/master/aegis/u%3D3539311380%2C3414701668%26fm%3D27%26gp%3D0.jpg" width="650"/></a>
</p>
<p align="center">基于<code>Java8</code> + <code>Spring Boot</code> 创建的 <b>Http Request</b> 拦截和校验框架😋</p>
<p align="center">花费 <b>10 分钟</b> 来学习用它做点东西，他能帮你检查并校验你的Http请求的参数.</p>
<p align="center">它能防御<b>重放攻击</b>, 检查 <b>参数是否被修改（数字签名）</b>, 校验 <b>请求是否超时</b></p>
<p align="center">
    🐾 <a href="#quick-start" target="_blank">快速开始</a> |
    🌚 
    🇨🇳 <a href="README_CN.md">简体中文</a>
</p>
<p align="center">
</a>
    <a href="#"><img src="https://img.shields.io/maven-central/v/com.bladejava/blade-mvc.svg?style=flat-square"></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
</p>

***

## Aegis 是啥?

能够给我一个赞 [star](https://github.com/EricTao2/aegis)来支持我:blush:

## <span id="quick-start">快速开始</span>

使用Maven创建一个基础的`Maven`项目，然后添加依赖：

```xml
<dependency>
    <groupId>com.github.com.erictao2</groupId>
    <artifactId>aegis-api</artifactId>
    <version>1.0.1</version>
</dependency>
```

> 你必须使用Spring Boot 来构建你的项目.

然后添加注解到你的`Controller`或`Method`上，请参考后续文档查看每个注解的作用!

+ [Aegis Demos](https://github.com/lets-blade/blade-demos)

## 框架文档

- [**`Aegis Annotation`**](#aegis-annotation)
    - [**`DefendModify`**](#defendmodify)
    - [**`DefendTimeout`**](#defendtimeout)
    - [**`DefendReplay`**](#defendreplay)

## Aegis 注解
现在`Aegis`有三个注解可以使用。

### DefendModify
这个注解能帮助你校验request里参数的数字签名，如果有人篡改过你的request参数，`Aegtis`将会拦截下该请求。附上代码：
```
@RestController
// 你也可以把注解用到Controller的类上，这样就是针对类的所有方法起作用
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
你的请求 `URL` 必须像这样：
`http://localhost:8080/1?parameterOne=oneoneone&reqSign=f4da2ed1dca4bfd481d83cfb89f12ab6`

在`URL里`
`reqSign` = (是用算法加密所有其他参数得出的结果, 默认是MD5加密 )

### DefendTimeout
这个注解能够帮你检查请求是否超时：
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
你的请求 `URL` 必须像这样：
`http://localhost:8080/2?timestamp=1550653175000`

### DefendReplay
这个注解能帮你防御重放攻击： 
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
你的请求 `URL` 必须像这样：
`http://localhost:8080/3?reqNo=ds1&timestamp=1533205566000`

当你发出一个请求时，如果该请求的`reqNo`和之前某个请求的`reqNo`相同，且两个请求的时间（`timestamp`）之差，没有超过设定的时间（`timeout`），那么这个请求将会被拦截掉。

## Contact

- Mail: 823222209@qq.com

## Licenses

Please see [Apache License](LICENSE)
