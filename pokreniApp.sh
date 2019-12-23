#-------------------------PODIZANJE-NC-BACKENDA-----------------------

gnome-terminal --working-directory=/home/vula/Desktop/sep/ncbackend --command="mvn spring-boot:run"

#-------------------------PODIZANJE-KP-BACKENDA-----------------------

#EUREKE
gnome-terminal --working-directory=/home/vula/Desktop/sep/eureka-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer1"
gnome-terminal --working-directory=/home/vula/Desktop/sep/eureka-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer2"
sleep 10;

#ZUULOVI
gnome-terminal --working-directory=/home/vula/Desktop/sep/zuul-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer1"
#gnome-terminal --working-directory=/home/vula/Desktop/sep/zuul-service --command="mvn spring-boot:run -Dspring-boot.run.profiles=peer2"
sleep 10;

#MIKROSERVISI
gnome-terminal --working-directory=/home/vula/Desktop/sep/sellerservice --command="mvn spring-boot:run"
gnome-terminal --working-directory=/home/vula/Desktop/sep/paypalservice --command="mvn spring-boot:run"
gnome-terminal --working-directory=/home/vula/Desktop/sep/bitcoinservice --command="mvn spring-boot:run"
sleep 10;
#-------------------------PODIZANJE-NC-FRONTA-----------------------

gnome-terminal --working-directory=/home/vula/Desktop/sep/ncfrontend --command="npm start"

#-------------------------PODIZANJE-KP-FRONTOVA-----------------------

gnome-terminal --working-directory=/home/vula/Desktop/sep/kpfrontend --command="npm start"
gnome-terminal --working-directory=/home/vula/Desktop/sep/paypalfrontend --command="npm start"
gnome-terminal --working-directory=/home/vula/Desktop/sep/bitcoinfrontend --command="npm start"
#gnome-terminal --working-directory=/home/vula/Desktop/sep/bankfrontend --command="npm start"
