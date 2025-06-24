#!/bin/bash
set -e

# Start virtual display for headless JavaFX testing
Xvfb :99 -screen 0 1024x768x24 &
export DISPLAY=:99

mvn clean test
