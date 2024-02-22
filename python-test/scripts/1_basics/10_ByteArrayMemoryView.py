# memoryview objects allow Python code to access the internal data of an object
# that supports the buffer protocol without copying.


bytearray_sample = bytearray(b'abcdefghijkl')
memoryview_sample = memoryview(bytearray_sample)

print(bytes(bytearray_sample[0:1]))

#  memory views can be used to access and modify the internal data of a bytearray in a memory-efficient manner
memoryview_sample[2] = 74
print('After update:', bytearray_sample)

print('Converted to list: ', list(memoryview_sample))  # convert to list
