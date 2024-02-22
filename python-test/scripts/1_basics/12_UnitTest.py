import unittest
from functions_module import add, multiply


# https://www.freecodecamp.org/news/how-to-write-unit-tests-for-python-functions/
class TestClass(unittest.TestCase):

    def test_add(self):
        self.assertEqual(6, add(2, 4))

    def test_multiply(self):
        self.assertEqual(0, multiply(2, 4))


# run tests by directly running the Python module containing the tests
# python 12_Unit_Test
if __name__ == '__main__':
    unittest.main()
