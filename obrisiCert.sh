#----------------BRISANJE-STARIH---------------
rm /home/vula/Desktop/sep/ssl/frontkp.key
rm /home/vula/Desktop/sep/ssl/frontkp.crt
rm /home/vula/Desktop/sep/ssl/frontkp.cer
rm /home/vula/Desktop/sep/ssl/identity.jks
rm /home/vula/Desktop/sep/ssl/keystore.p12

rm /home/vula/Desktop/sep/ncfrontend/frontnc.key
rm /home/vula/Desktop/sep/ncfrontend/frontnc.crt
rm /home/vula/Desktop/sep/ncfrontend/frontnc.cer
rm /home/vula/Desktop/sep/ncfrontend/identity.jks
rm /home/vula/Desktop/sep/ncfrontend/keystore.p12

rm /home/vula/Desktop/sep/ncbackend/src/main/resources/identity.jks
rm /home/vula/Desktop/sep/ncbackend/src/main/resources/truststore.jks
rm /home/vula/Desktop/sep/ncbackend/src/main/resources/nc.cer

rm /home/vula/Desktop/sep/zuul-service/src/main/resources/identity.jks
rm /home/vula/Desktop/sep/zuul-service/src/main/resources/truststore.jks
rm /home/vula/Desktop/sep/zuul-service/src/main/resources/kp.cer

rm /home/vula/Desktop/sep/bankfrontend/frontkp.key
rm /home/vula/Desktop/sep/bankfrontend/frontkp.crt

rm /home/vula/Desktop/sep/paypalfrontend/frontkp.key
rm /home/vula/Desktop/sep/paypalfrontend/frontkp.crt

rm /home/vula/Desktop/sep/bitcoinfrontend/frontkp.key
rm /home/vula/Desktop/sep/bitcoinfrontend/frontkp.crt

rm /home/vula/Desktop/sep/kpfrontend/frontkp.key
rm /home/vula/Desktop/sep/kpfrontend/frontkp.crt

certutil -D -d sql:$HOME/.pki/nssdb -n frontkp
certutil -D -d sql:$HOME/.pki/nssdb -n frontnc


