pipeline{
  agent {
    label "node1"
  }    
	stages{
    	stage('Prepare') {
    	    steps{
    		    script{
    		    	cleanWs()

    			    sh """
    			    	git clone https://github.com/Jooyeon-94/project0630.git
    			    	cd project0630 
    			    	chmod +x gradlew    			    	
    			    	./gradlew clean build -x test
    			    """
    		    }    	                  
    	    }
    	}
    	stage('Docker') {
    	    steps{
    		    script{
    			    sh """
    			        cd project0630
    			        cp Dockerfile build/libs
    			        docker-compose --version
    			    	docker-compose up -d
    			    """
    		    }    	                  
    	    }
    	}		    			
    	stage('API Test') {
    		steps{
    			script{
    				try{
    					sh "newman run project0630/Test_pass.postman_collection.json --reporters cli,junit --reporter-junit-export 'newman/NEWMAN-myreport.xml'"   				
    				}catch(err){
    					println("test error : ${err}")
    					sh "exit 0"
    				}		
    			}
			}           	
    	}	
    	stage('Result') {
    		steps{
    			junit '**/newman/NEWMAN-myreport.xml'
    		}           	
    	}	
	}
}

