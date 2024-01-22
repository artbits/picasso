[![](https://www.jitpack.io/v/artbits/picasso.svg)](https://www.jitpack.io/#artbits/picasso)
[![](https://img.shields.io/badge/JDK-8%20%2B-%23DD964D)](https://jdk.java.net/)
[![](https://img.shields.io/badge/license-Apache--2.0-%234377BF)](#license)


## Picasso
``Picasso`` is a Java image processing library. Just provide the image material and set relevant parameters to quickly generate the target image. Features: Image composition, cropping, scaling, rounded corners, transparency, quality compression.


## Download
Gradle:
```groovy
repositories {
    maven { url 'https://www.jitpack.io' }
}

dependencies {
    implementation 'com.github.artbits:picasso:1.0.2'
}
```
Maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.artbits</groupId>
    <artifactId>picasso</artifactId>
    <version>1.0.2</version>
</dependency>
```

## Usage
```java
Picasso.init(new File("material/cp-bg.png"))
        .addImage("https://avatars.githubusercontent.com/u/109500550?v=4", o -> {
            o.x = 85;
            o.y = 84;
            o.width = 86;
            o.height = 86;
            o.radius = 86;
        })
        .addImage(new File("material/cp-frame.png"), o -> {
            o.x = 85;
            o.y = 75;
        })
        .addText("Microsoft", o -> {
            o.x = 85;
            o.y = 175;
            o.color = Color.magenta;
            o.font = new Font("consolas", Font.ITALIC, 16);
        })
        .addImage("https://avatars.githubusercontent.com/u/14957082?v=4", o -> {
            o.x = 590;
            o.y = 84;
            o.width = 86;
            o.height = 86;
            o.radius = 86;
        })
        .addImage(new File("material/cp-frame.png"), o -> {
            o.x = 590;
            o.y = 75;
        })
        .addText("OpenAI", o -> {
            o.x = 590;
            o.y = 175;
            o.color = Color.magenta;
            o.font = new Font("consolas", Font.ITALIC, 16);
        })
        .toFile("output/cp-" + System.currentTimeMillis() + ".png");
```



## Display
|                          |                       |                                |
|:------------------------:|:---------------------:|:------------------------------:|
|cp-avatar-frame           |cp-background          |cp-banner                       |
|![](material/cp-frame.png)|![](material/cp-bg.png)|![](output/cp-1705756703722.png)|



## License
```
Copyright 2024 Zhang Guanhu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```