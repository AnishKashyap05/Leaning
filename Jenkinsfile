pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'anishmn'
        APP_NAME = 'spring-boot-app'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/AnishKashyap05/Leaning.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
       stage('Docker Build & Push') {
                   steps {
                       withCredentials([usernamePassword(credentialsId: 'docker-password-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                           script {
                               sh """
                               docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
                               docker build -t $DOCKER_REGISTRY/$APP_NAME:latest .
                               docker push $DOCKER_REGISTRY/$APP_NAME:latest
                               """
                           }
                       }
                   }
               }
           }

           post {
               always {
                   echo 'Pipeline execution completed!'
               }
           }
       }