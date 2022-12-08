pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './spring-boot-hello-world/gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './spring-boot-hello-world/gradlew test'
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