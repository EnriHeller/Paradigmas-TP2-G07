#!/bin/bash
set -e

# Check if running in GitHub Actions
if [ "$CI" = "true" ]; then
    echo "Running in CI environment"
    # GitHub Actions ya tiene display virtual configurado
    export DISPLAY=:99
    # Configuración más ligera para CI
    export MAVEN_OPTS="-Xmx1g -XX:MaxMetaspaceSize=256m"
else
    echo "Running locally"
    # Start virtual display for headless JavaFX testing
    Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 &
    export DISPLAY=:99
    # Wait for Xvfb to start
    sleep 2
    # Configuración más generosa para local
    export MAVEN_OPTS="-Xmx2g -XX:MaxMetaspaceSize=512m"
fi

# Set JavaFX headless properties
export JAVA_TOOL_OPTIONS="-Djava.awt.headless=true -Dtestfx.robot=glass -Dtestfx.headless=true -Dprism.order=sw"

# Run tests with less verbose output
mvn clean test -q