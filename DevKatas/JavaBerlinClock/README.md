BerlinClockConverter

In order to run the application the  must be installed into the system.

GIT - clone the project from the GIT remote repository
Java JDK 8 - compile and run the project
Maven - build the project

1) Open a terminal (macOS, Linux) or cmd/powershell (Windows)

2) Clone the project
    git clone https://github.com/irotech/berlinClock.git <path>/vcandelaBerlinClock
    * <path> any path in the filesystem

3) Move into the project root directory (<path>/vcandelaBerlinClock)
    cd <path>/vcandelaBerlinClock

4a) Build the project with Maven
    mvn clean compile

5a) Execute the project as common Java app
    java -cp target/classes/ com.gmail.at.irotech.converter.BerlinClockConverter <hours> <minutes> <seconds>
    e.g. java -cp target/classes/ com.gmail.at.irotech.converter.BerlinClockConverter 14 23 33

5b) Execute the project as Java Spring-boot app
    java -jar target/berlinClock-1.1.jar

    After very few seconds the server is up and running - Tomcat started on port(s): 8080 (http)
    Following, on any browsers, the URL "http://localhost:8080/" after a redirect on "http://localhost:8080/berlinClock"
    the result, in JSON format,
    {"inputClock":"00:00:00","berlinClock":"Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO","result":"success","error":"","date":"2016-12-15T00:00"}
    In order to generate a BerlinClock combination for a custom time value follow the URL:
    "http://localhost:8080/berlinClock/{hours}/{minutes}/{seconds}"
    e.g. "http://localhost:8080/berlinClock/12/23/59"