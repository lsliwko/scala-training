from collections import defaultdict

# !!! READ ABOUT IMPORT STRUCTURES !!!
# https://docs.python.org/3/reference/import.html
# https://stackoverflow.com/questions/9439480/from-import-vs-import

# Link:
# https://thomas-cokelaer.info/tutorials/python/data_structures.html

# Lists are enclosed in brackets
# ordered, non-unique
list_sample = [1, 2, "a"]
list_sample.append("b")
list_sample.append("3")

print(list_sample)

# Set only unique values, unordered
set_sample = {"apple", "banana", "cherry", "apple"}

print(set_sample)

# Dictionaries are built with curly brackets
# keys unique, maintains insertion order since Python 3.7
dict_sample = {"a": 1, "b": 2}

dict_sample.keys()
dict_sample.values()

dict_sample["a"]  # same as dict_sample.get("a")
dict_sample.pop("a")  # removes key->value pair and returns value for key "a"

value = dict_sample.setdefault("c", 0)  # sets value if missing, return value
# dict_sample.clear()

# update dictionary from another dictionary
# dict_sample.update({'a':1, 'b':2})

print(dict_sample)


# dictionaries can be compared, i.e. dict_sample == {'a':1, 'b':2}

def default_value():
    return 0


defaultdict_sample = defaultdict(default_value)  # 0 is default value (function)
# or defaultdict(lambda: 0)

# print(dict_sample["key-dont-exist"]) # error
print(defaultdict_sample["key-dont-exist"])  # lambda function

# Tuples are enclosed in parentheses
tuple = (1, 2, "a")


# useful for returning n-results from function
def multiple_results_function():
    return 1, 2, 3


(valueOne, valueTwo, valueThree) = multiple_results_function()
print(f'Values are {valueOne}, {valueTwo} and {valueThree}')


# string operations
string_test = 'abcdefghijiklmnopqrst'
print(f'{string_test.upper()}')  # capitalise
print(f'{string_test[:10]}')  # first 10 characters
print(f'{string_test[5:]}')  # all characters after 5th
