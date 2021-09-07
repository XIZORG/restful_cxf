./stop-docker.sh 
mvn clean package
sudo docker build -t user-mysql .
sudo docker rm user-mysql
sudo docker container start 563421cd564e
sudo docker run -d -p 8089:8089 --name user-mysql --link mysql-standalone:mysql user-mysql
./stop-docker.sh 
