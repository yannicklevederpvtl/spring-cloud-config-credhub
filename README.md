# Spring Cloud Config Credhub Sample

## Step 1

Install the  Cloud Foundry CLI Plugin following the instructions [here](https://docs.pivotal.io/spring-cloud-services/3-1/common/cf-cli-plugin.html).

## Step 2

Create a Sring Cloud Config Server in your Space
```
$ cf create-service p.config-server standard configserver -c '{"git": { "uri": "https://github.com/yannicklevederpvtl/config-repo.git"} }'
```

Replace the git `uri` with your own git repository URI (including the same `config-demo-service.properties` file)

## Step 3

Clone this Git repository

Push the Spring Boot Application to the same space

```
$ cf push
```

Once the application is up and running, access https://config-demo-service.YOURDOMAIN/properties
You should get the two properties from the git repository

```
{
stringsampleproperty: ""mypropertyfromgit"",
intsampleproperty: 5
}
```

## Step 4

Manage this same stringsampleproperty into a secret store from Credhub

A secret stored using a path beginning with a specific app name (for example, a path beginning with myapp/) will only be made available to the app that uses that name (in this example, an app named `config-demo-service`).

```
cf config-server-add-credhub-secret configserver config-demo-service/default/master/mysecret '{"config-demo-service.stringsampleproperty": "mysecretpropertyfromcredhub"}'
```

Restart the application and access again https://config-demo-service.YOURDOMAIN/properties
You should get the two properties but the `stringsampleproperty` has been replaced by the same property from Credhub, secret properties take precedence over git properties.

```
{
stringsampleproperty: ""mysecretpropertyfromcredhubt"",
intsampleproperty: 5
}
```


A secret stored using a path beginning with application/ will be made available to all apps that retrieve CredHub configuration from the Config Server instance

```
cf config-server-add-credhub-secret configserver application/default/master/mysecret '{"config-demo-service.stringsampleproperty": "mysecretpropertyfromcredhub"}'
```

Important, app-specific secrets (like `config-demo-service/default/master/mysecret`) take precedence over default secrets (`application/default/master/mysecret`)



To remove the secret store of properties
```
cf config-server-remove-credhub-secret configserver config-demo-service/default/master/mysecret 
```

Get more information about managing your information with Credhub [here](https://docs.pivotal.io/spring-cloud-services/3-1/common/config-server/managing-secrets-with-credhub.html).
