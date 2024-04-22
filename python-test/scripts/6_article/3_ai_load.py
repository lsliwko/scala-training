import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pyarrow.csv
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder

print("Loading file...")
dataset = pd.read_excel(
    "/Users/lsliwko/workspace/MASB/000AI-task-allocation-difficulty-paper/datapoint-task-merged.xlsx")
print("File loaded")

X = dataset.iloc[:, 4:].values  # columns 3-70 contain features
y = dataset.iloc[:, 0].values  # first column contains suitable nodes count

# print(X)

features_count = len(X[0])  # count features
print(f"Features count {features_count} Rows {dataset.shape[0]}")

# categorise features into zero-one vector
columns_list = [i for i in range(0, features_count)]
ct = ColumnTransformer(
    transformers=[('encoder', OneHotEncoder(drop='first'), columns_list)],
    remainder='passthrough',
    sparse_threshold=0,
    n_jobs=-1
)

print("Encoding")
X_cat_encoded = ct.fit_transform(X)
# print(X_cat_encoded)

print("Creating dataframe")
dataset_cat_encoded = pd.DataFrame(
    data=X_cat_encoded,
    columns=ct.get_feature_names_out()
)

# X_cat_encoded = np.array(ct.fit_transform(X))  # convert into numpy array

print("Saving file...")
dataset_cat_encoded.to_excel(
    "/Users/lsliwko/workspace/MASB/000AI-task-allocation-difficulty-paper/datapoint-task-merged-mini-encoded.xlsx",
    index=False
)
