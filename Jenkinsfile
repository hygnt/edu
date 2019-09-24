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
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Shell CMD') {
      steps {
        sh '''cat /etc/os-release
ls /home/jenkins/agent/workspace/edu_master/target/edu.war'''
      }
    }
  }
}