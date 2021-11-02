pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
	    jdk 'jdk1.8'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
        withAWS(credentials: 'jenkins-credentials')
    }

    environment {
        NAME = 'user-ms'
        AWS_REGION = 'us-east-1'
        GIT_COMMIT = "${env.GIT_COMMIT}"
    }

    stages {
        stage('Package Maven project') {
            steps {
                // Build Maven Java project
                sh 'mvn clean package'
            }
        }

        stage('Build Docker image') {
            steps {
                // Build project into Docker image
                sh 'docker build . -t ${NAME}'
            }
        }
        
        stage('Push to Amazon ECR') {
            steps {
                withAWS(credentials: 'jenkins-credentials', region: '${AWS_REGION}') {
                    /*
                     * Pull account ID from jenkins-credentials AWS profile
                     * Login to AWS ECR for private repo access
                     * Push image to ECR
                     */
                    sh '''
                        AWS_ACCOUNT_ID=$(aws sts get-caller-identity | grep -oP \'(?<="Account": ")[^"]*\')
                        aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
                        docker tag ${NAME}:latest ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                        docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest
                    '''
                }
            }
        }
    }

	post {
		always {
            // Cleanup, unused docker images are large
            sh 'mvn clean'
            sh 'docker system prune -f'
            cleanWs()
		}
	}
}
