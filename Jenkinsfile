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
                sh 'mvn package'
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'docker build . -t user-ms'
            }
        }
        
        stage('Push to Amazon ECR') {
            steps {
                withAWS(credentials: 'jenkins-credentials', region: '${AWS_REGION}') {
                    environment {
                        AWS_ACCOUNT_ID = sh 'aws sts get-caller-identity | grep -oP '(?<="Account": ")[^"]*''
                    }
                    sh "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
                    sh "docker tag user-ms:latest ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/user-ms:latest"
                    sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/user-ms:latest"
                }
            }
        }
    }
}
