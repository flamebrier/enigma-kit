FROM openjdk:11.0.10-jre
COPY target/enigma.jar enigma.jar
COPY src/main/resources/static/* src/main/resources/static/
COPY src/main/resources/static/img/ava/nophoto.png src/main/resources/static/img/ava/nophoto.png
COPY startscript.sh startscript.sh
RUN chmod +rx startscript.sh
CMD ["./startscript.sh"]
#ENTRYPOINT ["java", "-jar", "/enigma.jar"]
