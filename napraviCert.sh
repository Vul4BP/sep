#----------------DEO-ZA-KP-I-NC----------------

#kreiraj ceret za kp
keytool -genkeypair -keyalg RSA -keysize 3072 -alias kp -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=KP,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#kreiraj cert za nc
keytool -genkeypair -keyalg RSA -keysize 3072 -alias nc -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=NC,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/ncbackend/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#izvuci nc.cer iz identity.jks od nc
keytool -exportcert -keystore /home/vula/Desktop/sep/ncbackend/src/main/resources/identity.jks -storepass secret -alias nc -rfc -file /home/vula/Desktop/sep/ncbackend/src/main/resources/nc.cer

#izvuci kp.cer iz identity.jks od kp
keytool -exportcert -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/identity.jks -storepass secret -alias kp -rfc -file /home/vula/Desktop/sep/zuul-service/src/main/resources/kp.cer

#ubaci u truststore od nc cert od kp 
keytool -keystore /home/vula/Desktop/sep/ncbackend/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/zuul-service/src/main/resources/kp.cer -alias kp -storepass secret

#ubaci u truststore od kp cert od nc
keytool -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/ncbackend/src/main/resources/nc.cer -alias nc -storepass secret

#--------------DEO-ZA-NC-FRONT-----------------

#kreiraj cert za front nc
keytool -genkeypair -keyalg RSA -keysize 3072 -alias frontnc -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=FRONTNC,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/ncfrontend/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#izvuci frontnc.cer iz identity.jks od frontnc
keytool -exportcert -keystore /home/vula/Desktop/sep/ncfrontend/identity.jks -storepass secret -alias frontnc -rfc -file /home/vula/Desktop/sep/ncfrontend/frontnc.cer

#ubaci u truststore od nc cert od frontnc
keytool -keystore /home/vula/Desktop/sep/ncbackend/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/ncfrontend/frontnc.cer -alias frontnc -storepass secret

#izvlacenje .p12 iz identity.jks od nc
keytool -importkeystore \
    -srckeystore /home/vula/Desktop/sep/ncfrontend/identity.jks \
    -destkeystore /home/vula/Desktop/sep/ncfrontend/keystore.p12 \
    -deststoretype PKCS12 \
    -srcalias frontnc \
    -deststorepass secret \
    -destkeypass secret

#izvlacenje nc.crt
openssl pkcs12 -in /home/vula/Desktop/sep/ncfrontend/keystore.p12 -nokeys -out /home/vula/Desktop/sep/ncfrontend/frontnc.crt

#izvlacenje nc.key
openssl pkcs12 -in /home/vula/Desktop/sep/ncfrontend/keystore.p12 -nocerts -nodes -out /home/vula/Desktop/sep/ncfrontend/frontnc.key

#importuj sertifikat u browser
pk12util -d sql:$HOME/.pki/nssdb -i /home/vula/Desktop/sep/ncfrontend/keystore.p12

#--------------DEO-ZA-KP-FRONT-----------------

#kreiraj cert za front kp
keytool -genkeypair -keyalg RSA -keysize 3072 -alias frontkp -dname "CN=BACKAPALANKAJENAJJACA,OU=Development,O=FRONTKP,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore /home/vula/Desktop/sep/ssl/identity.jks -storepass secret -keypass secret -deststoretype pkcs12

#izvuci frontkp.cer iz identity.jks od frontkp
keytool -exportcert -keystore /home/vula/Desktop/sep/ssl/identity.jks -storepass secret -alias frontkp -rfc -file /home/vula/Desktop/sep/ssl/frontkp.cer

#ubaci u truststore od kp cert od frontkp
keytool -keystore /home/vula/Desktop/sep/zuul-service/src/main/resources/truststore.jks -importcert -file /home/vula/Desktop/sep/ssl/frontkp.cer -alias frontkp -storepass secret

#izvlacenje .p12 iz identity.jks od frontkp
keytool -importkeystore \
    -srckeystore /home/vula/Desktop/sep/ssl/identity.jks \
    -destkeystore /home/vula/Desktop/sep/ssl/keystore.p12 \
    -deststoretype PKCS12 \
    -srcalias frontkp \
    -deststorepass secret \
    -destkeypass secret

#izvlacenje frontkp.crt
openssl pkcs12 -in /home/vula/Desktop/sep/ssl/keystore.p12 -nokeys -out /home/vula/Desktop/sep/ssl/frontkp.crt

#izvlacenje frontkp.key
openssl pkcs12 -in /home/vula/Desktop/sep/ssl/keystore.p12 -nocerts -nodes -out /home/vula/Desktop/sep/ssl/frontkp.key

#importuj sertifikat u browser
pk12util -d sql:$HOME/.pki/nssdb -i /home/vula/Desktop/sep/ssl/keystore.p12

#kopiraj sertifikat u sve foldere od frnt
cp -i /home/vula/Desktop/sep/ssl/frontkp.crt /home/vula/Desktop/sep/bankfrontend
cp -i /home/vula/Desktop/sep/ssl/frontkp.key /home/vula/Desktop/sep/bankfrontend

cp -i /home/vula/Desktop/sep/ssl/frontkp.crt /home/vula/Desktop/sep/bitcoinfrontend
cp -i /home/vula/Desktop/sep/ssl/frontkp.key /home/vula/Desktop/sep/bitcoinfrontend

cp -i /home/vula/Desktop/sep/ssl/frontkp.crt /home/vula/Desktop/sep/paypalfrontend
cp -i /home/vula/Desktop/sep/ssl/frontkp.key /home/vula/Desktop/sep/paypalfrontend

cp -i /home/vula/Desktop/sep/ssl/frontkp.crt /home/vula/Desktop/sep/kpfrontend
cp -i /home/vula/Desktop/sep/ssl/frontkp.key /home/vula/Desktop/sep/kpfrontend
