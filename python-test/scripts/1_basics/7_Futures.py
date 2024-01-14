import asyncio


# https://docs.python.org/3/library/asyncio-task.html#coroutines
# https://stackoverflow.com/questions/34753401/difference-between-coroutine-and-future-task-in-python-3-5

# A future is like the Promise objects from Javascript. It is like a placeholder for a value that will be
# materialized in the future. In the above-mentioned case, while waiting on network I/O, a function can give us a
# container, a promise that it will fill the container with the value when the operation completes. We hold on to the
# future object and when it's fulfilled, we can call a method on it to retrieve the actual result.

# https://docs.python.org/3/library/asyncio-future.html

async def set_after(fut, delay, value):
    # Sleep for *delay* seconds.
    await asyncio.sleep(delay)

    # Set *value* as a result of *fut* Future.
    fut.set_result(value)


async def main():
    # Get the current event loop
    loop = asyncio.get_running_loop()

    # Create a new Future object
    fut = loop.create_future()

    # Run "set_after()" coroutine in a parallel Task.
    # We are using the low-level "loop.create_task()" API here because
    # we already have a reference to the event loop at hand.
    # Otherwise, we could have just used "asyncio.create_task()".
    loop.create_task(set_after(fut, 1, '... world'))

    print('hello ...')

    # Wait until *fut* has a result (1 second) and print it.
    print(await fut)


asyncio.run(main())
