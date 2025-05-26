# /bin/bash
set -e

mvn test-compile org.pitest:pitest-maven:mutationCoverage
