# fetch basic image
FROM maven:3.3.9-jdk-8

# application placed into /opt/app
RUN mkdir -p /opt/app
WORKDIR /opt/app

# linux utility for auto build code
RUN echo "deb [check-valid-until=no] http://archive.debian.org/debian jessie-backports main" > /etc/apt/sources.list.d/jessie-backports.list
RUN sed -i '/deb http:\/\/deb.debian.org\/debian jessie-updates main/d' /etc/apt/sources.list
RUN apt-get -o Acquire::Check-Valid-Until=false update && apt-get install entr -y

# selectively add the POM file and
# install dependencies
COPY pom.xml /opt/app/
RUN mvn install -DskipTests

# rest of the project
COPY src /opt/app/src
RUN mvn package -DskipTests

# local application port
EXPOSE 8080

# execute it
CMD ["mvn", "exec:java"]