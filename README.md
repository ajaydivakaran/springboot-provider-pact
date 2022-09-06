# springboot-provider-pact Project

### Software requirements
* JDK 17


### To publish pact verification results
```shell script
./gradlew clean test pactVerify -Ppact.verifier.publishResults=true
```
