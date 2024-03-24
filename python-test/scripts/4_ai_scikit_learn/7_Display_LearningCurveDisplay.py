import numpy as np
from sklearn.datasets import make_classification
import matplotlib.pyplot as plt

from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.model_selection import LearningCurveDisplay

X, y = make_classification(n_samples=1000, n_classes=2, n_features=10,
                           n_informative=2, n_redundant=0, n_repeated=0)

tree_clf = DecisionTreeClassifier(max_depth=3, random_state=42)
gb_clf = GradientBoostingClassifier(n_estimators=50, max_depth=3, tol=1e-3)

train_sizes = np.linspace(0.4, 1.0, 10)
fig, axes = plt.subplots(1, 2, figsize=(10, 4))
LearningCurveDisplay.from_estimator(tree_clf, X, y,
                                    train_sizes=train_sizes,
                                    ax=axes[0],
                                    scoring='accuracy')
axes[0].set_title('DecisionTreeClassifier')
LearningCurveDisplay.from_estimator(gb_clf, X, y,
                                    train_sizes=train_sizes,
                                    ax=axes[1],
                                    scoring='accuracy')
axes[1].set_title('GradientBoostingClassifier')
plt.show()