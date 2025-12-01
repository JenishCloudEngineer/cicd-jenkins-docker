#!/bin/bash
set -e

echo "Adding user to docker group..."
sudo groupadd -f docker
sudo usermod -aG docker $USER

echo "Run this now:"
echo "  newgrp docker"
echo "Start Jenkins:"
echo "  docker compose up -d"

