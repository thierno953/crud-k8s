
pipeline {
    agent {
        label "Jenkins-Agent"
    }
    tools {
        jdk 'java17'
        maven 'Maven3'
    }
    stages {
        stage('clean workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout from Git') {
            steps {
                git branch: 'main', url: 'https://github.com/thierno953/crud-k8s.git'
            }
        }
        stage("Build Application"){
            steps {
             sh 'mvn clean install -DskipTests' 
           }
       }
        stage("Test Application"){
            steps {
             sh "mvn clean test"
           }
       }
    }
}
