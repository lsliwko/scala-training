import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv
from sklearn.ensemble import RandomForestRegressor
from sklearn.linear_model import LinearRegression, BayesianRidge, LogisticRegression, SGDRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.tree import DecisionTreeRegressor
from sklearn.tree import DecisionTreeClassifier
from time import perf_counter
from sklearn.model_selection import train_test_split


print("Loading dataset...")
dataset = pd.read_csv('/Users/lsliwko/workspace/MASB-DATA/datapoint-task-merged-no-dups-cat-encoded.csv')
X = dataset.iloc[:, 4:].values
y = dataset.iloc[:, 0].values
print(f"Dataset size = {X.shape}")

# X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.8, random_state=0)

# use whole set to train
X_train = X
y_train = y
X_test = X
y_test = y
print(f"TrainSet size = {X_train.shape}")

def get_classification(available_nodes_count_tmp):
    if available_nodes_count_tmp == 1:
        return 1    # A
    elif available_nodes_count_tmp <= 500:
        return 2    # B
    elif available_nodes_count_tmp <= 1000:
        return 3    # C
    elif available_nodes_count_tmp <= 1500:
        return 4    # D
    elif available_nodes_count_tmp <= 2000:
        return 5    # E
    elif available_nodes_count_tmp <= 2500:
        return 6    # F
    elif available_nodes_count_tmp <= 3000:
        return 7    # G
    elif available_nodes_count_tmp <= 3500:
        return 8    # H
    elif available_nodes_count_tmp <= 4000:
        return 9    # I
    elif available_nodes_count_tmp <= 4500:
        return 10    # J
    elif available_nodes_count_tmp <= 5000:
        return 11    # K
    elif available_nodes_count_tmp <= 5500:
        return 12    # L
    elif available_nodes_count_tmp <= 6000:
        return 13    # M
    elif available_nodes_count_tmp <= 6500:
        return 14    # N
    elif available_nodes_count_tmp <= 7000:
        return 15    # O
    elif available_nodes_count_tmp <= 7500:
        return 15    # P
    elif available_nodes_count_tmp <= 8000:
        return 16    # Q
    elif available_nodes_count_tmp <= 8500:
        return 17    # R
    elif available_nodes_count_tmp <= 9000:
        return 18    # S
    elif available_nodes_count_tmp <= 9500:
        return 19    # T
    elif available_nodes_count_tmp <= 10000:
        return 20    # U
    elif available_nodes_count_tmp <= 10500:
        return 21    # V
    elif available_nodes_count_tmp <= 11000:
        return 22    # W
    elif available_nodes_count_tmp <= 11500:
        return 23    # X
    elif available_nodes_count_tmp <= 12000:
        return 24    # Y
    else:
        return 25    # Z


# Classifier
vfunc_get_classification = np.vectorize(get_classification)
y_train_class = vfunc_get_classification(y_train)
y_test_class = vfunc_get_classification(y_test)


model = DecisionTreeClassifier

# model = LinearRegression()
# model = DecisionTreeRegressor(random_state=0)
# model = LogisticRegression()
# model = SVR(kernel = 'rbf')
# model = RandomForestRegressor(n_estimators = 10, random_state = 0)
# model = KNeighborsRegressor(n_neighbors=5)
# model = BayesianRidge()
# model = SGDRegressor()

# try ensemble (zespol):
# https://scikit-learn.org/stable/auto_examples/ensemble/plot_gradient_boosting_regression.html

print(f"Training {str(model)}...")
start = perf_counter()
model.fit(X_train, y_train)
print(f"Trained in {(perf_counter() - start) * 1000:.0f} ms")

print(f"Predicting {str(model)}...")
start = perf_counter()
y_pred = model.predict(X_test)
print(f"Predicted in {(perf_counter() - start) * 1000:.0f} ms")

print("Saving results...")
start = perf_counter()
dataset.insert(1, "AVAILABLE NOTES PREDICTED", y_pred)
dataset.to_csv('/Users/lsliwko/workspace/MASB-DATA/datapoint-task-merged-no-dups-cat-encoded-predicted.csv',
               index=False)
print(f"Saved results in {(perf_counter() - start) * 1000:.0f} ms")



