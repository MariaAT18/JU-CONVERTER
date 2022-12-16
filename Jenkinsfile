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
        stage('SonarQube') {
            steps {
                dir("./spring-boot-hello-world") {
                    sh './gradlew sonarqube'
                }
            }
        }
        stage('Test') {
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
        stage('Publish') {
            steps {
                dir("./spring-boot-hello-world") {
                    sh './gradlew publish --console verbose'
                }
            }
        }
    }
}