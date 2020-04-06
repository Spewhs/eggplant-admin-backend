## Run with docker

docker build -t eggplant-back .

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

docker run -it -p 8080:8080 --name back eggplant-back
