FROM amazoncorretto:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecommerce-product-service-2.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-Xmx512M", "-jar", "/ecommerce-product-service-2.jar"]