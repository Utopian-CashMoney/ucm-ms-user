pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
	    jdk 'jdk1.8'
        nodejs 'nodejs'
    }

    stages {
        stage('Package Maven project') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'docker build . -t user-ms'
            }
        }
        
        stage('Push to Amazon ECR') {
            steps {
                withAWS(credentials: 'jenkins-credentials', region: 'us-east-1') {
                    sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 202447729588.dkr.ecr.us-east-1.amazonaws.com"
                    sh "docker tag user-ms:latest 202447729588.dkr.ecr.us-east-1.amazonaws.com/user-ms:latest"
                    sh "docker push 202447729588.dkr.ecr.us-east-1.amazonaws.com/user-ms:latest"
                }
            }
        }
    }
}
