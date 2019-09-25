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
  }
}