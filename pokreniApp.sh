#-------------------------PODIZANJE-NC-BACKENDA-----------------------

#gnome-terminal --working-directory=/home/$1/Desktop/sep/ncbackend --command="mvn spring-boot:run"

#--------------------------------BANKA---------------------------------

#gnome-terminal --working-directory=/home/$1/Desktop/sep/BankEntityBackend --command="mvn spring-boot:run"

#gnome-terminal --working-directory=/home/$1/Desktop/sep/BankEntityFrontend/Banka --command="npm start"

#-------------------------PODIZANJE-KP-BACKENDA-----------------------

#EUREKE
gnome-terminal --working-directory=/home/$1/Desktop/sep/eureka-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer1"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/eureka-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer2"
sleep 10;

#ZUULOVI
gnome-terminal --working-directory=/home/$1/Desktop/sep/zuul-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer1"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/zuul-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer2"
sleep 10;

#MIKROSERVISI
#gnome-terminal --working-directory=/home/$1/Desktop/sep/sellerservice --command="mvn spring-boot:run"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/paypalservice --command="mvn spring-boot:run"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/bitcoinservice --command="mvn spring-boot:run"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/bankservice --command="mvn spring-boot:run"
sleep 10;
#-------------------------PODIZANJE-NC-FRONTA-----------------------

#gnome-terminal --working-directory=/home/$1/Desktop/sep/ncfrontend --command="npm start"

#-------------------------PODIZANJE-KP-FRONTOVA-----------------------

#gnome-terminal --working-directory=/home/$1/Desktop/sep/kpfrontend --command="npm start"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/paypalfrontend --command="npm start"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/bitcoinfrontend --command="npm start"
#gnome-terminal --working-directory=/home/$1/Desktop/sep/bankfrontend --command="npm start"

