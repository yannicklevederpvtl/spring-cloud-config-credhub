# Spring Cloud Config Credhub + Spring Boot Client Sample

### Step 1

Install the  Cloud Foundry CLI Plugin following the instructions [here](https://docs.pivotal.io/spring-cloud-services/3-1/common/cf-cli-plugin.html)

### Step 2

Clone the git `config-repo` repository of properties:
```
$ git clone https://github.com/yannicklevederpvtl/config-repo.git
```

### Step 3

Create a Sring Cloud Config Server in your Space (Replace the git `uri` with your own git `config-repo` repository URI):
```
$ cf create-service p.config-server standard configserver -c '{"git": { "uri": "https://github.com/yannicklevederpvtl/config-repo.git"} }'
```


### Step 4

Clone this project git `spring-cloud-config-credhub` repository

Push the Spring Boot Application to the same Space:

```
$ cd spring-cloud-config-credhub
$ cf push
```

Once the application is up and running, access this link: https://config-demo-service.YOURCFDOMAIN/properties
You should get the two properties from the git `config-repo` repository:

```
{
stringsampleproperty: "mypropertyfromgit",
intsampleproperty: 5
}
```

### Step 5

Manage this same `stringsampleproperty` property into a secret store from Credhub for Config Server

A secret stored using a path beginning with a specific app name (for example, a path beginning with myapp/) will only be made available to the app that uses that name (in this example, an app named `config-demo-service`)

To add this secret store for Config Server:

```
cf config-server-add-credhub-secret configserver config-demo-service/default/master/mysecretstore '{"config-demo-service.stringsampleproperty": "mysecretpropertyfromcredhub"}'
```

Restart the application and access again this link: https://config-demo-service.YOURDOMAIN/properties
You should get the two properties but the `stringsampleproperty` has been replaced by the property value from Credhub, in fact secret properties take precedence over git properties:

```
{
stringsampleproperty: "mysecretpropertyfromcredhub",
intsampleproperty: 5
}
```

#### Secrets distribution to many apps
A secret stored using a path beginning with `application/` will be made available to all apps that retrieve CredHub configuration from the Config Server instance

```
cf config-server-add-credhub-secret configserver application/default/master/mysecretstore '{"config-demo-service.stringsampleproperty": "mysecretpropertyfromcredhub"}'
```

Important, app-specific secrets (like `config-demo-service/default/master/mysecretstore`) take precedence over default secrets (`application/default/master/mysecret`)



To remove the secret store of properties:
```
cf config-server-remove-credhub-secret configserver config-demo-service/default/master/mysecretstore 
```

Get more information about managing secrets with Credhub [here](https://docs.pivotal.io/spring-cloud-services/3-1/common/config-server/managing-secrets-with-credhub.html).
