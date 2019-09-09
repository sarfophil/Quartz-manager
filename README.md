# Quartz-manager
A Demo Kafka with Quartz and Spring

## Quick Start

After executing the following command, the application will start on `localhost:8080`

- Using maven

```bash
> mvnw clean spring-boot:run
```

- Using docker:

```bash
> docker-compose up -d
```

## Features
**CREATE**  
Method      : `POST: /schedule?job=jobName&job=2019-08-24T00:00:00.000`  
Status      : `202: Accepted`  

