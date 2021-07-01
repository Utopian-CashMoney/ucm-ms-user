pipeline { 
    agent any 
    
    tools { 
	    maven 'Maven 3.8.1' 
	    jdk 'jdk1.8' 
    }
	
    environment {
	    COMMIT_HASH = "${sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()}"
	    
	    registry = "202447729588.dkr.ecr.us-east-2.amazonaws.com/bankingapp"
	    
	   // AWS_ID = credentials('AWS_ID')
	    
	    IMG_NAME = "userms"
    }
    
    stages { 
	      stage ('Checkout Git Repo') {
	        steps {
	                git branch: 'master', url: 'https://github.com/Utopian-CashMoney/ucm-ms-user.git'            
	        }
	    }
	    
          stage ('Unit Tests') {
            
            steps {
            
                  sh 'mvn clean test'        
            }
        }

        stage ('Build') {
            
            steps {
            
                  sh 'mvn clean package' 
                
            }
        }
	    
       stage("Docker Build") {
	   steps {
	        echo "Docker Build...."
		   
// 		sh "aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin ${AWS_ID}.dkr.ecr.us-east-2.amazonaws.com"
		   
		sh "docker build --tag ${IMG_NAME}:${COMMIT_HASH} ."
		   
// 		sh "docker tag ${IMG_NAME}:${COMMIT_HASH} ${AWS_ID}.dkr.ecr.us-east-2.amazonaws.com/${IMG_NAME}:${COMMIT_HASH}"
		   
		echo "Docker Push..."
		   
// 		sh "docker push ${AWS_ID}.dkr.ecr.us-east-2.amazonaws.com/${IMG_NAME}:${COMMIT_HASH}"
	   }
	       
	}
	    
	stage('Pushing to ECR') {
		
             steps{ 
		    
                sh 'aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 202447729588.dkr.ecr.us-east-2.amazonaws.com'
		     
                sh 'docker push 202447729588.dkr.ecr.us-east-2.amazonaws.com/bankingapp:latest'
         
             }
		
      }
	    
  }
	    
	post {
	  always {
	      sh 'mvn clean'
	//               sh "docker system prune -f"
	   }
      }
}

	
	
