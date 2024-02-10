import asyncio
from threading import Thread


def my_function(a,b):
    print(f'{a} {b}')


thread = Thread(target=my_function, args=[1, "x"])
thread.start()



# https://docs.python.org/3/library/asyncio-task.html#coroutines
# https://stackoverflow.com/questions/34753401/difference-between-coroutine-and-future-task-in-python-3-5

# A coroutine is a generator function that can both yield values and accept values from the outside. The benefit of
# using a coroutine is that we can pause the execution of a function and resume it later. In case of a network
# operation, it makes sense to pause the execution of a function while we're waiting for the response. We can use the
# time to run some other functions.

class MyException(Exception):
    pass  # Empty code is not allowed in loops, function definitions, class definitions, or in if statements


# coroutine
async def my_coroutine(id: str, raise_exception: bool = False):
    print(f'{id}: start')
    await asyncio.sleep(2)
    if raise_exception:
        error = f'{id}: Exception'
        raise MyException(error)

    print(f'{id}: end')


# my_coroutine() calling my_coroutine() will not schedule it to be executed
asyncio.run(my_coroutine("coroutine-a"))


# tasks are executed in parallel
async def my_tasks():
    task1 = asyncio.create_task(my_coroutine("task-b"))
    task2 = asyncio.create_task(my_coroutine("task-c"))

    # task2.cancel()

    await task1
    await task2


asyncio.run(my_tasks())


async def my_tasks_group():
    try:
        async with asyncio.TaskGroup() as task_group:
            # TaskGroup keeps track of all tasks
            task_group.create_task(my_coroutine("grouped-task-d", raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-e", raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-f"))
            task_group.create_task(my_coroutine("grouped-task-g", raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-h"))
            # task_group._abort() unofficial way of cancelling task group

        # upon exiting scope, task group will wait till all tasks are completed
        print('All tasks done')  # wait for all tasks to complete

        # except* let's selectively handle only the exceptions in the group that match a certain type (here: Exception)
    except* MyException as task_group_errors:
        print('All errors:')
        for errors in task_group_errors.exceptions:
            print(errors)


asyncio.run(my_tasks_group())
