# 如何用java自带的jconsole连idea

1. 启动idea项目的时候要在vm options加上配置

    ```
    -Djava.rmi.server.hostname=127.0.0.1
    -Dcom.sun.management.jmxremote
    -Dcom.sun.management.jmxremote.port=11911
    -Dcom.sun.management.jmxremote.ssl=false
    -Dcom.sun.management.jmxremote.authenticate=false
    ```

2. jconsole使用远程连接模式，即：127.0.0.1:11911



