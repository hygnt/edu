pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
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
    stage('Shell CMD') {
      steps {
        sh '''cp /home/jenkins/agent/workspace/edu_master/target/edu.war /root/.m2
ls /root/.m2'''
      }
    }
    stage('Build Docker Image') {
      agent {
        docker {
          image 'reg.harbor.io/k8s/jsdk'
          args '-v /root/.m2:/root/.m2'
        }

      }
      steps {
        sh '''cat /etc/os-release
cat >>Dockerfile<<EOF
FROM tomcate:latest
COPY /root/.m2/edu.war /usr/local/tomcat/webapps/ROOT/
RUN jar -xvf /usr/local/tomcat/webapps/ROOT/edu.war \\
    && rm -f /usr/local/tomcat/webapps/ROOT/edu.war
ENTRYPOINT ["catalina.sh"]
EOF
docker build -t tc-edu:v1 ./'''
      }
    }
  }
}