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
        sh 'mvn clean install -Dmaven.test.skip=true'
      }
    }
  }
}