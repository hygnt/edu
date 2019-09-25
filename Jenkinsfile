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
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Docker Build') {
      steps {
        sh '''ls /home/jenkins/agent/workspace/edu_master/target/edu.war
cat >Dockerfile<<EOF
FROM tomcat:latest
RUN rm -fr /usr/local/tomcat/webapps/ROOT/*
COPY /home/jenkins/agent/workspace/edu_master/target/edu.war /usr/local/tomcat/webapps/ROOT/edu.war
CMD ["catalina.sh", "run"]
EOF
docker build -t tc-edu:v1 .
docker images'''
      }
    }
  }
}