# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM adoptopenjdk:11.0.6_10-jre-hotspot

COPY target/covid19-followup-app-*.jar /app/covid19-followup-app.jar
COPY target/lib/* /app/lib/
COPY src/scripts/start.sh /app/start.sh
WORKDIR "/app"
CMD ["/bin/sh","start.sh"]
