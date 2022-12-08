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
                    sh './gradlew sonarqube -Dsonar.login=sqa_5b2f0b1aa5c6f70c5ab4c8336f6e27753a176049'
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
    }
}