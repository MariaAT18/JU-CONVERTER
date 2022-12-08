pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                dir("./spring-boot-hello-world") {
                    sh './gradlew --stacktrace --scan assemble'
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