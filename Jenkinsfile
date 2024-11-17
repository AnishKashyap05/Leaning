pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'anishmn'
        APP_NAME = 'spring-boot-app'
        DOCKER_PASSWORD = 'AnishDoc@0918'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/AnishKashyap05/Leaning.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    sh """
                    docker build -t $DOCKER_REGISTRY/$APP_NAME:latest .
                    docker login -u $DOCKER_REGISTRY -p $DOCKER_PASSWORD
                    docker push $DOCKER_REGISTRY/$APP_NAME:latest
                    """
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sshagent(['dockerhub-credentials']) {
                        sh """
                        ssh root@139.59.15.2 'docker pull $DOCKER_REGISTRY/$APP_NAME:latest && docker-compose up -d'
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
