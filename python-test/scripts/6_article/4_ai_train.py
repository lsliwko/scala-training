import sklearn
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.ensemble import RandomForestRegressor, RandomForestClassifier, AdaBoostClassifier, BaggingClassifier, \
    VotingClassifier
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RBF
from sklearn.linear_model import LinearRegression, BayesianRidge, LogisticRegression, SGDRegressor, Perceptron, \
    RidgeClassifierCV, RidgeClassifier, ElasticNet, Lasso, SGDClassifier
from sklearn.naive_bayes import GaussianNB, BernoulliNB, MultinomialNB, CategoricalNB, ComplementNB
from sklearn.neighbors import KNeighborsRegressor, KNeighborsClassifier, NearestCentroid
from sklearn.neural_network import MLPClassifier
from sklearn.svm import SVC, NuSVC
from sklearn.tree import DecisionTreeRegressor, ExtraTreeClassifier
from sklearn.tree import DecisionTreeClassifier
from time import perf_counter
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score

print(f"The scikit-learn version: {sklearn.__version__}")

MINI_TAG = ""  # "-mini"  # empty for full file
RANDOM_STATE = None # 42

print("Loading dataset...")
# mini is for testing:
# head -n 2001 datapoint-task-merged-no-dups-cat-encoded.csv > datapoint-mini-task-merged-no-dups-cat-encoded.csv
dataset = pd.read_csv(f'/Users/lsliwko/workspace/MASB-DATA/datapoint{MINI_TAG}-task-merged-no-dups-cat-encoded.csv')
X = dataset.iloc[:, 4:].values
y = dataset.iloc[:, 0].values
print(f"Dataset size = {X.shape}")

DATASET_TRAIN_TEST_SPLIT_FLAG = True

if DATASET_TRAIN_TEST_SPLIT_FLAG:
    X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.75, random_state=42)
else:
    # use whole set to train
    X_train = X
    y_train = y
    X_test = X
    y_test = y
print(f"TrainSet size = {X_train.shape}")
print(f"TestSet size = {X_test.shape}")


def get_classification(count):
    if count <= 1:
        return 'A'
    elif count > 12000:
        return 'Z'
    return chr((int(count) - 1) // 500 + 66)

CLASSIFIER_OR_REGRESSOR_FLAG = True

# Classifier
model = None
if CLASSIFIER_OR_REGRESSOR_FLAG:
    vfunc_get_classification = np.vectorize(get_classification)
    y_train = vfunc_get_classification(y_train)
    y_test = vfunc_get_classification(y_test)

    for i in range(1,10):
        # https://scikit-learn.org/stable/auto_examples/classification/plot_classifier_comparison.html

        X_test, X_train, y_test, y_train = train_test_split(X, y, test_size=0.75)
        y_train = vfunc_get_classification(y_train)
        y_test = vfunc_get_classification(y_test)

        # DONE model = KNeighborsClassifier(n_neighbors=3, weights="distance")  # Nearest Neighbors
        # DONE model = DecisionTreeClassifier(max_depth=15, max_features=None, class_weight="balanced", random_state=RANDOM_STATE)  # Decision Tree classifier
        # DONE model = RandomForestClassifier(max_depth=10, n_estimators=20, max_features=None, class_weight="balanced", random_state=RANDOM_STATE)  # Random Forest
        # DONE model = MLPClassifier(hidden_layer_sizes=(30, 30), max_iter=200, random_state=RANDOM_STATE)  # Artificial Neural Network
        # DONE model = Perceptron(max_iter=100, random_state=RANDOM_STATE)
        # DONE model = GaussianNB()
        # DONE model = ComplementNB(alpha=0.3)
        # DONE model = NearestCentroid()
        # DONE model = RidgeClassifier(alpha=0.3, fit_intercept=False, random_state=RANDOM_STATE)
        # DONE model = SGDClassifier(fit_intercept=False, max_iter=250, loss="modified_huber", random_state=RANDOM_STATE)

        # import inspect
        # from sklearn.utils.testing import all_estimators
        # for name, clf in all_estimators(type_filter='classifier'):
        #     if 'sample_weight' in inspect.getargspec(clf().fit)[0]: print name
        # BernoulliNB,DecisionTreeClassifier,ExtraTreeClassifier,ExtraTreesClassifier,MultinomialNB,NuSVC,Perceptron,RandomForestClassifier,RidgeClassifierCV,SGDClassifier,SVC
        # DONE model = AdaBoostClassifier(estimator=ExtraTreeClassifier(splitter="random", class_weight="balanced", random_state=RANDOM_STATE), n_estimators=30, random_state=RANDOM_STATE)    # AdaBoost

        model = BaggingClassifier(estimator=ExtraTreeClassifier(splitter="random", class_weight="balanced", random_state=RANDOM_STATE), n_estimators=20, bootstrap=False, n_jobs=-1, random_state=RANDOM_STATE)

        model.fit(X_train, y_train)
        y_pred = model.predict(X_test)
        line = classification_report(y_test, y_pred, digits=4, zero_division=0, labels=np.unique(y_test))
        print(line.splitlines(keepends=False)[2:4])


    '''
    model = VotingClassifier(
        estimators=[
            ('Random-Forest',
             RandomForestClassifier(max_depth=10,
                                    n_estimators=20,
                                    max_features=None,
                                    class_weight="balanced",
                                    random_state=RANDOM_STATE)
            ),
            ('Neural-Network',
             MLPClassifier(hidden_layer_sizes=(30, 30),
                           max_iter=200,
                           random_state=RANDOM_STATE)
            ),
            ('Ridge-Regression',
             RidgeClassifier(alpha=0.3,
                             fit_intercept=False,
                             random_state=RANDOM_STATE)
            ),
            ('Linear Support Vector Machine with Stochastic Gradient Descent',
             SGDClassifier(fit_intercept=False,
                           max_iter=100,
                           random_state=RANDOM_STATE)
            )
        ],
        voting='hard',
        n_jobs=-1
    )
    '''

    # estimators=[
    #     ('Random-Forest', RandomForestClassifier(max_depth=10, n_estimators=20, max_features=None, class_weight="balanced", random_state=RANDOM_STATE)),
    #     ('Neural-Network', MLPClassifier(hidden_layer_sizes=(30, 30), max_iter=200, random_state=RANDOM_STATE)),
    #     ('Ridge-Regression', RidgeClassifier(alpha=0.3, fit_intercept=False, random_state=RANDOM_STATE)),
    #     ('Linear Support Vector Machine with Stochastic Gradient Descent', SGDClassifier(fit_intercept=False, max_iter=100, random_state=RANDOM_STATE))
    # ],

    # DOES NOT WORK model = GaussianProcessClassifier()
    # DOES NOT WORK model = QuadraticDiscriminantAnalysis()
    # DOES NOT WORK model = SVC(kernel="linear", C=0.025, random_state=RANDOM_STATE)    # Linear SVM
    # DOES NOT WORK model = SVC(kernel="rbf") # , gamma=2, C=1, random_state=RANDOM_STATE)    # RBF SVM
    # DOES NOT WORK model = NuSVC()    # RBF SVM

    # AdaBoostClassifier,
    # ExtraTreeClassifier,
    # ExtraTreesClassifier,

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
print('-----')
if CLASSIFIER_OR_REGRESSOR_FLAG:
    y_true = y_test

    print(f"Accuracy: {accuracy_score(y_true, y_pred)}")
    print('-----')

    print(classification_report(y_true, y_pred, digits=4, zero_division=0, labels=np.unique(y_true)))
    print('-----')

    '''
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
    '''

    # for index, (val_y_true, val_y_pred) in enumerate(zip(y_true, y_pred)):
    #     if val_y_true != val_y_pred:
    #         print(f"Difference at row {index + 2}: {val_y_true} <> {val_y_pred}")
    # print('-----')
else:
    pass


SAVE_RESULTS_FLAG = False

if SAVE_RESULTS_FLAG:

    print(f"Predicting full dataset (for results save) {str(model)}...")
    start = perf_counter()
    y_pred = model.predict(X)
    print(f"Predicted full dataset (for results save) in {(perf_counter() - start) * 1000:.0f} ms")

    print("Saving results...")
    start = perf_counter()

    if CLASSIFIER_OR_REGRESSOR_FLAG:
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
