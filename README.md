# webflux-kotlin-nuxt-app [![Build Status](https://travis-ci.org/daggerok/webflux-kotlin-nuxt-app.svg?branch=master)](https://travis-ci.org/daggerok/webflux-kotlin-nuxt-app)
Spring WebFlux reactive non-blocking Backend + Nuxt.js Ionic Frontend

## branches

* [material-design-icons-iconfont npm package](https://jossef.github.io/material-design-icons-iconfont/)
* [using ionic](https://github.com/daggerok/webflux-kotlin-nuxt-app/tree/ionic)
* [using vuetify](https://github.com/daggerok/webflux-kotlin-nuxt-app/tree/master)

## build and run

```bash
./gradlew bootRun
```

open http://127.0.0.1:8080/ in iPhone or Android

## test jar

```bash
java -jar build/libs/*.jar
http :8080/
http :8080/not-found
http :8080/api/message 
http --stream :8080/api/messages 
```

## resources

* https://github.com/maoberlehner/vuex-map-fields
* https://chunkbytes.com/2019/02/v-model-with-vuex/
* https://github.com/vuejs/vuex/issues/1037
* [Nuxt.js Vuetify module](https://github.com/nuxt-community/vuetify-module)
* [Vuetify icons setup](https://vuetifyjs.com/ru/customization/icons)
* [Nuxt.js web font loader](https://github.com/Developmint/nuxt-webfontloader)
* [Issue: Malformed markup: Attribute "..." appears more than once in element](https://github.com/thymeleaf/thymeleaf/issues/572)
* [webpack split-chunks plugin](https://webpack.js.org/plugins/split-chunks-plugin/)
* [Nuxt.js split chunks max size configuration.](https://github.com/nuxt/nuxt.js/issues/2201)
* https://github.com/daggerok/typescript-ionic-nuxt-app
* https://github.com/daggerok/spring-boot-nuxt-spa
* https://github.com/daggerok/vue-ionic-example
* https://github.com/daggerok/ionic-nuxt-app
* https://github.com/daggerok/nuxt-examples
* https://github.com/daggerok/vue-examples
* [WebFlux CORS](https://stackoverflow.com/a/49571895/1490636)
* [Vue for index](https://stackoverflow.com/a/52658488/1490636)
* https://ionicons.com/usage
* [Nuxt.js ionicons](https://www.youtube.com/watch?v=1TxCcWgoZbQ)
* https://github.com/daggerok/gitignore-idea-runConfigurations
* https://stackoverflow.com/questions/47631243/spring-5-reactive-webexceptionhandler-is-not-getting-called
* https://docs.spring.io/spring-boot/docs/2.0.0.M7/reference/html/boot-features-developing-web-applications.html#boot-features-webflux-error-handling
* https://jar-download.com/artifacts/org.thymeleaf/thymeleaf-spring5/3.0.10.RELEASE/source-code/org/thymeleaf/spring5/view/reactive/ThymeleafReactiveViewResolver.java
* https://github.com/reactor/reactor-kotlin-extensions
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/gradle-plugin/reference/html/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/languages.html#coroutines)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
