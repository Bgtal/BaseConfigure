
# BaseConfigure [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)   [![](https://jitpack.io/v/Bgtal/BaseConfigure.svg)](https://jitpack.io/#Bgtal/BaseConfigure)

> maven
```  
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

> implementation 'com.github.Bgtal:BaseConfigure:1.0.7'

## 项目依赖
* 使用的时候需要在自己的项目中引用下面的包，不然会报错
> implementation 'com.android.support:appcompat-v7:27.1.1'  
> implementation 'com.android.support:multidex:1.0.3'  
> implementation 'com.android.support:recyclerview-v7:27.1.1'
> implementation 'com.github.Bgtal:SnbUtil:0.0.8'  
> implementation 'com.github.Bgtal:SnbView:0.0.8'  
> implementation 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.5@aar'
> implementation 'io.reactivex.rxjava2:rxjava:2.1.14'

# apk
[apk下载](/app/apk/BaseConfigure.apk)

### 说明

平时的基类和一些常用的逻辑类写太多了,索性慢慢封装成包直接用

具体使用看app的代码和说明
