# spring-retry-issue

This repo is to be used for replicating following two issues of spring-retry.

1. Issue with randomExpression

When I use the following annotation to do retry. Then it is expected that I get 10 retries but only exponentially increasing and **randomness** in time.

```properties
retry.max-attempts=10
retry.delay=2000
retry.multiplier=2
retry.random=true
```

```java
@Retryable(retryFor = {RuntimeException.class}, 
        maxAttemptsExpression = "${retry.max-attempts}", 
        backoff = @Backoff(delayExpression = "${retry.delay}", 
                multiplierExpression = "${retry.multiplier}", 
                randomExpression = "${retry.random}"))
```

But, instead I get 10 retries exponentially increasing and **no randomness** in time.

2. Issue with multiplierExpression

When I have the following values:

```properties
retry.delay2=5000
retry.multiplier2=0.5
```

```java
@Retryable(retryFor = {RuntimeException.class}, 
        maxAttemptsExpression = "${retry.max-attempts}", 
        backoff = @Backoff(delayExpression = "${retry.delay}", 
                multiplierExpression = "${retry.multiplier}"))
```

Then the expected is that multiplierExpression should evaluate to 1.0 for any value between 0.0 to 1.0. But instead it's evaluating to 0.5 and you can see the retry time exponentially decreasing.

# How to Use this repo?

1. Run the server using following command in terminal

```shell
./gradlew clean bootRun
```

2. To replicate 1st issue. Run following curl

```shell
curl "http://localhost:8080/"
```

And checkout the seconds printed in the logs.

3. To replicate 2nd issue. Run following curl

```shell
curl "http://localhost:8080/issue2"
```

And checkout the seconds printed in the logs.