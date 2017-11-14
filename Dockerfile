FROM openjdk:8
MAINTAINER TeamBPMPolytechAdmission
COPY ./target/PolytechAdmissionBPM-1.0-SNAPSHOT.jar /home/PolytechAdmissionBPM-1.0-SNAPSHOT.jar
CMD ["java","-jar","/home/PolytechAdmissionBPM-1.0-SNAPSHOT.jar"]