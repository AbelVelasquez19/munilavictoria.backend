# ================================
# Etapa 1: Compilar con Gradle
# ================================
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copiar lo mínimo para cachear dependencias
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN ./gradlew --no-daemon build -x test || true

# Copiar el código fuente y compilar
COPY src src
RUN ./gradlew --no-daemon clean bootJar -x test

# ================================
# Etapa 2: Ejecutar
# ================================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar el JAR generado
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer puerto
EXPOSE 8081

# Variables de entorno opcionales
ENV JAVA_OPTS="-Dfile.encoding=UTF-8 -Duser.timezone=America/Lima"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
