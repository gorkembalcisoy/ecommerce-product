FROM amazoncorretto:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecommerce-product-module.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-Xmx512M", "-jar", "/ecommerce-product-module.jar"]