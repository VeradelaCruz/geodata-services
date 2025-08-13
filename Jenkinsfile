pipeline {
    agent any

    environment {
        DOCKER_CREDS = credentials('dockerhub-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/VeradelaCruz/geodata-services.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Build Microservices') {
            steps {
                script {
                    def services = ['eureka-service', 'configGeo-server', 'api-gateway', 'geologist-service', 'sample-service', 'study-service']
                    for (s in services) {
                        echo "Compiling ${s}..."
                        dir(s) {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def services = ['eureka-service', 'config-server', 'apigateway', 'geologist-service', 'sample-service', 'study-service']
                    sh "echo '$DOCKER_CREDS_PSW' | docker login -u '$DOCKER_CREDS_USR' --password-stdin"
                    for (s in services) {
                        echo "Building and pushing Docker image for ${s}..."
                        dir(s) {
                            sh "docker build -t $DOCKER_CREDS_USR/${s}:latest ."
                            sh "docker push $DOCKER_CREDS_USR/${s}:latest"
                        }
                    }
                }
            }
        }
    }

    post {
        success { echo 'Pipeline completado correctamente ✅' }
        failure { echo 'Pipeline falló ❌' }
    }
}
