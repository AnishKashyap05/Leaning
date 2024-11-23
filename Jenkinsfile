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
                withCredentials([string(credentialsId: 'docker-password-id', variable: 'DOCKER_PASSWORD')]) {
                    sh """
                    docker buildx build --platform linux/amd64 -t $DOCKER_REGISTRY/$APP_NAME:latest .
                    docker login -u $DOCKER_REGISTRY -p $DOCKER_PASSWORD
                    docker push $DOCKER_REGISTRY/$APP_NAME:latest
                    """
                }
            }
        }
         stage('Deploy') {
                     steps {
                         withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                 sh """
                                 ssh root@139.59.15.2 '
                                     echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin && \
                                     docker pull $DOCKER_REGISTRY/$APP_NAME:latest && \
                                     docker-compose up -d'
                                 """
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
