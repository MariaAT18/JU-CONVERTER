pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                echo './gradlew test'
            }
            post {
                always {
                    junit 'build/test-results/test/**/*.xml'
                    archiveArtifacts 'build/reports/test/**/*'
                }
            }
        }
    }
}