#!/bin/bash

docker rm -f mongo
docker rm -f kibana
docker rm -f elasticsearch

docker run -d --name mongo -p 27017:27017 mongo
docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.1
docker run -d --name=kibana --link=elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.10.1

