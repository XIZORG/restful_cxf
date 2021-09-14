./stop-docker.sh
cd ../
mvn clean package
sudo docker build -t user-mysql .
sudo docker rm user-mysql
sudo docker container start mysql-standalone
sudo docker run -d -p 8089:8089 --name user-mysql --link mysql-standalone:mysql user-mysql
cd docker-scripts
./stop-docker.sh 
