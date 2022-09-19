# UP42 Backend Coding Challenge

This assignment's purpose is to showcase my clean code & best engineering practice skills

### Local Setup

* Install Gradle version 7 from `https://services.gradle.org/distributions/gradle-7.3.2-bin.zip`

### Execution

* To run unit tests:
  `./gradlew clean test`
* To run unit tests:
  `./gradlew clean build`
* To run unit tests:
  `./gradlew clean bootRun`

## Things I tried to follow

1. I tried to follow 4 rules of simple design by Kent Beck.

2. I didn't make interfaces as per YAGNI principles because for now, I don't feel the need for the same (Yes, I am aware
   of this principle also - "Program to the interface rather than concrete implementation", but I only make an interface
   where there are at least 2 implementations otherwise I feel it's premature).

3. I tried to create immutable domain entities.

4. I tried to follow the law of Demeter so as not to break encapsulation (As per the domain I try to make value objects,
   but sometimes I have to add getters because of Jackson).

5. I tried to follow the separation of concerns by introducing general 3-layered architecture.

6. I tried to have small & logical commits with commit messages as the reasoning why I made this change (That's why some
   commit messages got long), and the commit history is linear.

5. I tried to avoid code duplication by refactoring/reusing duplicate code - DRY principle.

7. I tried to follow AAA (Arrange, Act, Assert) structuring which is equivalent to the "Given When Then" approach in
   unit tests & helps in increasing readability as unit tests are the documentation of my code.

8. I used a hybrid mocking strategy in my unit tests as per Martin Fowler. I didn't use mocking in unit-test for domain
   models (called social unit tests), but used mocking in other unit tests so as make them fast as per the test pyramid.
   Both school of thoughts have their own pros & cons (London & California school of thought), so it's kind of a
   trade-off here

11. I tried to achieve high coverage in my unit tests.

11. Added some Info logs to check whether caching is working or not.

12. I separated out DTO & Models because the service should encapsulate the domain AFAIK. Domain entity should not get
    leaked out of service as per

## Things I could have done/improved if given more time & leverage

1. I am a admirer of clean architecture by Uncle Bob (Robert C Martin) but due to time constraints & considering it as
   an MVP I felt that we can refactor it later.

2. If given more time, I could have added scenarios in my Integration test for get quicklook endpoint.

3. If given more time, I could have generated a unit test coverage report.

4. If given more time, I could have also added some unit tests to check whether my caching is working or not properly.

5. If given more time, I could have followed Martin's follower notification pattern as per my knowledge, throwing
   exceptions are costly sometimes I feel.

6. In some scenarios I added unit tests later although for every story/ticket according to me tests are part of
   development. A story cannot be completed unless tests are done.

## Why I did that thing?

1. No ache eviction policy? I assumed that this data source will not change otherwise we also need to think in te
   direction of cache eviction policy too

2. No string constant? I didn't create string constants as per YAGNI. I only go for constants when they are being used
   more than twice.

## Things might not have been required

1. One can feel creating packages for controller, service & repository could have been avoided as we only have a single
   class of Controller, Service & Repository.

2. I also personally felt some models like FeatureCollection.java is not required as it just contains a list of
   features. This decision is purely dependent upon the business problem we are solving. Ideal domain modeling would
   have Feature.Java, Acquisition.java.