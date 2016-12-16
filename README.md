# KeepCpuAwakeDemo
# 电话唤醒CPU机制

为什么要写这套机制
----------
Andorid 平台上，手机接收推送通知的送达率不能得到保障，原因如下：
不同与IPhone平台的统一推送策略，Andorid平台的每个应用通常都需要维护自己的一条与自己服务器的Socket长连接，推送消息通过这条Socket来传递。由于移动设备的网络环境错综复杂，并不能保证这条Socket长连接能够稳定不断，所以服务器在利用Socket推送通知之前，都需要移动设备通过心跳机制进对移动设备进行判断，（比如说移动设备需要和服务器每隔1分钟通信一次（就是所谓的心跳），如果超过3分钟没有通信则判断移动设备处于离线，取消推送）。移动设备通过Socket与服务器进行通讯，是需要CPU来完成的操作。然而CPU是移动设备的耗电大户，一般手机在熄屏后选择将CPU调节至睡眠状态，而频繁的Socket联网操作会阻碍CPU进入睡眠。这就导致了手机耗电量迅速增加的情况。
针对这种耗电情况，各个手机厂商对自己定制的Android系统ROM都有相对应的定制。比如MIUI，在手机熄屏后，会强制CPU进入睡眠状态，原计划的Socket心跳比如每分钟与服务器通信一次，则会被延迟。直到某个底层或系统级应用需要联网操作时才会唤醒CPU，而我们自己的应用则没有这样唤醒CPU的权限。
