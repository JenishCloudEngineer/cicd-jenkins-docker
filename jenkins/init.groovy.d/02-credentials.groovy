import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.*
import hudson.util.Secret

println("=== Running Credentials Setup Script ===")

def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

def domain = Domain.global()

store.addCredentials(domain, new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "github-creds",
    "GitHub Credentials",
    "GITHUB_USERNAME",
    "GITHUB_TOKEN"
))

store.addCredentials(domain, new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "dockerhub-creds",
    "DockerHub Credentials",
    "DOCKER_USERNAME",
    "DOCKER_PASSWORD"
))

store.addCredentials(domain, new StringCredentialsImpl(
    CredentialsScope.GLOBAL,
    "slack-webhook",
    "Slack Webhook URL",
    Secret.fromString("SLACK_WEBHOOK_URL")
))

println("=== Credentials added successfully ===")
