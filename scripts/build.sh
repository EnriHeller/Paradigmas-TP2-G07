#!/bin/bash
set -e

# Check if running in GitHub Actions
if [ "$CI" = "true" ]; then
    echo "Running in CI environment"
    # GitHub Actions ya tiene display virtual configurado
    export DISPLAY=:99
else
    echo "Running locally"
    # Start virtual display for headless JavaFX testing
    Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 &
    export DISPLAY=:99
    # Wait for Xvfb to start
    sleep 2
fi

# Set JavaFX headless properties
export JAVA_TOOL_OPTIONS="-Djava.awt.headless=true -Dtestfx.robot=glass -Dtestfx.headless=true -Dprism.order=sw"

mvn clean test