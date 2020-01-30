

#importuj sertifikat u browser
pk12util -d sql:$HOME/.pki/nssdb -i /home/$1/Desktop/sep/ncfrontend/keystore.p12

#importuj sertifikat u browser
pk12util -d sql:$HOME/.pki/nssdb -i /home/$1/Desktop/sep/ssl/keystore.p12
