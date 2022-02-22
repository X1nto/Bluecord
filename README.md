# Bluecord - Open Source Edition
Unofficial open source version of Bluecord, because Blue still haven't figured out how to put code on github.

![](https://i.imgur.com/E25H08S.png)

Version: 2.1 Beta 2 - based on Discord 113.9

### Disclaimer
This is just a joke, if you want a normal discord mod that's open source - use [Aliucord](https://github.com/Aliucord/Aliucord).

### How to build
1. Grab 113.9 Discord apk (for example from [here](https://www.apkmirror.com/wp-content/themes/APKMirror/download.php?id=3178486))
2. Download custom version of [apktool](https://f001.backblazeb2.com/file/avepub/apktool-cli-all.jar) with `--no-dummy` PR
3. Decompile Discord using `java -jar apktool.jar d com.discord-113009.apk --no-dummy`
4. `cd com.discord-113009` and apply patch using `patch -p1 -i ../bluecord.patch`
5. Copy `res` dir from this repo to decompiled Discord 
6. Move `smali/z` to `smali_classes3/z` and `smali_classes2/com/esotericsoftware` to `smali_classes3/com/esotericsoftware`
7. Build apk using `java -jar apktool.jar b`
8. Build custom Bluecord dex using `./gradlew Bluecord:make`
9. Copy `Bluecord/build/Injector.dex` to built apk as `classes4.dex`
10. Sign apk using apksigner
11. Done
