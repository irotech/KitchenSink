from calculator import Calculator
import unittest
from inspect import isclass

class TestCalculator(unittest.TestCase):
    '''A class to test our simple calculator.'''

    def setUp(self):
        '''Create a calculator to use for testing.'''
        self.calc = Calculator()
        
    def test_pow(self):
        '''Test raising a number to a power.'''
        self.assertEqual(self.calc.pow(2, 3), 8)

    def test_add(self):
        '''Test adding 2 numbers.'''

    def test_sub(self):
        '''Test substracting 2 numbers.'''

    def test_mult(self):
        '''Test multiplying 2 numbers.'''

unittest.main()
 
