pipeline {
    agent any
    stages {
        stage("Test and Package") {
            steps {
               sh "mvn clean test"
            }
        }
        
	        stage("Clean and Test target") {
	    steps {
	        	 sh "mvn package"
	    	}
        }

    }
    post {
        always {
            sh "mvn clean"
        }
    }
}