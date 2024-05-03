pipeline {
    agent {
        label "Jenkins-Agent"
    }
    tools {
        jdk 'java17'
        maven 'Maven3'
    }
    environment {
        SCANNER_HOME = tool 'sonarqube-scanner'
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
        stage('SonarQube Analysis'){
            steps{
                script {
                    withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token') {
                    sh "mvn sonar:sonar"
                   }
                }
            }
        }
        stage("Quality Gate"){
            steps {
                script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonarqube-token'
                }
            }
        }
        stage('TRIVY FS SCAN') {
            steps {
                sh "trivy fs . > trivyfs.txt"
            }
        }
        stage("Docker Build & Push"){
            steps{
                script{  
                    withDockerRegistry(credentialsId: 'dockerhub', toolName: 'docker') {
                       sh "docker build -t thiernos/springboot-app:1.0.0 ."
                       sh "docker push thiernos/springboot-app:1.0.0"
                    }
                }
            }
        }
    }
}
