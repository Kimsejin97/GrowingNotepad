#!/bin/bash

# Wait for MySQL to start
until docker exec mysql mysqladmin ping -h localhost --silent; do
    echo 'waiting for mysql...'
    sleep 1
done

echo 'mysql started'

# Start Spring Boot app
docker-compose up spring-boot-app
