pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('deploy'){
        	steps{
        		sh 'cp /root/.jenkins/workspace/examProAPI_Pipeline/target/examPro-0.0.1-SNAPSHOT.war /home/ubuntu/apache-tomcat-8.5.60/webapps'
        	}
        }
    }
}
