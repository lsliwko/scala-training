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
# X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.8, random_state=0)

# use whole set to train
X_train = X
y_train = y
X_test = X
y_test = y
print(f"TrainSet {X_train.shape}")


print("Training linear regression...")
from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)
print("Trained")

print("Predicting...")
y_pred = regressor.predict(X_test)
print("Predicted")

print("Saving results")
dataset.insert(1, "AVAILABLE NOTES PREDICTED", y_pred)
dataset.to_csv('/Users/lsliwko/workspace/MASB-DATA/datapoint-task-merged-no-dups-cat-encoded-predicted.csv', index=False)
print("Saved results")

