# spring-boot2-graylog
Spring boot Microservices logging with Graylog

# Overview
Graylog is a powerful platform that allows for easy log management of both structured and unstructured data along with debugging applications. It is based on Elasticsearch, MongoDB, and Scala. Graylog has a main server, which receives data from its clients installed on different servers, and a web interface, which visualizes the data and allows to work with logs aggregated by the main server. 
[View Documentation](http://docs.graylog.org/en/3.1/index.html).

# Prerequisites
* JAVA >= 8
* [Maven](http://maven.apache.org/download.cgi)
* Git
* [Docker](https://www.docker.com/)

# How to Use
1. Clone project
2. Run docker-compose file `docker-compose up -d` (We are using docker compose to easily setup a graylog instance with data persistent support)
3. Run `mvn spring-boot:run` from project root

  ## Sending application logs (Spring boot application)
  
  We used Logback-gelf which is used here to generate logs in Graylog.

There is a detailed introduction to using logback-gelf on [here](https://github.com/osiegmar/logback-gelf).

* Add a logback-gelf dependency in the pom file

```xml
<dependency>
    <groupId>de.siegmar</groupId>
	  <artifactId>logback-gelf</artifactId>
	  <version>2.0.0</version>
</dependency>
```
* logback.xml file
```xml
<configuration>


    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%green(%date) %highlight(%-5level) %yellow([%-4relative]) %magenta([%thread]) %cyan(%logger{10}) %gray([%file:%line]) %blue(: %msg%n)</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="GELF" class="de.siegmar.logbackgelf.GelfUdpAppender">
        <graylogHost>127.0.0.1</graylogHost>
        <graylogPort>12201</graylogPort>
        <maxChunkSize>508</maxChunkSize>
        <useCompression>true</useCompression>
        <encoder class="de.siegmar.logbackgelf.GelfEncoder">
            <originHost>127.0.0.1</originHost>
            <includeRawMessage>false</includeRawMessage>
            <includeMarker>true</includeMarker>
            <includeMdcData>true</includeMdcData>
            <includeCallerData>false</includeCallerData>
            <includeRootCauseData>false</includeRootCauseData>
            <includeLevelName>false</includeLevelName>
            <shortPatternLayout class="ch.qos.logback.classic.PatternLayout">
                <pattern>%m%nopex</pattern>
            </shortPatternLayout>
            <fullPatternLayout class="ch.qos.logback.classic.PatternLayout">
                <pattern>%m%n</pattern>
            </fullPatternLayout>
            <staticField>app_name:backend</staticField>
            <staticField>os_arch:${os.arch}</staticField>
            <staticField>os_name:${os.name}</staticField>
            <staticField>os_version:${os.version}</staticField>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>

    <logger name="com.logging.springboot2graylog">
        <appender-ref ref="MAIN_LOG_FILE" />
        <appender-ref ref="GELF" />
    </logger>

    <logger name="com.logging.springboot2graylog.interceptor.RestControllerInterceptor" additivity="false">
        <level value="DEBUG"/>
        <appender-ref ref="GELF" />
    </logger>


</configuration>
```

## Receiving logs (Graylog server)

Access to the graylog server in the browser http://ip:9000.

To send the data to Graylog, you must therefore configure an entry. This will tell Graylog to accept the log messages.

Go to the Web interface -> System -> Entries and select "GELF UDP" and click on "Launch a new entry", then you will see the below screen.

![capture 1](https://github.com/anicetkeric/spring-boot2-graylog/blob/master/screens/input.PNG)


![capture 2](https://github.com/anicetkeric/spring-boot2-graylog/blob/master/screens/udp%20input.PNG)


![capture 3](https://github.com/anicetkeric/spring-boot2-graylog/blob/master/screens/result.PNG)

# Resources
* [Graylog documentation](https://docs.graylog.org/en/3.2/)
* [logback-gelf github](https://github.com/osiegmar/logback-gelf).
