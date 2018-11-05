# Spring Boot 2 + Docker Compose + MariaDB + Persistence
Test controller available at `http://localhost:9090/` with basic info:

```
[uuuu-MM-dd'T'HH:mm:ss.SSS] container_id : port 
[2018-11-02T11:27:40.370] af0dc15f20a5 : 8080
```

Database data `http://localhost:9090/customers`

##Docker Compose
Portainer UI (optional):
- https://portainer.io/install.html
```cmd
docker volume create portainer_data
docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
```

Docker commands:
```cmd
docker-compose up
docker-compose down
```

- Error starting userland proxy?
  - Restart docker
- Shared drive error?
  - Allow to share your drive in `Docker/Settings/Shared Drives`
- MariaDB failed to start?
  - Try to recreate `./mariadb` dir, run `docker system prune` to clean up all volumes and run again

##Standalone
Maven build command:
```cmd
mvn clean install dockerfile:build
```

Note if you want to push image to the docker add to your `~/.m2/settings.xml`:
```xml
<servers>
  <server>
    <id>docker.io</id>
    <username>PLACE_HOLDER</username>
    <password>PLACE_HOLDER</password>
  </server>
</servers>
```
And to the `.pom` file in `dockerfile-maven-plugin`:
```xml
<configuration>
  <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
</configuration>

```

Docker commands:
```cmd
docker run -p 9090:8080 -t PLACE_HOLDER/docker-compose:1.0.3-SNAPSHOT
```