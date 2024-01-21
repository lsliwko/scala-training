import pandas as pd
from sklearn.datasets import fetch_california_housing
from sklearn.decomposition import TruncatedSVD
from sklearn.preprocessing import StandardScaler
import seaborn as sns
import matplotlib.pyplot as plt

# What are Sparse Datasets? The features of a dataset can be sparse or dense. If the data stored for
# a particular feature contains mostly zeroes, it is referred to as a sparse feature. If the feature
# is populated mostly with non-zero values, it is dense.
# https://scikit-learn.org/stable/modules/generated/sklearn.decomposition.TruncatedSVD.html

# TODO

# Contrary to PCA, this estimator does not center the data before computing the singular value decomposition.

# https://dataaspirant.com/truncated-svd/


# Load the dataset
cal_housing = fetch_california_housing(as_frame=True)
X = cal_housing.data
y = cal_housing.target

X.to_excel('california-housing.xlsx',index=False)

# Standardize the data
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Create a TruncatedSVD object and fit the data
tsvd = TruncatedSVD(n_components=2)
X_svd = tsvd.fit_transform(X_scaled)

# Create a new DataFrame for the reduced data
data = pd.DataFrame(X_svd, columns=['SVD Component 1', 'SVD Component 2'])
data['Target'] = y

# Plot the results

sns.scatterplot(data=data, x='SVD Component 1', y='SVD Component 2', hue='Target')
plt.show()
