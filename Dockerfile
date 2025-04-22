FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем сборку jar файла (если используешь gradle)
COPY build/libs/*.jar app.jar

EXPOSE 8081

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
