import numpy as np
import pandas as pd
import pyarrow.csv

from sklearn.model_selection import train_test_split
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
from sklearn.linear_model import LinearRegression

dataset = pd.read_csv('survey-results.csv', low_memory=False)

X = dataset.iloc[:, 1:].values  # all columns after first are your training data
y = dataset.iloc[:, 0].values  # first column is your result
features_count = len(X[0])

# here I am assuming you have yes / no answers, i.e. categories, not numeric values
columns_list = [i for i in range(1, features_count)]
ct = ColumnTransformer(
    transformers=[('encoder', OneHotEncoder(), columns_list)],
    remainder='passthrough',
    sparse_threshold=0,
    n_jobs=-1
)
X_cat_encoded = np.array(ct.fit_transform(X))

# here the data is split into train and test datasets
X_test, X_train, y_test, y_train = train_test_split(X_cat_encoded, y, test_size=0.2, random_state=0)

# here data is trained, I had good results with Multi-linear regression
regressor = LinearRegression()
regressor.fit(X_train, y_train)

# here we are predicting
y_test_pred = regressor.predict(X_test)
np.set_printoptions(precision=2)

# here we print the whole trained set
print(np.concatenate((y_test_pred.reshape(len(y_test_pred), 1), y_test.reshape(len(y_test), 1)), 1))
