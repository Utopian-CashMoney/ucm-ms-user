pipeline { 
    agent any 
    
    tools { 
	    maven 'Maven 3.8.1' 
	    jdk 'jdk1.8' 
    }
    
    environment {

	    scannerHome = tool 'SonarqubeScanner'
    }
    
    stages { 
	      stage ('Checkout Git Repo') {
	        steps {
	                git branch: 'master', url: 'https://github.com/Utopian-CashMoney/ucm-ms-user.git'            
	        }
	    }
	    
          stage ('Unit Tests') {
            
            steps {
            
                  sh 'mvn test'   
            }
        }
	
	stage ('SonarQube Analysis') {
            
	     tools {
			jdk 'jdk11'
		}
		
             steps {
		     	  sh 'java -version'
                      withSonarQubeEnv('Sonarqube') {
                          sh 'mvn sonar:sonar'
                      }
                  }
        }
	    
        stage ('Build') {
            
            steps {
            
                  sh 'mvn clean package' 
                
            }
        }
    }
}
