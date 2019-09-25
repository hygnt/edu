pipeline {
  agent any
  stages {
    stage('Git Clone') {
      steps {
        git(url: 'https://github.com/hygnt/edu.git', branch: 'master')
      }
    }
    stage('Maven Build') {
      steps {
        sh '''mvn -B -DskipTests clean package
ls /root/.m2'''
      }
    }
    stage('Shell CMD1') {
      steps {
        sh '''cp /home/jenkins/agent/workspace/edu_master/target/edu.war /root/.m2
ls /root/.m2'''
      }
    }
    stage('Docker Build') {
      steps {
        sh '''cat >Dockerfile<<EOF
FROM tomcat:latest
RUN rm -f /usr/local/tomcat/webapps/ROOT/*
COPY /root/.m2/edu.war /usr/local/tomcat/webapps/ROOT/edu.war
CMD ["catalina.sh", "run"]
EOF
docker build -t tc-edu:v1 .
docker images'''
      }
    }
  }
}