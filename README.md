# AllRun-APP
基于Asmack框架和xUtils框架的聊天，运动社交安卓混编app

实现功能：
1.注册登陆业务
2.添加好友
3.俱乐部群聊，添加好友私聊功能，聊天包括发送表情，文字，地点定位，语音
4.运动圈中可以发送分享话题，图片，定位，类似朋友圈
5.运动模块可以记录运动时跑步的速度时间以及gps定位。
6.用web前端编程实现了商城界面。

项目技术：
1.使用ThinkAndroid，xUtils框架，使用httpUtils进行断点下载，使用BitmapUtils进行异步图片加载，使用xUtils中的PauseOnScrollListener实现
lisview快速滑动时不加载图片，使用Asmack聊天框架进行收发消息，实现服务器主动推送信息给客户端。
2.自定义Viewgroup，自定义ListView下拉刷新，使用了多个自定义View如SlidingMenu，GitView，CirlcleImageView，JustifyTextView。
3.Memory Analyzer Tools检查内存泄漏，hierarchy View检查绘制每个控件所用的时间。
4，使用gradle自动打包。
5.使用了单例，工厂设计，IOC，观察者等设计模式。
6.聊天内容使用NDK进行加密，解密，通过wireshark获取网卡上数据包，查看加密效果。
7.使用webView加载能自适应手机屏幕的网页，实现在线订购功能。
8.自己开发服务器上的servlet，存放最新的apk版本信息，客户端通过联网得到Json，获取最新版本号，进行软件升级。
9.发图片时用Base64将byte[]转成字符串，收到后再用Base64进行解码。发语音时用MediaRecorder进行录音，存到sd卡上再用MediaPlayer进行播放。
10.登陆时没有直接发送密码，而是发送密码的md5串。
11.地图开发使用Baidu地图开发sdk，通过LocationClient进行地图定位。
12.在自定义的Adapter中用到了ViewHolder，notifyDatasetChanged。全部使用相对布局，能自适应不同的设备，用到了ListView，ScrollView，popupWindow，Dialog，mapView等控件。为了适应不同屏幕字号定义在dimens.xml中。使用drwa9patch.bat制作.9图片。为每个Button使用了selector，使用了style，theme。
