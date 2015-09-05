# 開発するぞ、開発するぞ、開発するぞ

## セットアップ〜起動まで

必要なソフトのインストール

```
$ brew install typesafe-activator
```

プロジェクト作成

```
$ activator new scala-sawasawa scalikejdbc-activator-template
```


起動

```
$ cd scala-sawasawa
$ ./activator # sbt でも同じ
```

以下、sbt内で。

```
[scala-sawasawa] $ compile
[scala-sawasawa] $ run
```

これで、以下のURLにアクセス可能になる。赤い画面になるが、詳しくは次節で。

[http://localhost:9000/](http://localhost:9000/)

## DB関連

初回アクセスで真っ赤な画面になった原因は、DBの定義が古いため。
落ち着いて 「Apply this script now!」ボタンをクリックすると、DBに最新のマイグレーションスクリプトが適用される。
ちょっとすると画面が表示されると思う。

一旦、sbtに戻って、Ctrl + d でプロセスを一旦停止。その後 sbt 内で、

```
[scala-sawasawa] $ h2-browser
```

と実行すると、H2にアクセスするためのwebインターフェースのプロセスがバックグラウンドで起動する。
URLは以下のとおりなので、ブラウザでアクセス。

[http://192.168.56.1:8082/](http://192.168.56.1:8082/)

ログイン画面の、接続先の欄には以下のように入力する。パスは適宜読み替えること。

```
jdbc:h2:file:/path/to/scala-sawasawa/db/playapp
```

これで、DBも覗ける。

## さて開発

### テーブルの作成

conf/db/migration/default に flyway のマイグレーションファイルを作成。
こんな[感じ](https://github.com/k4200/scala-sawasawa/commit/0d7554ed84490b360b9e4bd305767b7e0b42e998)。

flyway の CLI がインストールされていれば、それを使ってDBに反映させることが出来るが、無い場合は、

* sbt で run
* ブラウザでアクセス
* 「Apply this script now!」ボタンをクリック

でしのぐ。

### コード生成

ScalikeJDBC には、DBの中身を見てコードを生成してくれる[リバースエンジニアリング](http://scalikejdbc.org/documentation/reverse-engineering.html)
の機能がある。設定は、[scalikejdbc.properties](https://github.com/k4200/scala-sawasawa/commit/d328c152d7a779ca64f00d487dfaeb2d1bef535d) を参照。

sbt 内で以下を実行すると、models の下にファイルが生成される。ファイルの中身は[こちら](https://github.com/k4200/scala-sawasawa/commit/9b74d857705baf5f10d72869de9b8e02feff8b5a)。

```
[scala-sawasawa] $ scalikejdbcGenAll
```

生成されたファイルを修正するの著しくオススメできないので、継承するなりして使うと良い。
この辺は、他のライブラリ(hibernate等)と同じ。

以上で、開発に専念できる・・・はず。

----
# Hello ScalikeJDBC!

This is a [Typesafe Activator](http://typesafe.com/platform/getstarted) template for ScalikeJDBC beginners.

http://typesafe.com/activator/template/scalikejdbc-activator-template

![screenshot](https://raw.github.com/scalikejdbc/hello-scalikejdbc/master/screenshot.png)

## ScalikeJDBC

https://github.com/scalikejdbc/scalikejdbc

A tidy SQL-based DB access library for Scala developers. This library naturally wraps JDBC APIs and provides you easy-to-use APIs.

## Play Framework 2.x

http://www.playframework.com/

Play Framework makes it easy to build web applications with Java & Scala. Play is based on a lightweight, stateless, web-friendly architecture.

Built on Akka, Play provides predictable and minimal resource consumption (CPU, memory, threads) for highly-scalable applications.

### play-flyway

https://github.com/tototoshi/play-flyway

Flyway plugin for Play2. It aims to be a substitute for play-evolutions.

### play-json4s

https://github.com/tototoshi/play-json4s

This module allows you to use json4s in your Play2 apps.

## Backbone.js

http://backbonejs.org/

Backbone.js gives structure to web applications by providing models with key-value binding and custom events, collections with a rich API of enumerable functions, views with declarative event handling, and connects it all to your existing API over a RESTful JSON interface.

## CoffeeScript

http://coffeescript.org/

CoffeeScript is a little language that compiles into JavaScript. Underneath that awkward Java-esque patina, JavaScript has always had a gorgeous heart. CoffeeScript is an attempt to expose the good parts of JavaScript in a simple way.

