pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'maven:3.6.1-jdk-8-alpine'
          args '-v /root/.m2:/root/.m2 -v /root/artifacts:/artifacts --network sonar-qube_default'
        }

      }
      steps {
        sh 'mvn -B -DskipTests clean -s maven-settings.xml -Dsettings.security=settings-security.xml package sonar:sonar -Dsonar.host.url=http://sonarqube:9000 -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true'
        sh 'cp -rp target /artifacts'
        archiveArtifacts(artifacts: 'target/*.jar', fingerprint: true)
      }
 //     post {
 //       always {
 //           junit 'target/surefire-reports/*.xml'
 //       }
 //     }
    }
    stage('Containerize') {
      steps {
        sh "cp -rp /artifacts/target ." 
        sh "/usr/bin/docker build -t  ${env.SERVICE_URL}:${env.SERVICE_PORT}/${env.APP_NAME}:${env.BUILD_ID} . "
        sh "/usr/bin/docker login  ${env.SERVICE_URL}:${env.SERVICE_PORT} -u ${GITHUB_ASH_CREDS_USR} -p ${GITHUB_ASH_CREDS_PSW}"
        sh "/usr/bin/docker push   ${env.SERVICE_URL}:${env.SERVICE_PORT}/${env.APP_NAME}:${BUILD_NUMBER}"
        sh "/usr/bin/docker tag   ${env.SERVICE_URL}:${env.SERVICE_PORT}/${env.APP_NAME}:${BUILD_NUMBER} ${env.SERVICE_URL}:${env.SERVICE_PORT}/${env.APP_NAME}:latest"
        sh "/usr/bin/docker push   ${env.SERVICE_URL}:${env.SERVICE_PORT}/${env.APP_NAME}:latest"
      }
    }
  }
  environment {
    SERVICE_URL = 'docker.viosystems.com'
    SERVICE_PORT = '8443'
    APP_NAME = 'authnservice'
    GITHUB_ASH_CREDS  = credentials('jenkins-user-for-nexus-repository')
  }
}
