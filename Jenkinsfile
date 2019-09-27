pipeline {
  agent any
  stages {
    stage('Git Clone') {
      steps {
        echo 'Step 1:Pull code from github'
        git(url: 'https://github.com/hygnt/edu.git', branch: 'master')
      }
    }
    stage('Maven Pack') {
      steps {
        echo 'Step 2:Execute code compile and pack'
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Docker Build') {
      steps {
        echo 'Step 3:Docker build image'
        sh '''cp /home/jenkins/agent/workspace/edu_master/target/edu.war .
cat >Dockerfile<<EOF
FROM tomcat:latest
RUN rm -fr /usr/local/tomcat/webapps/ROOT/*
COPY edu.war /usr/local/tomcat/webapps/ROOT/edu.war
RUN cd /usr/local/tomcat/webapps/ROOT \\
    && jar -xf edu.war \\
    && rm -f edu.war
CMD ["catalina.sh", "run"]
EOF
docker build -t reg.harbor.io/k8s/edu:v1 .'''
      }
    }
    stage('Docker Push') {
      steps {
        echo 'Step 4:Docker push image to registry'
        sh 'echo \'192.168.9.135 reg.harbor.io\'>>/etc/hosts'
        withCredentials(bindings: [usernamePassword(credentialsId: 'reg.harbor.io', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${dockerHubUser} -p ${dockerHubPassword} reg.harbor.io"
          sh 'docker push reg.harbor.io/k8s/edu:v1'
        }

      }
    }
    stage('Shell CMD Test') {
      steps {
        sh 'mvn -v'
        sh 'java -version'
        sh 'docker info'
        sh 'kubectl get node'
      }
    }
  }
}