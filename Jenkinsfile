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
        sh '''cp /home/jenkins/agent/workspace/edu_master/target/edu.war .
ls edu.war
cat >Dockerfile<<EOF
FROM tomcat:latest
RUN rm -fr /usr/local/tomcat/webapps/ROOT/*
COPY edu.war /usr/local/tomcat/webapps/ROOT/edu.war
RUN jar -xvf /usr/local/tomcat/webapps/ROOT/edu.war \\
    && rm -f /usr/local/tomcat/webapps/ROOT/edu.war
CMD ["catalina.sh", "run"]
EOF
docker build -t tc-edu:v1 .
docker images'''
      }
    }
  }
}