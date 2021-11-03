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
                sh 'mvn clean package -DskipTests'
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
                /*
                * Pull account ID from jenkins-credentials AWS profile
                * Login to AWS ECR for private repo access
                * Push image to ECR
                */
                
                script {
                    def aws_account_id = awsIdentity().account
                    sh ecrLogin()
                    sh "docker tag ${NAME}:latest ${aws_account_id}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest"
                    sh "docker push ${aws_account_id}.dkr.ecr.${AWS_REGION}.amazonaws.com/${NAME}:latest"
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
