pipeline {
    agent any

    environment{
         ORG_NAME = "shkb.cf:5000"
         APP_NAME = "springdemo"
         APP_VERSION = "${env.BUILD_NUMBER}"
        //DOCKER_HUB = credentials("${ORG_NAME}-docker-hub")
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn package'
                echo 'Build Success'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
                echo 'Test Success'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building'
                sh 'docker build -t ${ORG_NAME}/${APP_NAME}:${APP_VERSION} .'
                sh "docker tag ${ORG_NAME}/${APP_NAME}:${APP_VERSION} ${ORG_NAME}/${APP_NAME}:latest"
                echo 'Build Success'
            }
        }
        stage('Run Docker image') {
                steps {
                    echo "-=- run Docker image -=-"
                    sh 'docker stop ${APP_NAME}'
                    sh "docker run --name ${APP_NAME} -d --rm -p 8009:8090 ${ORG_NAME}/${APP_NAME}:latest"
                }
        }
        stage('Push To Registry'){
            echo 'Pushing'
            steps{
                 sh "docker push ${ORG_NAME}/${APP_NAME}:${APP_VERSION}"
                 sh "docker rmi $(docker images | grep ${ORG_NAME}/${APP_NAME} | grep -v "latest" | grep -v ${APP_VERSION} | awk '{print $3}') "
            }
            echo 'Pushing Success'
        }
    }
}