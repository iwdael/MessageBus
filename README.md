# MessageBus  [![](https://jitpack.io/v/blackchopper/messagebus.svg)](https://jitpack.io/#blackchopper/messagebus)
MessageBus can send messages anywhere and receive messages anywhere. [中文文档](https://github.com/blackchopper/messagebus/blob/master/README_CHINESE.md)
## Instruction
MessageBus must be registered where the message is received and not registered when it is not necessary to accept the message. Otherwise, a memory leak will result.
### Code Sample
Register messagebus where you will recieved message , such as:
```Java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        MessageBus.getBus().register(this);
        ...
    }

```
Unregister messagebus when you need't recieving message , such as:
```Java
    @Override
    protected void onDestroy() {
        MessageBus.getBus().unregister(this);
        super.onDestroy();
    }
```
Send meaage in anywhere.
```Java
  MessageBus.getBus().send(Test test);//Object can be a class that carries message
```

Recieve message in the method you will.
```Java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        MessageBus.getBus().register(this);
        ...
    }
    
    //ThreadMode has four menbers. the method will run in different thread if you set it.
    @Subscribe(ThreadMode.MAINTHREAD)
    public void recived(Test test) {  // the name of the method will decome what you want 
        String id = test.getId();
        String name = test.getUsername();
    }

    @Override
    protected void onDestroy() {
        MessageBus.getBus().unregister(this);
        super.onDestroy();
    }
```


## How to
To get a Git project into your build:
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories.[click here for details](https://github.com/blackchopper/CarouselBanner/blob/master/root_build.gradle.png)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### Step 2. Add the dependency
Add it in your application module build.gradle at the end of dependencies where you want to use.   [click here for details](https://github.com/blackchopper/CarouselBanner/blob/master/application_build.gradle.png)
```Java
	dependencies {
	  ...
          compile 'com.github.blackchopper:messagebus:v1.0.6'
	}
```	
<br><br><br>
## Thank you for your browsing
If you have any questions, please join the QQ group. I will do my best to answer it for you. Welcome to star and fork this repository, alse follow me.
<br>
![Image Text](https://github.com/blackchopper/CarouselBanner/blob/master/qq_group.png)

 
