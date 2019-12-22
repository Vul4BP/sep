#----------------DEO-ZA-KP-I-NC----------------

#kreiraj ceret za kp
keytool -genkeypair -keyalg RSA -keysize 3072 -alias kp -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=KP,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#kreiraj cert za nc
keytool -genkeypair -keyalg RSA -keysize 3072 -alias nc -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=NC,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/client_app/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#izvuci nc.cer iz identity.jks od nc
keytool -exportcert -keystore /home/vula/Desktop/sep/client_app/src/main/resources/identity.jks -storepass secret -alias nc -rfc -file /home/vula/Desktop/sep/client_app/src/main/resources/nc.cer

#izvuci kp.cer iz identity.jks od kp
keytool -exportcert -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/identity.jks -storepass secret -alias kp -rfc -file /home/vula/Desktop/sep/zuul-service/src/main/resources/kp.cer

#ubaci u truststore od nc cert od kp 
keytool -keystore /home/vula/Desktop/sep/client_app/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/zuul-service/src/main/resources/kp.cer -alias kp -storepass secret

#ubaci u truststore od kp cert od nc
keytool -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/client_app/src/main/resources/nc.cer -alias nc -storepass secret

#--------------DEO-ZA-FRONT-----------------

#kreiraj cert za front kp
keytool -genkeypair -keyalg RSA -keysize 3072 -alias frontkp -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=FRONTKP,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/kpfrontend/ssl/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#izvuci frontkp.cer iz identity.jks od frontkp
keytool -exportcert -keystore /home/vula/Desktop/sep/kpfrontend/ssl/identity.jks -storepass secret -alias frontkp -rfc -file /home/vula/Desktop/sep/kpfrontend/ssl/frontkp.cer

#ubaci u truststore od kp cert od frontkp
keytool -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/kpfrontend/ssl/frontkp.cer -alias frontkp -storepass secret

#izvlacenje .p12 iz identity.jks od frontkp
keytool -importkeystore \
    -srckeystore /home/vula/Desktop/sep/kpfrontend/ssl/identity.jks \
    -destkeystore /home/vula/Desktop/sep/kpfrontend/ssl/keystore.p12 \
    -deststoretype PKCS12 \
    -srcalias frontkp \
    -deststorepass secret \
    -destkeypass secret

#izvlacenje frontkp.crt
openssl pkcs12 -in /home/vula/Desktop/sep/kpfrontend/ssl/keystore.p12 -nokeys -out /home/vula/Desktop/sep/kpfrontend/ssl/frontkp.crt

#izvlacenje frontkp.key
openssl pkcs12 -in /home/vula/Desktop/sep/kpfrontend/ssl/keystore.p12 -nocerts -nodes -out /home/vula/Desktop/sep/kpfrontend/ssl/frontkp.key

#importuj sertifikat u browser
pk12util -d sql:$HOME/.pki/nssdb -i /home/vula/Desktop/sep/kpfrontend/ssl/keystore.p12
