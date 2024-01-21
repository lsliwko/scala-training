import numpy as np
import cv2
import os

# pip3 install numpy
# pip3 install opencv-python

# NumPy - Numerical Python
# Numpy adds support for large, multidimensional matrices and arrays, along with a gigantic
# collection of top-end mathematical functions to operate on these arrays and matrices.

# NumPy is thin wrapper over C code:
# https://stackoverflow.com/questions/1825857/how-much-of-numpy-and-scipy-is-in-c

array_test = np.array([1, 2, 3, 4, 5])

array_test.mean()
np.median(array_test)

print(array_test)
print(type(array_test))

# https://numpy.org/doc/stable/reference/arrays.ndarray.html
ndarray_test = np.array([[1, 2, 3], [4, 5, 6]], np.int32)

print(ndarray_test.shape)
print(ndarray_test.dtype)

# https://stackoverflow.com/questions/39762019/how-to-read-binary-files-in-python-using-numpy
size_x = 256  # dimensions
size_y = 256
with open('test.bin', 'wb') as file_out:
    file_out.write(os.urandom(size_x * size_y))  # randomise bytes
    print(f'File stored: {file_out.name}')

filearray_test = np.fromfile('test.bin', dtype='uint8')
cv2.imwrite('test.png', filearray_test[:size_x * size_y].reshape(size_x, size_y))
