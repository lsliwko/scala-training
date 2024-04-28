import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv

print("Loading dataset...")
dataset = pd.read_csv('/Users/lsliwko/workspace/MASB-DATA/datapoint-task-merged-no-dups-cat-encoded.csv')
X = dataset.iloc[:, 4:].values
y = dataset.iloc[:, 0].values
print(f"Dataset {X.shape}")

from sklearn.model_selection import train_test_split

X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.8, random_state=0)
print(f"TrainSet {X_train.shape}")

print("Training...")
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)
print("Trained")

y_pred = regressor.predict(X_test)

