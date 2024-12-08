pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'anishmn'
        APP_NAME = 'spring-boot-app'
        IMAGE_TAG = 'latest'  // You can change this to a dynamic version or tag
        CONTAINER_NAME = 'spring-boot-app-container' // Name your container for easy reference
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
        stage('Stop Existing Container') {
            steps {
                script {
                    // Check if a container with the name already exists and stop it
                    def containerId = sh(script: "docker ps -q -f name=$CONTAINER_NAME", returnStdout: true).trim()
                    if (containerId) {
                        echo "Stopping container $CONTAINER_NAME with ID: $containerId"
                        sh "docker stop $containerId"
                        sh "docker rm $containerId"
                    } else {
                        echo "No existing container found with the name $CONTAINER_NAME"
                    }

                    // Check if any container is occupying port 8090 and stop it
                    def containerUsingPort = sh(script: "docker ps -q -f 'expose=8090'", returnStdout: true).trim()
                    if (containerUsingPort) {
                        echo "Found container occupying port 8090, stopping it."
                        sh "docker stop $containerUsingPort"
                        sh "docker rm $containerUsingPort"
                    }

                    // Remove any container with the same name as the new one
                    def existingContainer = sh(script: "docker ps -a -q -f name=$CONTAINER_NAME", returnStdout: true).trim()
                    if (existingContainer) {
                        echo "Found an existing container with name $CONTAINER_NAME. Removing it."
                        sh "docker rm -f $existingContainer"
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
                    docker run -d --name $CONTAINER_NAME -p 8090:8090 $DOCKER_REGISTRY/$APP_NAME:$IMAGE_TAG
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
