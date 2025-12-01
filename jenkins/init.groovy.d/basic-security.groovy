#!groovy

import jenkins.model.*
import hudson.security.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.domains.DomainRequirement
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.impl.StringCredentialsImpl
import hudson.util.Secret

println("Running fixed basic-security.groovy")

def instance = Jenkins.getInstance()

// Create admin user
def realm = new HudsonPrivateSecurityRealm(false)
realm.createAccount("admin", "admin123")
instance.setSecurityRealm(realm)

instance.setAuthorizationStrategy(new FullControlOnceLoggedInAuthorizationStrategy())
instance.save()

println("Admin user created: admin / admin123")

// Credentials store
def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()
def domain = Domain.global()

// GitHub credentials
def githubCreds = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "github-creds",
    "GitHub Credentials",
    "GITHUB_USERNAME",
    "GITHUB_TOKEN"
)
store.addCredentials(domain, githubCreds)

// DockerHub credentials
def dockerCreds = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "dockerhub-creds",
    "DockerHub Credentials",
    "DOCKER_USERNAME",
    "DOCKER_PASSWORD"
)
store.addCredentials(domain, dockerCreds)

// Slack webhook
def slackCreds = new StringCredentialsImpl(
    CredentialsScope.GLOBAL,
    "slack-webhook",
    "Slack Webhook URL",
    Secret.fromString("SLACK_WEBHOOK_URL")
)
store.addCredentials(domain, slackCreds)

println("Credentials added successfully")
