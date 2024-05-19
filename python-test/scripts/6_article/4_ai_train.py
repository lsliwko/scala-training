import sklearn
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.ensemble import RandomForestRegressor, RandomForestClassifier, AdaBoostClassifier
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.linear_model import LinearRegression, BayesianRidge, LogisticRegression, SGDRegressor
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsRegressor, KNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeRegressor
from sklearn.tree import DecisionTreeClassifier
from time import perf_counter
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score

print(f"The scikit-learn version: {sklearn.__version__}")

MINI_TAG = ""  # "-mini"  # empty for full file

print("Loading dataset...")
# mini is for testing:
# head -n 2001 datapoint-task-merged-no-dups-cat-encoded.csv > datapoint-mini-task-merged-no-dups-cat-encoded.csv
dataset = pd.read_csv(f'/Users/lsliwko/workspace/MASB-DATA/datapoint{MINI_TAG}-task-merged-no-dups-cat-encoded.csv')
X = dataset.iloc[:, 4:].values
y = dataset.iloc[:, 0].values
print(f"Dataset size = {X.shape}")

X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.8, random_state=0)

# use whole set to train
# X_train = X
# y_train = y
# X_test = X
# y_test = y
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

    # https://scikit-learn.org/stable/auto_examples/classification/plot_classifier_comparison.html

    model = KNeighborsClassifier(n_neighbors=4)  # Nearest Neighbors
    # model = SVC(kernel="linear", C=0.025, random_state=42)    # Linear SVM
    # model = SVC(gamma=2, C=1, random_state=42)    # RBF SVM
    # model = GaussianProcessClassifier(1.0 * RBF(1.0), random_state=42)    # Gaussian Process
    # model = DecisionTreeClassifier(max_depth=5, random_state=42)   # Decision Tree classifier
    # model = RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1, random_state=42) # Random Forest
    # model = MLPClassifier(alpha=1, max_iter=1000, random_state=42)    # Neural Net
    # model = AdaBoostClassifier(algorithm="SAMME", random_state=42)    # AdaBoost
    # model = GaussianNB() # Naive Bayes
    # model = QuadraticDiscriminantAnalysis() # QDA

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

print(f"Accuracy report for {str(model)}...")
if CLASSIFIER_OR_REGRESSOR:
    # print(accuracy_score(y, y_pred))
    # print('-----')
    y_true = y_test

    print(classification_report(y_true, y_pred, digits=6, zero_division=0, labels=np.unique(y_true)))
    print('-----')

    # print(confusion_matrix(y, y_pred, labels=np.unique(y)))
    # https://stackoverflow.com/questions/50325786/sci-kit-learn-how-to-print-labels-for-confusion-matrix
    unique_label = np.unique([y_true, y_pred])
    confusion_matrix_pd = pd.DataFrame(
        confusion_matrix(y_true, y_pred, labels=unique_label),
        index=['true:{:}'.format(x) for x in unique_label],
        columns=['pred:{:}'.format(x) for x in unique_label]
    )
    print(confusion_matrix_pd.to_string())
    print('-----')

    # for index, (val_y_true, val_y_pred) in enumerate(zip(y_true, y_pred)):
    #     if val_y_true != val_y_pred:
    #         print(f"Difference at row {index + 2}: {val_y_true} <> {val_y_pred}")
    # print('-----')
else:
    pass

SAVE_RESULTS = False

if SAVE_RESULTS:

    print(f"Predicting full dataset (for results save) {str(model)}...")
    start = perf_counter()
    y_pred = model.predict(X)
    print(f"Predicted full dataset (for results save) in {(perf_counter() - start) * 1000:.0f} ms")

    print("Saving results...")
    start = perf_counter()

    if CLASSIFIER_OR_REGRESSOR:
        dataset.drop(dataset.columns[0], axis=1, inplace=True)  # drop first column
        dataset.insert(0, "AVAILABLE NODES CLASS", y)
        dataset.insert(1, "AVAILABLE NODES CLASS PREDICTED", y_pred)
    else:
        dataset.insert(1, "AVAILABLE NODES PREDICTED", y_pred)

    dataset.to_csv(
        f'/Users/lsliwko/workspace/MASB-DATA/datapoint{MINI_TAG}-task-merged-no-dups-cat-encoded-predicted.csv',
        index=False
    )
    print(f"Saved results in {(perf_counter() - start) * 1000:.0f} ms")
