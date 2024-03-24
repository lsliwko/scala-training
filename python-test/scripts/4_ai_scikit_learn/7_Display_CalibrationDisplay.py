from sklearn.calibration import CalibrationDisplay
from sklearn.model_selection import train_test_split
from sklearn.datasets import make_classification
from sklearn.pipeline import make_pipeline
from sklearn.ensemble import HistGradientBoostingClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
import matplotlib.pyplot as plt

X, y = make_classification(n_samples=1000,
                           n_classes=2, n_features=5,
                           random_state=42)
X_train, X_test, y_train, y_test = train_test_split(X, y,
                                                    test_size=0.3, random_state=42)
proba_clf = make_pipeline(StandardScaler(),
                          SVC(kernel="rbf", gamma="auto",
                              C=10, probability=True))
proba_clf.fit(X_train, y_train)

CalibrationDisplay.from_estimator(proba_clf,
                                  X_test, y_test)

hist_clf = HistGradientBoostingClassifier()
hist_clf.fit(X_train, y_train)

ax = plt.gca()
CalibrationDisplay.from_estimator(hist_clf,
                                  X_test, y_test,
                                  ax=ax)
plt.show()
