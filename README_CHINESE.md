# messagebus     [![](https://img.shields.io/badge/author-aliletter-green.svg)](https://github.com/aliletters) [![](https://jitpack.io/v/aliletter/messagebus.svg)](https://jitpack.io/#aliletter/messagebus)[![](https://img.shields.io/travis/rust-lang/rust.svg)]()
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
## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。[点击查看详情](https://github.com/aliletter/CarouselBanner/blob/master/root_build.gradle.png)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### Step 2. 添加依赖
合并以下代码到需要使用的application Module的dependencies尾。[点击查看详情](https://github.com/aliletter/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
	  ...
          compile 'com.github.aliletter:messagebus:v1.0.5'
	}
```	
<br><br><br>
## 感谢浏览
如果你有任何疑问，请加入QQ群，我将竭诚为你解答。欢迎Star和Fork本仓库，当然也欢迎你关注我。
<br>
![Image Text](https://github.com/aliletter/CarouselBanner/blob/master/qq_group.png)
