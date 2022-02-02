# MIBQuiz2021

Anbei finden Sie die wichtigsten Informationen zum Repository der **Gruppe O** (*Patterns and Frameworks* im WiSe
2021/22).

## Entwickler-Team:

* [Andreas Kissmehl](https://git.mylab.th-luebeck.de/andreas.kissmehl)
* [Jan Steinke](https://git.mylab.th-luebeck.de/jan.steinke)
* [Thomas Krohnfuß](https://git.mylab.th-luebeck.de/BSG2000)

## Projektbeschreibung

Im Rahmen der Aufgabenstellung haben wir uns entschieden ein **Multiplayer-Quiz** umzusetzen. Dabei handelt es sich um
eine Client-Server-Anwendung, welche in Java geschrieben ist. Neben dem Java-Spiel-Client gibt es eine Web-App, welche
in JavaScript geschrieben ist. Es ist möglich den Java-Client gegen die Web-App spielen zu lassen.

### Verwendete Frameworks und Technologien

Es folgt eine Auswahl der wichtigsten Frameworks und Technologien, welche jedoch nicht vollständig ist.

* **Server-App:** REST API ist mit Spring Boot (Spring-Framework) umgesetzt, JPA Hibernate, javax servlet, lombok, JWT (
  JSON Web Token)
* **Java-Spiel-Client:** JavaFX, lombok ... <-- @Jan Bitte ergänzen @ToDo
* **JavaScript-Spiel-Client:** Bootstrap, jQuery

### Tech-Stack

* **Container:** Docker
* **Datenbank:** MariaDB (Docker)
* **WebServer:** nginx für den JavaScript Client (Docker), Tomcat für die Spring REST API
* **Entwicklungsumgebung:** IntelliJ IDEA 2021.3 (Ultimate Edition)
* **Build-Werkzeug:** MAVEN
* **API Testwerkzeug:** Postman mit Team Workspace
* **DB-Administration:** Adminer (Docker)
* **Versionierung und Ticketing:** Gitlab der THL und New Relic (CodeStream)
* **Videokonferenzen:** MS-Teams

## Spielablauf

![Hauptfenster der Client-App](doc/img/Spielablauf_Uebersicht.png)

## Ordnerstruktur

Das Repository besteht aus folgenden Ordnern (hier sind nur die wichtigsten genannt):

    📦 Reop
    ┣ 📂 .mvn                Maven Wrapper
    ┣ 📂 doc                 Die ausgearbeitete Dokumentation und zugehörige Dateien.
    ┃ ┣ 📂 EA                Unterordner mit Daten zur Einsendeaufgabe
    ┃ ┣ 📂 Javadoc           Unterordner mit Javadoc
    ┃ ┣ 📂 img               Unterordner mit Bildern
    ┃ ┣ 📂 java_client       Unterordner mit Doku zur Client-App
    ┃ ┣ 📂 mockups           Unterordner mit ersten Entwürfen der Client-App
    ┃ ┣ 📂 notes             Unterordner mit Besprechungsnotizen
    ┃ ┣ 📂 postman           Unterordner für Postman Vorlage
    ┣ 📂 lib                 Für die Anwendung benötigte Libraries
    ┣ 📂 META-INF            Für die Anwendung benötigte Einstellungen
    ┣ 📂 src                 Unterordner für Source und die docker-compose.yml
    ┃ ┣ 📂 main              Hauptentwicklungszweig
    ┃ ┃ ┣ 📂 java            Daten der Java Anwendungen
    ┃ ┃ ┃ ┗ 📂 de.gruppeo
    ┃ ┃ ┃ ┃ ┣ 📂 wise2122_java_client
    ┃ ┃ ┃ ┃ ┗ 📂 wise2122_java_server
    ┃ ┃ ┃ ┃ ┃ ┣ 📂 docker.mysql
    ┃ ┃ ┃ ┃ ┃ ┃ ┗📜 data.sql                 <- SQL Script mit Beispieldaten (Fragen, Kategorien, zwei Spieler)
    ┃ ┃ ┃ ┃ ┃ ┗ 📜 MibquizzzApplication.java <- corsConfiguration
    ┃ ┃ ┣ 📂 jswebclient                     <-- Daten der Webapp
    ┃ ┃ ┗ 📂 resources
    ┣ 📂 target
    ┣ 📜 CONTRIBUTING.md
    ┣ 📜 LICENSE
    ┣ 📜 lombok.config
    ┣ 📜 pom.xml
    ┗ 📜 README.md

## Build-Prozess und Docker

1. Repo mit `git` in die IDE klonen - wir empfehlen **IntelliJ**.
2. In der `pom.xml` im Root Ordner befinden sich die dependencies - diese einmal mit **MAVEN** laden.
3. Unter `src/docker-compose.yml` befindet sich ein **Docker** Compose File. Bitte einmal die Images laden und starten.
   Hier ist vor allem der **MariDB** Container entscheidend, da sonst das DBMS fehlt. Einstellungen sollten möglichst
   nicht verändert werden. Das Datenbank-Schema wird vom Server per **Hibernate** erstellt.
4. Unter `src/main/resources/application.properties` befinden sich wichtige **Einstellungen der Server-API**. Unter
   anderem der Datenbankport (per default `5555`) und der Server Port (per default `8080`).
5. Unter `src/main/java/de/gruppeo/wise2122_java_server/docker/mysql/data.sql` können Beispieldaten (Kategorien und
   Fragen) importiert werden. Ohne diese Daten kann das _MIBQuizzz_ nicht sinnvoll ausprobiert werden.
6. Der Server wird unter `src/main/java/de/gruppeo/wise2122_java_server/MibquizzzApplication.java` gestartet -
   alternativ mit `.\mvnw.cmd spring-boot:run` vom Terminal aus starten. Nun sollte man die **REST API** bereits mit _
   Postman_ abfragen können.
7. Der Java Spiele Client wird unter `src/main/java/de/gruppeo/wise2122_java_client/Start.java` gestartet. Es kann sein,
   dass die Run-Configuration in der IDE folgendermaßen ergänzt werden muss (VM
   Options) `--module-path $ProjectFileDir$\lib\javafx-sdk-17.0.1\lib --add-modules javafx.controls,javafx.fxml,javafx.web`
   . Es können auch die MAVEN Libs genutzt werden.
8. Um mehrere Java Clients gegeneinander spielen lassen zu können, muss in der IDE Run Configuration die
   Option `Allow multiple Instances` aktiviert werden.
9. Der JavaScript Spiele Client ist erreichbar, wenn der **nginx** Docker Container läuft. Die Konfiguration zur
   Pfadangabe befindet sich im Docker Compose File (siehe oben `- ./main/jswebclient:/usr/share/nginx/html/`).
10. Falls der Browser **CORS** Fehler produziert, bitte prüfen, ob in
    der `src/main/java/de/gruppeo/wise2122_java_server/MibquizzzApplication.java`
    unter `.allowedOrigins("http://localhost")` die Adresse und Port des JS-Clients korrekt ist. Wenn man `index.html`
    direkt aus der IDE startet, dann wird normal ein Port angehangen. Dieser muss dann im zuvor genannten Eintrag
    ergänzt werden. Diese Konfiguration verhindert Cross Site Requests und man gibt explizit an, wer Anfragen an die
    REST-API stellen darf.