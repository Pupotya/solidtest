# solidtest

install:
- Java 21.0 or higher
- allure

run: 

`./gradlew clean test -DpublicKey=<value> -DsecretKey=<value>`

*ask for api keys if needed

to see report:

`allure serve ./build/allure-results`
