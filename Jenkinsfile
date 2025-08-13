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
                    def services = [
                        [name: 'eureka-service', folder: 'eureka-service'],
                        [name: 'config-server', folder: 'configGeo-server'],
                        [name: 'apigateway', folder: 'api-gateway'],
                        [name: 'geologist-service', folder: 'geologist-service'],
                        [name: 'sample-service', folder: 'sample-service'],
                        [name: 'study-service', folder: 'study-service']
                    ]

                    // Login seguro a Docker Hub
                    sh """
                    echo '${DOCKER_CREDS_PSW}' | docker login -u '${DOCKER_CREDS_USR}' --password-stdin
                    """

                    for (s in services) {
                        echo "Building and pushing Docker image for ${s.name}..."
                        sh "docker build -f ${s.folder}/Dockerfile -t ${DOCKER_CREDS_USR}/${s.name}:latest ${s.folder}"
                        sh "docker push ${DOCKER_CREDS_USR}/${s.name}:latest"
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
