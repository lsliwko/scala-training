
string_repr = repr('string')
print(f"REPR: {string_repr}")

list_repr = repr(['a','b','c'])
print(f"REPR: {list_repr}")

dict_repr = repr({'a':1,'b':2,'c':3})
print(f"REPR: {dict_repr}")

string_sample = eval(string_repr)
list_sample = eval(list_repr)
dict_sample = eval(dict_repr)



