def my_function():
    return 1


def my_function_with_arguments(name):
    return f"My name is {name}"


def my_function_with_arguments_2(name: str, age: int) -> str:
    return f"My name is {name}. I am {age} years old"


print(my_function_with_arguments_2("Les", 10))

print(my_function_with_arguments_2(name="Les", age=100))


def test_function_with_docstring(p1, p2, p3):
    """
    test_function does blah blah blah.

    :param p1: describe about parameter p1
    :param p2: describe about parameter p2
    :param p3: describe about parameter p3
    :return: describe what it returns
    """
    return f"{p1} {p2} {p3}"


list_sample = [1, 2, 3, 4, 5, 6, 7, 8, 9]
even_list = []
for x in list_sample:
    if x % 2 == 0:  # remainder divide
        even_list.append(x)

print(even_list)

# comprehension
even_list_2 = [x for x in list_sample if x % 2 == 0]
print(even_list_2)

# a lambda function is a small anonymous function
plus_five_function = lambda arg: arg + 10
print(plus_five_function(5))

multiply_function = lambda arg1, arg2: arg1 * arg2
print(multiply_function(5, 6))


def multiply_function2(arg1: int, arg2: int) -> int:
    return arg1 * arg2


print(multiply_function2(5, 6))

# dictionary comprehension
list_sample = [1, 2, "a"]
dict_sample = {
    f"Item {index+1}": f"Value [{element}]"  # key : value
    for index, element in enumerate(list_sample)
}

print(dict_sample)

