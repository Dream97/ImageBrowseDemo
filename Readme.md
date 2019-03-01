[toc]

### 使用效果
![示例](https://github.com/Dream97/ImageBrowseDemo/raw/master/use_result.gif)

RickBrowse是一个常用于图片浏览库，应用场景类似于微信朋友圈中九宫格图片的浏览效果，可以左右滑动浏览，放大缩小。

### 使用方法
支持两种类型图片 Url加载图片和Resource存放的图片

**引入依赖**


**Resource方式**

RickResContent作为Resource图片数据的载体
```java
                /**数据赋值**/
                RickResContent rickResContent = new RickResContent.Builder()
                        .addRes(mResList) //必选 
                        .addTiTle(mTitleList) //选填
                        .build(); //必选
  
                /**浏览图片**/    
                RickIvBrowse.with(this)
                        .content(rickResContent)
                        .position(0)
                        .start(); //必选
```

- RickResContent中的**addRes**方法支持List<String>和String两种类型，用于多图片和单张图片的传入
- RickResContent中的**addTitle**作为选填方式同样支持List<String>和String两种类型
- RickIvBrowse中position是作为浏览的位置，也就是从第几张图片作为入口位置



**Url方式**

RickUrlContent作为Url图片数据的载体
```java
                /**数据赋值**/
                RickUrlContent rickContent = new RickUrlContent.Builder()
                        .addUrl(mUrlList
                        .addTiTle(mTitleList
                        .build();
  
                /**浏览图片**/    
              RickIvBrowse.with(this)
                        .content(rickContent)
                        .position(3)
                        .start();
```

- RickUrlContent中的**addRes**方法支持List<String>和String两种类型，用于多图片和单张图片的传入
- RickUrlContent中的**addTitle**作为选填方式同样支持List<String>和String两种类型
- RickIvBrowse中position是作为浏览的位置，也就是从第几张图片作为入口位置

### RickBrowse架构解析

![架构示意图](https://github.com/Dream97/ImageBrowseDemo/raw/master/RickBrowseAnaly.png)

如图所示，已RickContent作为入口，通过RickPick进行筛选，存储到Rickspec中，然后启动Activity，在Activity中使用自定义View组件实现图片的浏览以及缩放。

关于自定义ImageView如何实现缩放 请看[等我写完]()
### 问题/异常

- 1、OOM内存溢出

这个问题常见于低版本Android设备，在AndroidManifest中的Application中关闭硬件加速，避免消耗太多内存
```xml
        android:hardwareAccelerated="false"
        android:largeHeap="true"
```

### 项目地址

github地址：https://github.com/Dream97/ImageBrowseDemo.git

欢迎Star & 指正
