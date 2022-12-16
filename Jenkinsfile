pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                dir("./spring-boot-hello-world") {
                    sh './gradlew assemble'
                }
            }
        }
        
        stage('Test') {
            when {
                expression { false }
              }
            steps {
                dir("spring-boot-hello-world") {
                    sh './gradlew test'
                }
            }
            post {
                always {
                    junit 'spring-boot-hello-world/build/test-results/test/**/*.xml'
                    archiveArtifacts 'spring-boot-hello-world/build/reports/test/**/*'
                }
            }
        }
        stage('SonarQube') {
            steps {
                dir("./spring-boot-hello-world") {
                    withSonarQubeEnv('sonar-jenkins') {
                        sh './gradlew sonarqube'
                    }
                }
            }
        }
        stage("Quality Gate") {
            steps {
              timeout(time: 2, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }
          }

        stage('Publish') {
            steps {
                dir("./spring-boot-hello-world") {
                    sh './gradlew publish'
                }
            }
        }
    }
}