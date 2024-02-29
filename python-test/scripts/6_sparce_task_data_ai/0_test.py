import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv

dataset = pd.read_csv('datapoint-task-test-mini.csv', low_memory=False)
# dataset = pd.read_csv('datapoint-task-full.csv', low_memory=False)

# print(dataset.columns[0:4].all(['AVAILABLE NODES COUNT', 'TASK ID', 'CPU REQUIRED', 'MEMORY REQUIRED']))
# sanity check
if list(dataset.columns[0:4]) != ['AVAILABLE NODES COUNT', 'TASK ID', 'CPU REQUIRED', 'MEMORY REQUIRED']:
    raise Exception('illegal columns')

