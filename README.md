## How to run this?

przydatne komendy:

Z folderu backend:
docker build -t myorg/myapp .
docker run -p 8080:8080 myorg/myapp

-----------------------------------

docker run -ti --entrypoint /bin/sh myorg/myapp
 //czysty alpine z openjdk15
docker ps
 //wyświetl działające kontenery
docker exec -ti MNEMONIC_FROM_DOCKER_PS /bin/sh
 // Otwórz interpreter ash w kontenerze
docker container ls -a
 // wyświetl wszystkie kontenery

docker kill $(docker ps -a -q)
 //wyłącz wszystkie kontenery (linux)
docker rm $(docker ps -a -q)
 //usuń wszystkie kontenery (linux)
 
 @echo off
FOR /f "tokens=*" %%i IN ('docker ps -aq') DO docker rm %%i
FOR /f "tokens=*" %%i IN ('docker images --format "{{.ID}}"') DO docker rmi %%i
//usuń kontenery w jako skrypt .bat

http://localhost:8080/actuator/health
 //get request zwróci status

Backend should start on http://localhost:8080


