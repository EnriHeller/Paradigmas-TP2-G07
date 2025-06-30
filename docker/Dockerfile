FROM maven:3.8.4-openjdk-17-slim as base

# Install necessary packages for headless testing
RUN apt-get update && apt-get install -y \
    xvfb \
    libgtk-3-0 \
    libxss1 \
    libasound2 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy pom.xml first for better Docker layer caching
COPY pom.xml ./

# Download dependencies (this will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Set environment variables for headless testing
ENV DISPLAY=:99
ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true -Dtestfx.robot=glass -Dtestfx.headless=true -Dprism.order=sw -Dprism.verbose=true"

# Start Xvfb and run tests
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 & sleep 2 && mvn clean test"]
