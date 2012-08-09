Web-sovelluksen käyttöönotto OpenShift-pilvessä
-----------------------------------------------
1. Asenna tietokoneelle OpenShift asiakasohjelma
sudo gem install rhc
2. Luo oma namespace
rhc domain create -n <namespace>
3. Kopio verkkosovellus uuteen hakemistoon ja luo uusi Git repository
git init
4. Luo uusi OpenShift sovellus
rhc app create -a sensors -t diy-0.1 --nogit
5. Lisää OpenShift sovelluksen palvelin Gitin versionhallinta palvelimeksi
git remote add origin ssh://<uuid>@sensors-<namespace>.rhcloud.com/~/git/sensors.git/
git pull -s recursive -X theirs origin master
6. Lisää sovellukselle MySQL-tietokanta
rhc app cartridge add -a sensors -c mysql-5.1
7. Lisää luotuun Git repoon paikalliset tiedostot
git add .
8. Tallenna muutokset paikalliseen repoon
git commit -m "initial deploy"
9. Hae Githubista Play frameworkin OpenShift-konfiguraatio
git remote add quickstart -m master https://github.com/opensas/play2-openshift-quickstart.git
git pull -s recursive -X theirs quickstart master
10. Käännä web-sovellus
play clean compile stage
11. Lisää conf/openshift.conf-tiedostoon:
	db.default.driver=com.mysql.jdbc.Driver
	db.default.url="jdbc:mysql://"${OPENSHIFT_DB_HOST}":"${OPENSHIFT_DB_PORT}/${OPENSHIFT_APP_NAME}
	db.default.user=${OPENSHIFT_DB_USERNAME}
	db.default.password=${OPENSHIFT_DB_PASSWORD}
12. Lisää uudet tiedostot paikalliseen repoon
git add .
13. Tallenna muutokset paikalliseen repoon
git commit -am "deploying"
14. Työnnä verkkosovellus pilveen
git push origin
15. Varmista sovelluksen toiminta
rhc app tail -a sensors
