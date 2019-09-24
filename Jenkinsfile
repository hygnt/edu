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
    stage('Mamen Build & Copy War') {
      parallel {
        stage('Mamen Build') {
          steps {
            sh 'mvn clean package -Dmaven.test.skip=true'
          }
        }
        stage('Copy War') {
          steps {
            sh '''sudo apt install sshpass -y
sudo ssh-keygen -t rsa -N \'\' -f ~/.ssh/id_rsa
sudo sshpass -p \'admin123\' ssh-copy-id -o StrictHostKeyChecking=no -i /root/.ssh/id_rsa -p 22 root@192.168.9.138
sudo scp /home/jenkins/agent/workspace/edu_master/target/edu.war root@192.168.9.138:/root'''
          }
        }
      }
    }
  }
}