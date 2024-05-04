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
        stage("Build Application (with tests skipped)"){
            steps {
                sh 'mvn clean install -DskipTests' 
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
        stage('Deploy to Kubernets'){
            steps{
                script{
                    dir('kubernetes') {
                       withKubeConfig(caCertificate: '', clusterName: 'EKS-1', contextName: '', credentialsId: 'kubernetes', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://694F72DFCE5A9600D40383A19A8FB008.gr7.eu-west-3.eks.amazonaws.com') {
                       sh 'kubectl create -f db.yaml'
                       sh 'kubectl rollout restart deployment.apps/springboot-crud-deployment'
                       }   
                    }
                }
            }
        }
    }
}
