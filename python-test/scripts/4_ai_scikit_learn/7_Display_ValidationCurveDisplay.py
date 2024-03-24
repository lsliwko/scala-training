import numpy as np
from sklearn.datasets import make_classification
import matplotlib.pyplot as plt

from sklearn.model_selection import ValidationCurveDisplay
from sklearn.linear_model import LogisticRegression

X, y = make_classification(n_samples=1000, n_classes=2, n_features=10,
                           n_informative=2, n_redundant=0, n_repeated=0)

param_name, param_range = "C", np.logspace(-8, 3, 10)
lr_clf = LogisticRegression()

ValidationCurveDisplay.from_estimator(lr_clf, X, y,
                                      param_name=param_name,
                                      param_range=param_range,
                                      scoring='f1_weighted',
                                      cv=5, n_jobs=-1)
plt.show()
