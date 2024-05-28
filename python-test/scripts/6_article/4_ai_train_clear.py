import numpy as np
import pandas as pd
from time import perf_counter
from sklearn.ensemble import VotingClassifier
from sklearn.linear_model import RidgeClassifier, SGDClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score

# load datapoints
dataset = pd.read_csv(f'/Users/lsliwko/workspace/MASB-DATA/datapoint-task-merged-no-dups-cat-encoded.csv')
X = dataset.iloc[:, 4:].values  # columns 4+ contain features
y = dataset.iloc[:, 0].values  # nodes counts' are in the first column

X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.9, random_state=42)

# group classification function
def get_group_classification(count):
    if count <= 1: return 'A'
    elif count > 12000: return 'Z'
    return chr((int(count) - 1) // 500 + 66)

# converts counts to groups
vfunc_get_group_classification = np.vectorize(get_group_classification)
y_train = vfunc_get_group_classification(y_train)
y_test = vfunc_get_group_classification(y_test)

model = VotingClassifier(
    estimators=[
        ('Neural-Network',
         MLPClassifier(hidden_layer_sizes=(30, 30),
                       max_iter=200,
                       random_state=42)),
        ('Ridge-Regression',
         RidgeClassifier(alpha=0.3,
                         fit_intercept=False,
                         random_state=42)),
        ('SGDClassifier',
         SGDClassifier(fit_intercept=False,
                       max_iter=100,
                       random_state=42))
    ],
    voting='hard',
    n_jobs=-1
)

print(f"Training {str(model)}...")
start = perf_counter()
model.fit(X_train, y_train)
print(f"Trained in {(perf_counter() - start) * 1000:.0f} ms")

print(f"Predicting {str(model)}...")
start = perf_counter()
y_pred = model.predict(X_test)
print(f"Predicted in {(perf_counter() - start) * 1000:.0f} ms")

y_true = y_test
print(f"Accuracy: {accuracy_score(y_true, y_pred)}")

print(classification_report(y_true, y_pred,
                            digits=4,
                            zero_division=0,
                            labels=np.unique(y_true)))

unique_label = np.unique([y_true, y_pred])
confusion_matrix_pd = pd.DataFrame(
    confusion_matrix(y_true, y_pred, labels=unique_label),
    index=['true:{:}'.format(x) for x in unique_label],
    columns=['pred:{:}'.format(x) for x in unique_label]
)
print(confusion_matrix_pd.to_string())
