pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
             sh "pwd"
                dir("./spring-boot-hello-world") {
                    sh "pwd"
                    sh 'gradlew assemble'
                }
            }
        }
        stage('Test') {
            steps {
             sh "pwd"
                dir("spring-boot-hello-world") {
                 sh "pwd"
                    sh 'gradlew test'
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