#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true -U
docker login -u 16602309566 -p directxjy921 hub.c.163.com
docker build -t hub.c.163.com/gzeic080/eureka .
docker push hub.c.163.com/gzeic080/eureka

