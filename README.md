# messagebus  [![](https://jitpack.io/v/aliletter/messagebus.svg)](https://jitpack.io/#aliletter/messagebus)
MessageBus is a android framework for message transporting , for example , activity and activity , activity and fragment , so on .
# How to
To get a Git project into your build:
## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
## Step 2. Add the dependency

	dependencies {
	        compile 'com.github.aliletter:messagebus:v1.0.4'
	}
  
# Instructions
## Register messagebus where you will recieved message , such as:
```Java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        MessageBus.getBus().register(this);
        ...
    }

```
## Unregister messagebus when you need't recieving message , such as:
```Java
    @Override
    protected void onDestroy() {
        MessageBus.getBus().unregister(this);
        super.onDestroy();
    }
```
## Send meaage in anywhere.
```Java
  MessageBus.getBus().send(Object);//Object can be a class that carries message
```

## Recieve message in the method you will.
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
