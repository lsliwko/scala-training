import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv

dataset = pd.read_csv('/Users/lsliwko/workspace/MASB-DATA/with-header/datapoint-task-merged-cat-encoded-1-200000.csv')
X = dataset.iloc[:, :-1].values
y = dataset.iloc[4:].values

print(X)