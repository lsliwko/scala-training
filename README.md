# scala-training
Scala training exercises


1. LESSON ONE: Functional programming
- show presentation: https://www.youtube.com/watch?v=bD9EfC_2NvY
- show GitHub repo: https://github.com/lsliwko/s3-cloudsearch-lambda/blob/main/src/main/scala/LambdaMain.scala
- why functional programming (multiple CPUs L2/L3 memory cache)
- 90% of cases we are good with object-oriented programming
- mutable / immutable collections
- parallel collections
- HOMEWORK: Play Framework exercise

2. LESSON TWO: Play Framework exercise
- use Google to search for Maven Repository string for sbt 
- routes
- REST api
- ScalaTest
- atomic operations / Google Guava
- HOMEWORK: Send Futures Chain / ComprehensiveFor brain twister
- HOMEWORK: Akka Framework exercise

3. LESSON THREE: Akka Parallel Processing
- ExplainFutures Chain / ComprehensiveFor brain twister
- trick with substreams per CPU
- also error handling by Either (processEvent)
- RestartSource
- hashcode and equals

4. LESSON FOUR: Case class, accompanying Object, tailrec, Either
- fold: MainFoldApp (talk about "structural sharing" in immutable collections)

5. LESSON FIVE: Implicits
- Contextual Parameters
- implicit function: ConcurrentMap.replaceWith
- implicit function: ImplicitsWhen example
- HOMEWORK: Give shopping cart exercise:
Add items to the shopping basket Remove items from the shopping basket Empty the shopping basket Additionally, we must be able to calculate the total value of the shopping basket accounting for:
Buy-one-get-one-free discounts on items 10% off on totals greater than £20 (after bogof) 2% off on total (after all other discounts) for customers with loyalty cards. We must be able to see the code running via passing tests or Terminal output.

6. LESSON SIX: Docker / webservices
- check shopping cart exercise
- Docker: Dockerfile
- Docker: port exposure
- Docker images repository
- HOMEWORK: Extend Shopping Cart exercise and move it into webservices (Play). Memory based (no persistence)

7. LESSON SEVEN: Persistence layer
- Play anorm (mention Java JPA)
- HOMEWORK: Extend Shopping Cart exercise with persistence layer (for PostgreSQL)

8. LESSON EIGHT: Unit tests
- check Shopping Cart exercise with persistence
- show example of unit tests with matchers

9. LESSON NINE: docker images
- docker Shopping Cart web-service
- HOMEWORK: Create docker compose for Shopping Cart and database images

10. LESSON TEN: docker compose 
- add Docker PostgreSQL database to docker (in docker compose)
- HOMEWORK: Create docker compose for Shopping Cart and database images

11. LESSON ELEVEN: kubernetes
- kubernetes secrets
- persistent volume claims
- ingress

12. LESSON TWELVE: Advanced Scala
- folding
- traits
- cake pattern
- LoadingCache
- higher-order function
- tailrec
HOMEWORK: try few examples with foldLeft

13. LESSON THIRTEEN: Spark
- Big Data

14. LESSON FOURTEEN: Cats
- Cats library
- show Properties Engine Spring

15. LESSON FIFTEEN: Kafka
- play-scala-kafka project
- Kafka topology
- bitnami/kafka docker-compose
HOMEWORK: add kafka consumer to shopping-cart web

16. LESSON SIXTEEN: Python Pandas
- 

17. LESSON SIXTEEN: Machine Learning
- 