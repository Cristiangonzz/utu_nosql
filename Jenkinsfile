pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository containing both backend and frontend
                git 'https://your-repository-url.git'
            }
        }

        stage('Build Backend') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-21'
                }
            }
            steps {
                dir('backend') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            agent {
                docker {
                    image 'node:20'
                }
            }
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('my-app')
                }
            }
            }

        stage('Deploy Application') {
            steps {
                script {
                    docker.image('my-app').run('-p 8080:8080')
                }
            }
        }
    }
}
