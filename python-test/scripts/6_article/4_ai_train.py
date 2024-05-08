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
from sklearn.metrics import classification_report

MINI_TAG = "-mini"  # empty for full file

print("Loading dataset...")
# mini is for testing:
# head -n 3000 datapoint-task-merged-no-dups-cat-encoded.csv > datapoint-mini-task-merged-no-dups-cat-encoded.csv
dataset = pd.read_csv(f'/Users/lsliwko/workspace/MASB-DATA/datapoint{MINI_TAG}-task-merged-no-dups-cat-encoded.csv')
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
        return 'A'
    elif available_nodes_count_tmp <= 500:
        return 'B'
    elif available_nodes_count_tmp <= 1000:
        return 'C'
    elif available_nodes_count_tmp <= 1500:
        return 'D'
    elif available_nodes_count_tmp <= 2000:
        return 'E'
    elif available_nodes_count_tmp <= 2500:
        return 'F'
    elif available_nodes_count_tmp <= 3000:
        return 'G'
    elif available_nodes_count_tmp <= 3500:
        return 'H'
    elif available_nodes_count_tmp <= 4000:
        return 'I'
    elif available_nodes_count_tmp <= 4500:
        return 'J'
    elif available_nodes_count_tmp <= 5000:
        return 'K'
    elif available_nodes_count_tmp <= 5500:
        return 'L'
    elif available_nodes_count_tmp <= 6000:
        return 'M'
    elif available_nodes_count_tmp <= 6500:
        return 'N'
    elif available_nodes_count_tmp <= 7000:
        return 'O'
    elif available_nodes_count_tmp <= 7500:
        return 'P'
    elif available_nodes_count_tmp <= 8000:
        return 'Q'
    elif available_nodes_count_tmp <= 8500:
        return 'R'
    elif available_nodes_count_tmp <= 9000:
        return 'S'
    elif available_nodes_count_tmp <= 9500:
        return 'T'
    elif available_nodes_count_tmp <= 10000:
        return 'U'
    elif available_nodes_count_tmp <= 10500:
        return 'V'
    elif available_nodes_count_tmp <= 11000:
        return 'W'
    elif available_nodes_count_tmp <= 11500:
        return 'X'
    elif available_nodes_count_tmp <= 12000:
        return 'Y'
    else:
        return 'Z'


model = None

CLASSIFIER_OR_REGRESSOR = 1

# Classifier
if CLASSIFIER_OR_REGRESSOR:
    vfunc_get_classification = np.vectorize(get_classification)
    y = vfunc_get_classification(y)
    y_train = vfunc_get_classification(y_train)
    y_test = vfunc_get_classification(y_test)

    model = DecisionTreeClassifier()

else:
    model = LinearRegression()
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
if CLASSIFIER_OR_REGRESSOR:
    dataset.drop(dataset.columns[0], axis=1, inplace=True)  # drop first column
    dataset.insert(0, "AVAILABLE NODES CLASS", y)
    dataset.insert(1, "AVAILABLE NODES CLASS PREDICTED", y_pred)

    print(classification_report(y, y_pred, labels=np.unique(y)))

else:
    dataset.insert(1, "AVAILABLE NODES PREDICTED", y_pred)

dataset.to_csv(
    f'/Users/lsliwko/workspace/MASB-DATA/datapoint{MINI_TAG}-task-merged-no-dups-cat-encoded-predicted.csv',
    index=False
)
print(f"Saved results in {(perf_counter() - start) * 1000:.0f} ms")
