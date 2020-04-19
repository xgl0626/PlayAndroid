# playandroid
wanandroid
# 玩Android-徐国林-2019212381

图标

![avatar](https://github.com/xgl0626/playandroid/blob/master/app/src/main/tb-playstore.png)

欢迎界面

![avatar](https://github.com/xgl0626/playandroid/blob/master/app/src/main/res/drawable/welcomeimg.png)

#### 玩andorid功能介绍

1.首先制作了图标，欢迎界面图片：欢迎界面通过handler延时机制。

2.主页采用viewpager+fragments搭建，底部导航栏采用bottomnavgation（偶然间发现这是一个好用的东西）支持点击变色。

3.主页实现banner自定义，文章recyclerview支持上拉下滑刷新，点击item进入webview网页文章细节。

4.主页点击一个搜索进入搜索页面，此处实现了搜索作者昵称，流氏布局搜索热词，添加了常用网站信息。

5.主页点击头像或者右滑进入navgation个人中心页面，自定义圆形头像，点击后可打开图库和相机。但是没有实现照片持久化。对于我的收藏由于用登录接口就没有实现，提问item是打算接入一个图灵机器人玩但是还没有实现。然后就注销登录。

#### 技术分析（自己的学到的新技术）

mvp架构模式（由于登录注册借用的上次本地登录就没有改mvp模式。）

自定义banner，通过学习网上的一个demo，理解了banner原理。

自定义view圆形头像，自定义流氏布局tag，最近在学自定义view所以看了网上demo后来自己理解了一遍。

封装log，toast，bitmap，okhttp请求类

掌握了新的刷新技巧通过监听recyclerview。

命名规则基本刻意去规范

分包简捷

#### 问题：

webview加载网页比较缓慢。

代码不够高效简捷，感觉自己也看了一些设计模式，builder，单例，观察者，工厂，策略模式等但是好像不知道什么时候用（希望学长可以指导指导）

感觉有时候看人家的项目代码会有一些base类，这次自己也学了一点点，但是感觉没学会，希望可以可以讲讲。

#### 作业感想

这是我自己真正意义上完成的第一个项目，曾在寒假也做了一个项目，但是由于好多东西都没学会并且在看别人的代码，花了一个月时间才独立完成逼乎app，记得当时用http请求网络+json解析踩了好多坑在掌握，但是确实学到了好多东西。

**学到了许多**

开发第二次项目用学会了okhttp请求+gson运用，了解了很多坑，

对于回调接口概念更深刻。

mvp与mvc，mvvm这几种模式也理解了，

对于recyclerview理解深刻。

debug能力也增强了

由于是第二次开发项目也没啥值得骄傲的，感觉还是很辣鸡。

**然后由于时间问题在开发项目的同时也在学android新的技术，就没有做出完整的玩android，应该是自己写代码太慢了。**



