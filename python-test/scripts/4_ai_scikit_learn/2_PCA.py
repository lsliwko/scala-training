import numpy as np
from sklearn import datasets
from sklearn.decomposition import PCA


# 1) Singular Value Decomposition
# https://www.youtube.com/playlist?list=PLWhu9osGd2dB9uMG5gKBARmk73oHUUQZS


# 2) Principal Component Analysis, or PCA, is a dimensionality reduction method that is often used to
# reduce the dimensionality of large data sets, by transforming a large set of variables into a smaller
# one that still contains most of the information in the large set.

# https://youtu.be/FgakZw6K1QQ
# https://youtu.be/HMOI_lkzW08


# https://scikit-learn.org/stable/modules/generated/sklearn.decomposition.PCA.html

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names[:2]}')

pca = PCA(n_components=2)
pca.fit(data_dict.data)

print(pca)

