docker run --rm -d --name java-glovo --network postgresnet -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -p 8080:8080 java-glovo