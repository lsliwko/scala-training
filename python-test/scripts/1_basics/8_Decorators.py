# https://www.programiz.com/python-programming/decorator

# Python decorator is a function that takes in a function and returns it by adding some functionality

def make_pretty(func):
    def inner():
        print("I got decorated")
        func()

    return inner


def ordinary():
    print("I am ordinary")


ordinary()


def make_pretty2(func):  # argument function func
    def inner():
        print("I got decorated 2")
        func()

    return inner


def make_pretty3(func):  # argument function func
    def inner():
        print("I got decorated 3")
        func()

    return inner


@make_pretty2  # calls function make_pretty2 with function ordinary2 as an argument
@make_pretty3  # calls function make_pretty3 with function ordinary2 as an argument
def ordinary2():
    print("I am ordinary 2")


ordinary2()
