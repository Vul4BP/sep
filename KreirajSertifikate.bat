keytool -genkeypair -keyalg RSA -keysize 3072 -alias hakan -dname "CN=Altindag,OU=Development,O=Hakan,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1,IP:192.168.56.101" -validity 3650 -keystore C:/Users/Stevko/IdeaProjects/sep/zuul-service/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12
keytool -genkeypair -keyalg RSA -keysize 3072 -alias suleyman -dname "CN=Altindag,OU=Development,O=Suleyman,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1,IP:192.168.56.101" -validity 3650 -keystore C:/Users/Stevko/IdeaProjects/sep/client_app/src/main/resources/identity.jks -storepass secret -keypass secret -deststoretype pkcs12
keytool -exportcert -keystore C:/Users/Stevko/IdeaProjects/sep/client_app/src/main/resources/identity.jks -storepass secret -alias suleyman -rfc -file C:/Users/Stevko/IdeaProjects/sep/client_app/src/main/resources/client.cer
keytool -exportcert -keystore C:/Users/Stevko/IdeaProjects/sep/zuul-service/src/main/resources/identity.jks -storepass secret -alias hakan -rfc -file C:/Users/Stevko/IdeaProjects/sep/zuul-service/src/main/resources/server.cer
keytool -keystore C:/Users/Stevko/IdeaProjects/sep/client_app/src/main/resources/truststore.jks -importcert -file C:/Users/Stevko/IdeaProjects/sep/zuul-service/src/main/resources/server.cer -alias hakan -storepass secret
keytool -keystore C:/Users/Stevko/IdeaProjects/sep/zuul-service/src/main/resources/truststore.jks -importcert -file C:/Users/Stevko/IdeaProjects/sep/client_app/src/main/resources/client.cer -alias suleyman -storepass secret


