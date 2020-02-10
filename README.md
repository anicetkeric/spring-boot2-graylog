# spring-boot2-graylog
Spring boot Microservices logging with Graylog

# Overview
Graylog is a powerful platform that allows for easy log management of both structured and unstructured data along with debugging applications. It is based on Elasticsearch, MongoDB, and Scala. Graylog has a main server, which receives data from its clients installed on different servers, and a web interface, which visualizes the data and allows to work with logs aggregated by the main server. 
[View Documentation](http://docs.graylog.org/en/3.1/index.html).

# Prerequisites
*	JAVA >= 8
* [Maven](http://maven.apache.org/download.cgi)
* Git
* [Docker](https://www.docker.com/)

# How to Use
1. Clone project
2. Run docker-compose file `docker-compose up -d` (We are using docker compose to easily setup a graylog instance with data persistent support)
3. Run `mvn spring-boot:run` from project root

  ## Sending application logs
  
  We used Logback-gelf which is used here to generate logs in Graylog.

There is a detailed introduction to using logback-gelf on [here](https://github.com/osiegmar/logback-gelf).
