# MessageBus
[![](https://img.shields.io/badge/platform-android-orange.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/language-java-yellow.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/JCenter-1.0.8-brightgreen.svg)](http://jcenter.bintray.com/com/hacknife/messagebus/) [![](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/license-apache--2.0-green.svg)](https://github.com/hacknife) [![](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/hacknife)<br/><br/>
MessageBus能在任何地方发送消息，在任何地方接受消息。
## 使用说明
在接受消息的地方必须注册MessageBus,不需要接受消息的时候反注册，否则会导致内存泄漏。
### 代码示例
需要接收消息的地方注册MessageBus。
```Java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        MessageBus.getBus().register(this);
        ...
    }

```
不需要接收消息的时候反注册MessageBus。
```Java
    @Override
    protected void onDestroy() {
        MessageBus.getBus().unregister(this);
        super.onDestroy();
    }
```
在任意地方发送消息
```Java
  MessageBus.getBus().send(Test test);//Object can be a class that carries message
```
根据参数类型接收消息
```Java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        MessageBus.getBus().register(this);
        ...
    }
    
    //ThreadMode用来设置此方法运行的线程
    @Subscribe(ThreadMode.MAINTHREAD)
    public void recived(Test test) {  
        String id = test.getId();
        String name = test.getUsername();
    }

    @Override
    protected void onDestroy() {
        MessageBus.getBus().unregister(this);
        super.onDestroy();
    }
```
## 快速引入项目
合并以下代码到需要使用Module的dependencies中
```Java
	dependencies {
	  ...
          compile 'com.hacknife:messagebus:1.0.8'
	}
```	
<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/hacknife/CarouselBanner/blob/master/qq_group.png)
