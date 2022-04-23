# FeignClientExample


> FeignClient 예제

### FeignClientExample clone repository
```shell
$ git clone https://github.com/PCloud63514/FeignClientExample.git {project-path}
$ cd project-path
$ git remote remove origin
```
## 테스트 방법
### 1. oh-lot api server 실행하기
> Java 11 must be installed.
```shell
$ ./gradlew applications:app-ohlot:bootRun
```

### swagger 3.0
> can configure the port from [applications/app-ohlot/src/resources/application.yml](https://github.com/PCloud63514/webapp-ohlot/blob/main/applications/app-ohlot/src/main/resources/application.yml)
>
> [localhost:8888/swagger-ui/index.html](http://localhost:8888/swagger-ui/index.html)
