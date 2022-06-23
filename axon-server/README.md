https://docs.axoniq.io/reference-guide/axon-server/installation/docker-k8s/axon-server-se

docker run -it --rm --name axonserver -p 8024:8024 -p 8124:8124 -v `pwd`data:/axonserver/data -v `pwd`events:/axonserver/events -v `pwd`config:/axonserver/config axoniq/axonserver:latest-dev

curl -s http://localhost:8024/actuator/info

