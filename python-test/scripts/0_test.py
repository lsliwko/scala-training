import numpy as np

x = np.array([1, 0, 0, 1, 0])

reverse_func = lambda t: int(t == False)
reverse_func_np = np.vectorize(reverse_func)
x = reverse_func_np(x)

print(reverse_func(0))
print(reverse_func(1))

print(x)
