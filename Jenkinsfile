pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
	    jdk 'jdk1.8'
        nodejs 'nodejs'
    }

    environment {
        AWS_REGION = 'us-east-1'
        GIT_COMMIT = "${env.GIT_COMMIT}"
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
                    sh """
                        AWS_ACCOUNT_ID=$(aws sts get-caller-identity | grep -oP '(?<=\"Account\": \")[^\"]*')
                        aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com
                        docker tag user-ms:latest ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/user-ms:latest
                        docker push ${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/user-ms:latest
                    """
                }
            }
        }
    }
}
