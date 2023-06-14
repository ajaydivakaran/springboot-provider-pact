# springboot-provider-pact Project

### Software requirements
* JDK 17


### To publish pact verification results

#### using Gradle

```shell script
./gradlew clean test pactVerify -Pprovider.version=3.0.0 -Ppact.verifier.publishResults=true
```
