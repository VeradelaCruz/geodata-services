pipeline {
    agent any

    environment {
        DOCKER_CREDS = credentials('dockerhub-credentials')
    }

   stage('Checkout') {
       steps {
           git branch: 'master',
               url: 'https://github.com/VeradelaCruz/geodata-services.git',
               credentialsId: 'github-creds'  // <-- ID exacto de la credencial que creaste
       }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh '''
                echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                docker build -t $DOCKER_USER/geodata-service:latest .
                docker push $DOCKER_USER/geodata-service:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completado correctamente ✅'
        }
        failure {
            echo 'Pipeline falló ❌'
        }
    }
}
