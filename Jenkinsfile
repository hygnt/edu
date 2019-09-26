pipeline {
  agent any
  stages {
    stage('Git Clone') {
      steps {
        echo 'Step 1:Pull code from github'
        git(url: 'https://github.com/hygnt/edu.git', branch: 'master')
      }
    }
    stage('Maven Build') {
      steps {
        echo 'Step 2:Execute code compile and pack'
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Docker Build') {
      steps {
        echo 'Step 3:Docker build image'
        sh '''cp /home/jenkins/agent/workspace/edu_master/target/edu.war .
ls edu.war
cat >Dockerfile<<EOF
FROM tomcat:latest
RUN rm -fr /usr/local/tomcat/webapps/ROOT/*
COPY edu.war /usr/local/tomcat/webapps/ROOT/edu.war
RUN cd /usr/local/tomcat/webapps/ROOT \\
    && jar -xf edu.war \\
    && rm -f edu.war
CMD ["catalina.sh", "run"]
EOF
docker build -t reg.harbor.io/k8s/edu:v1 .
docker images|grep edu'''
      }
    }
    stage('Docker Push') {
      steps {
        echo 'Step 4:Docker push image to registry'
        sh '''echo \'192.168.9.135 reg.harbor.io\'>>/etc/hosts
cat /etc/hosts
docker login -u admin -p Harbor12345 reg.harbor.io
docker push reg.harbor.io/k8s/edu:v1'''
      }
    }
  }
}