# mongosearch
Example project using MongoDB as database and ElasticSearch as Search engine with SpringBoot

## To regenarate the project just use the Spring Initializr URL bellow.
[Spring Initializr URL](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.4.1.RELEASE&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=mongosearch&name=mongosearch&description=Demo%20project%20using%20Spring%20Boot%2C%20MongoDB%20and%20ElasticSearch&packageName=com.example.mongosearch&dependencies=devtools,web,thymeleaf,data-mongodb,data-elasticsearch)

## To execute both MongoDB and ElasticSearch on your local machine with docker execute the commands bellow.

### Executes ElasticSearch
docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.1

### Executes Kibana (Optional)
Kibana is not necessary, you'll just use it if you want a better way to manage Elasticsaarch
docker run --name=kibana --link=elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.10.1

### Executes MongoDB
docker run -d --name mongo -p 27017:27017 mongo
