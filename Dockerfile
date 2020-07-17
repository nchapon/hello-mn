FROM openjdk:14-alpine
COPY target/hello-mn-*.jar hello-mn.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "hello-mn.jar"]