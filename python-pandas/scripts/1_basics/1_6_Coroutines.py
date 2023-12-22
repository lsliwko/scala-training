import asyncio


# https://docs.python.org/3/library/asyncio-task.html#coroutines

class MyException(Exception):
    pass


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

    await task1
    await task2


asyncio.run(my_tasks())


async def my_tasks_group():
    try:
        async with asyncio.TaskGroup() as task_group:
            # TaskGroup keeps track of all tasks
            task_group.create_task(my_coroutine("grouped-task-d"))  # , raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-e"))  # , raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-f"))
            task_group.create_task(my_coroutine("grouped-task-g"))  # , raise_exception=True))
            task_group.create_task(my_coroutine("grouped-task-h"))

        print('All tasks done')  # wait for all tasks to complete

        # except* let's selectively handle only the exceptions in the group that match a certain type (here: Exception)
    except* MyException as task_group_errors:
        print('All errors:')
        for errors in task_group_errors.exceptions:
            print(errors)


asyncio.run(my_tasks_group())
