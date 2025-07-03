#!/bin/bash
set -e

echo "Setting up CI environment for JavaFX testing..."

# Install dependencies for headless testing
sudo apt-get update
sudo apt-get install -y xvfb libgtk-3-0 libxss1 libasound2 libxrender1 libxtst6 libxi6

# Start virtual display
export DISPLAY=:99
Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 &

# Wait for Xvfb to start
sleep 3

# Set JavaFX properties for headless testing
export JAVA_TOOL_OPTIONS="-Djava.awt.headless=true -Dtestfx.robot=glass -Dtestfx.headless=true -Dprism.order=sw"

# Run tests
mvn clean test