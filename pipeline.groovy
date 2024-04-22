pipeline {
    agent any
    stages {
        stage ('SCM checkout'){
            steps{
                retry(3){
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/lasith54/4111-Perera.git']])
                }
            }
      
        }
        stage('build docker'){
            steps {
                sh 'docker build -t  LasithS/4111-perera.'
            }
        
        }
         stage('run'){
            steps{
                sh 'docker run -d -p 5000:3000 LasithS/4111-perera'
         }
         }
         stage('show running containners'){
      steps{
        sh 'docker ps'
      }
    }
    }
}