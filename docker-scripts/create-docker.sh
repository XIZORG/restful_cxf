cd ../
mvn clean package
sudo docker build -t user-mysql .
sudo docker pull mysql:5.6
sudo docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=Ktjyfhlj2002 -e MYSQL_DATABASE=test -e MYSQL_PASSWORD=Ktjyfhlj2002 -d mysql:5.6
sleep 15
sudo docker run -d -p 8089:8089 --name user-mysql --link mysql-standalone:mysql user-mysql