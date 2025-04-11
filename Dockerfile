# Utiliser l'image OpenJDK 17 comme base
FROM openjdk:17-jdk-alpine AS build

# Répertoire de travail
WORKDIR /app
# hey

# Copier le fichier JAR généré par Maven dans l'image Docker
COPY target/pfaEtudiant-0.0.1-SNAPSHOT.jar app.jar

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exposer le port que votre application écoute
EXPOSE 8080
