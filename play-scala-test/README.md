# Task description

You are asked to implement word counting service with the below rest api (please see use cases below):

* /word/[word] - increase the counter for a given word and return this number;
* /reset - reset all word counters;
* /top/[n] - select n words with the highest counters and return a json map with (word -> counter) pairs. If several words have the same count, return all of them (note: this call might return more items than n).

There is no requirement to implement persistence - in-memory solution is fine. Application should accept only words consisting of non-empty non-whitespace ascii-only characters.

Aside from the solution itself, we also would like to see unit tests. External libraries are accepted.

# Building application and running tests

To build application use Java 11 and sbt 1.8.3 (or higher). To run tests, execute the command ```sbt test``` from root folder.

To start application on default port 9000, execute the command ```sbt run``` from root folder.

# Use cases

1) Counting words:

```curl localhost:9000/word/dog``` (response ```1```)

```curl localhost:9000/word/cat``` (response ```1```)

```curl localhost:9000/word/dog``` (response ```2```)

```curl localhost:9000/top/1``` (response ```{"dog":2}```)

2) Resetting counters:

```curl localhost:9000/word/dog``` (response ```1```)

```curl localhost:9000/word/cat``` (response ```1```)

```curl localhost:9000/reset```

```curl localhost:9000/top/1``` (response ```{}``` (counters were reset))

3) Top with multiple same counters:

```curl localhost:9000/word/dog``` (response ```1```)

```curl localhost:9000/word/dog``` (response ```2```)

```curl localhost:9000/word/cat``` (response ```1```)

```curl localhost:9000/word/cat``` (response ```2```)

```curl localhost:9000/word/cow``` (response ```1```)

```curl localhost:9000/word/cow``` (response ```2```)

```curl localhost:9000/top/1``` (response ```{"cat":2,"cow":2,"dog":2}``` (all three items are listed))

```curl localhost:9000/word/cow``` (response ```3```)

```curl localhost:9000/word/fly``` (response ```1```)

```curl localhost:9000/top/1``` (response ```{"cow":3}``` (only top word is listed))

```curl localhost:9000/top/2``` (response ```{"cat":2,"cow":3,"dog":2}``` (cat and dog are listed as they share counts))

```curl localhost:9000/top/3``` (response ```{"cat":2,"cow":3,"dog":2}``` (top three words are listed))

```curl localhost:9000/top/4``` (response ```{"fly":1,"cat":2,"cow":3,"dog":2}```)