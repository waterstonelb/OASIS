pipeline {
    agent any

    environment{
         ORG_NAME = "106.12.89.160:5000"
         APP_NAME = "backend-shkb"
         APP_VERSION = "${env.BUILD_NUMBER}"
        //DOCKER_HUB = credentials("${ORG_NAME}-docker-hub")

    }

    stages {
        stage('Clean') {
            steps {
                echo 'Building..'
                sh 'mvn clean'
                echo 'Clean Success'
            }
        }
//         stage('Build Docker Image') {
//                     steps {
//                         echo 'Building'
//                         sh 'docker build -t ${APP_NAME}:${APP_VERSION} .'
//                         echo 'Build Success'
//                     }
//                 }
        stage('Test') {
            steps{
                echo 'Testing..'
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
                jacoco execPattern: 'target/jacoco.exec'
                echo 'Test Success'
            }

        }

        stage('Build'){
            steps{
                echo 'Building'
                sh 'mvn package -Dmaven.test.skip=true'
                echo 'Build Success'
            }
        }


        stage('Push to SonarQube') {
                steps{
                    withSonarQubeEnv('sonarqube') {
                        sh "mvn sonar:sonar -Dproject.settings=sonar-project.properties"
                    }
                }

        }


        stage('Deploy to Service'){
                steps{
                    sh "/home/shkb/jar.sh"
                    sh "cp target/*jar /home/shkb/"
                    sh "/home/shkb/start.sh"
                    sh "ssh shkb@39.97.108.99 ./jar.sh"
                    sh "rsync target/*.jar shkb@39.97.108.99:~/"
                    //sshScript remote: remote1, script: "./start.sh"
                    sh "ssh shkb@39.97.108.99 ./start.sh &"
                    sh "ssh shkb@123.56.253.41 ./jar.sh"
                    sh "rsync target/*.jar shkb@123.56.253.41:~/"
                    //sshScript remote: remote2, script: "./start.sh"
                    sh "ssh shkb@123.56.253.41 ./start.sh &"
                }
        }

//         stage('Run Docker image') {
//                 steps {
//                     echo "-=- run Docker image -=-"
//                     sh 'docker stop ${APP_NAME}'
//                     sh "docker run --name ${APP_NAME} -d -v shkb:/var/log/shkb --rm -p 8090:8090 ${APP_NAME}:${APP_VERSION}"
//                 }
//         }
//         stage('Delete old image'){
//
//             steps{
//                 echo 'Deleting'
//                 //sh "docker tag ${APP_NAME}:${APP_VERSION} ${ORG_NAME}/shkb/${APP_NAME}:${APP_VERSION}"
//
//                 //sh "docker push ${ORG_NAME}/shkb/${APP_NAME}:${APP_VERSION}"
//                 //sh 'docker rmi $(docker images | grep ${APP_NAME} | grep -v ${APP_VERSION}) '
//                 echo 'Delete Success'
//             }


    }
}