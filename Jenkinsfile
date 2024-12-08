pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'anishmn'
        APP_NAME = 'spring-boot-app'
        IMAGE_TAG = 'latest'  // You can change this to a dynamic version or tag
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
                        // Docker login
                        sh """
                        docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
                        docker build -t $DOCKER_REGISTRY/$APP_NAME:$IMAGE_TAG .
                        docker push $DOCKER_REGISTRY/$APP_NAME:$IMAGE_TAG
                        """
                    }
                }
            }
        }
        stage('Pull & Run Docker Image') {
            steps {
                script {
                    // Pull the Docker image from Docker Hub
                    sh """
                    docker pull $DOCKER_REGISTRY/$APP_NAME:$IMAGE_TAG
                    docker run -d -p 8090:8090 $DOCKER_REGISTRY/$APP_NAME:$IMAGE_TAG
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
