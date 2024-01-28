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
    implementation 'com.github.artbits:picasso:1.0.3'
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
    <version>1.0.3</version>
</dependency>
```

## Usage
```java
File file1 = new File("material/cp-bg.png");
File file2 = new File("material/cp-frame.png");
String avatar1 = "https://avatars.githubusercontent.com/u/109500550?v=4";
String avatar2 = "https://avatars.githubusercontent.com/u/14957082?v=4";
String name1 = "Microsoft";
String name2 = "OpenAI";
Font font = new Font("consolas", Font.ITALIC, 16);

Picasso.init(file1)
        .addImage(avatar1, o -> o.location(85, 84).width(86).height(86).radius(86))
        .addImage(file2, o -> o.location(85, 75))
        .addText(name1, o -> o.location(85, 175).font(font).color(Color.magenta))
        .addImage(avatar2, o -> o.location(590, 84).width(86).height(86).radius(86))
        .addImage(file2, o -> o.location(590, 75))
        .addText(name2, o -> o.location(590, 175).font(font).color(Color.magenta))
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