# Deploy Spring Boot on ServerLess Architecure  Using Azure Function and Spring Cloud Function

## Crud Operation with Spring Cloud Operation
## Install Azure CLI

```
https://docs.microsoft.com/en-us/cli/azure/install-azure-cli-windows?view=azure-cli-latest

brew tap azure/functions
brew install azure-functions-core-tools
```

## Build a function

```
mvn clean package -DskipTests=true
```

## Run Locally

```
mvn azure-functions:run
```


## Deploy

```
az login
mvn azure-functions:deploy
```
```
Put Here Master key of all function in case of AuthorizationLevel=Function
https://<APP_NAME>.azurewebsites.net/api/<FUNCTION_NAME>?code=<API_KEY>

```
https://presos.dsyer.com/decks/road-to-serverless.html
