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
    stage('Mamen Build') {
      steps {
        sh '''mvn clean package -Dmaven.test.skip=true
ls /home/jenkins/agent/workspace/edu_master/target/edu.war
ssh root@192.168.9.138'''
      }
    }
  }
}