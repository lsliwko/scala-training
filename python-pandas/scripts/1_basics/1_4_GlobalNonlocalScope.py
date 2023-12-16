variable_x = 0  # global scope


def outer():
    variable_x = 1  # outer scope

    def inner():
        variable_x = 2  # local scope
        print("inner:", variable_x)

    inner()
    print("outer:", variable_x)


outer()
print("global:", variable_x)
print('---')

# inner: 2
# outer: 1
# global: 0


def outer():
    variable_x = 1  # outer scope

    def inner():
        nonlocal variable_x  # outer scope (!!!)
        variable_x = 2
        print("inner:", variable_x)

    inner()
    print("outer:", variable_x)


outer()
print("global:", variable_x)
print('---')

# inner: 2
# outer: 2
# global: 0

x = 0


def outer():
    variable_x = 1  # outer scope

    def inner():
        global variable_x  # global scope(!!!)
        variable_x = 2
        print("inner:", variable_x)

    inner()
    print("outer:", variable_x)


outer()
print("global:", variable_x)
print('---')

# inner: 2
# outer: 1
# global: 2
