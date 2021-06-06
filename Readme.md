#build
mvn clean install
#run
java -jar ./target/queue-service-0.0.1-SNAPSHOT.jar

#postman curls

Create topic

curl --location --request POST 'http://localhost:8080/v1/queue' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9EFE9EF615F71962382DD56376D69AA6' \
--data-raw '{
     "name" : "test"
}'



Create consumers

curl --location --request POST 'http://localhost:8080/v1/queue/test/consumer' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9EFE9EF615F71962382DD56376D69AA6' \
--data-raw '{
     "clientId" : "consumer-1",
     "batchSize" : 10,
     "callbackUrl" : "http://localhost:8080/callbacks"
}'



Add message

curl --location --request POST 'http://localhost:8080/v1/queue/test/message' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9EFE9EF615F71962382DD56376D69AA6' \
--data-raw '
    "hello world"
'



Delete topic

curl --location --request DELETE 'http://localhost:8080/v1/queue/test/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9EFE9EF615F71962382DD56376D69AA6' \
--data-raw ''




Delete Consumers

curl --location --request DELETE 'http://localhost:8080/v1/queue/test/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjI6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9EFE9EF615F71962382DD56376D69AA6' \
--data-raw ''



