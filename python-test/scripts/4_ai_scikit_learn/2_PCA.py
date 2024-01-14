import numpy as np
from sklearn import datasets
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt

# 1) Singular Value Decomposition
# https://www.youtube.com/playlist?list=PLWhu9osGd2dB9uMG5gKBARmk73oHUUQZS


# 2) Principal Component Analysis, or PCA, is a dimensionality reduction method that is often used to
# reduce the dimensionality of large data sets, by transforming a large set of variables into a smaller
# one that still contains most of the information in the large set.

# https://youtu.be/FgakZw6K1QQ
# https://youtu.be/HMOI_lkzW08


# https://scikit-learn.org/stable/modules/generated/sklearn.decomposition.PCA.html
# https://scikit-learn.org/stable/auto_examples/decomposition/plot_pca_iris.html

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names}')

pca = PCA(n_components=3)  # number of PC1, PC2, PC3, ...
pca.fit(data_dict.data)

pca_data_points = pca.transform(data_dict.data)  # apply reduction

print(pca_data_points)
