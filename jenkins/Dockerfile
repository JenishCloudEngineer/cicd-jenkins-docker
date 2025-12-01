FROM jenkins/jenkins:lts

USER root

RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get update \
    && apt-get install -y nodejs docker.io \
    && apt-get clean

RUN usermod -aG docker jenkins

RUN chown -R jenkins:jenkins /var/jenkins_home

USER jenkins

