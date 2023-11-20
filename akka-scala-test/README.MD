TASK

Consider a scenario where the application you are working on is consuming messages from a queue and sequentially processes them. Each message corresponds to an update operation on a single object (from a set of objects), and it is critical to ensure that the updates are executed in the correct order on a every object.
How would you introduce parallel processing to the existing application workflow?

Each object has an id and three attributes (a, b, c). All initial values are stored in objects.csv file. Events are stored in events.csv file. Each event instructs which object's variable should be updated.

HINTS

Use Akka Streams framework for processing events in order.

Super CSV is a framework to load csv files