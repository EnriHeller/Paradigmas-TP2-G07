#!/bin/bash
set -e

echo "Building and starting services..."
docker compose -f docker/docker-compose.yaml build --no-cache
docker compose -f docker/docker-compose.yaml up -d

echo "Waiting for services to be ready..."
sleep 5

echo "Service status:"
docker compose -f docker/docker-compose.yaml ps -a

echo "Service logs:"
docker compose -f docker/docker-compose.yaml logs
