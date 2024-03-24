from sklearn.datasets import make_classification
from sklearn.decomposition import PCA
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import GridSearchCV
from sklearn.pipeline import make_pipeline
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler

import time

X, y = make_classification(n_samples=1000,
                           n_classes=2, n_features=5,
                           random_state=42)

pipeline = Pipeline(steps=[('scaler', StandardScaler()),
                           ('pca', PCA()),
                           ('estimator', RandomForestClassifier())])

param_grid = {'pca__n_components': [2, 'mle'],
              'estimator__n_estimators': [3, 5, 7],
              'estimator__max_depth': [3, 5]}

start = time.perf_counter()
clf = GridSearchCV(pipeline, param_grid=param_grid, cv=5, n_jobs=4)
clf.fit(X, y)

print(f"It takes {time.perf_counter() - start} seconds to finish the search.")
