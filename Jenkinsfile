pipeline {
    agent any

    environment {
        DOCKER_USER = credentials('docker-username') // credencial Jenkins
        DOCKER_PASS = credentials('docker-password') // credencial Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/tu-usuario/geodata-service.git'
            }
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
