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

        stage ('Code Analysis') {
            steps {
                // Set SonarQube home directory, waiting for better way to do this
                script {
                    scannerHome = tool 'SonarQube Scanner 4.6'
                }
                // Run SonarQube scan using running EC2 instance
                withSonarQubeEnv('SonarQube Scanner') {
                    sh "mvn sonar:sonar -Dsonar.host.url=http://sonar.utopiancashmoney.de -Dsonar.login=d6163b0bd65e7daa5bc3d40e64f5503535a473b7"
                }
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
