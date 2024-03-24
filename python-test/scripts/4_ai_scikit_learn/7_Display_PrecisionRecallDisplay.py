from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

from xgboost import XGBClassifier
from sklearn.datasets import load_wine
from sklearn.metrics import PrecisionRecallDisplay

wine = load_wine()
X, y = wine.data[wine.target<=1], wine.target[wine.target<=1]
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3,
                                                    stratify=y, random_state=42)

xgb_clf = XGBClassifier()
xgb_clf.fit(X_train, y_train)

PrecisionRecallDisplay.from_estimator(xgb_clf, X_test, y_test)
plt.show()