import matplotlib.pyplot as plt
from sklearn import datasets, decomposition

# needed to run on windows
from PyQt5.QtCore import *
from PyQt5.QtGui import *


# Linear regression analysis is used to predict the value of a variable based on the value of another variable.
# The variable you want to predict is called the dependent variable (target). The variable you are using to predict
# the other variable's value is called the independent variable.

# PCA is an unsupervised method (only takes in data, no dependent variables)
# Linear regression (in general) is a supervised learning method (takes target)


# 1) Singular Value Decomposition
# https://www.youtube.com/playlist?list=PLWhu9osGd2dB9uMG5gKBARmk73oHUUQZS

# 2) Principal Component Analysis
# https://youtu.be/FgakZw6K1QQ
# https://youtu.be/HMOI_lkzW08

# Principal Component Analysis, or PCA, is a dimensionality reduction method that is often used to
# reduce the dimensionality of large data sets, by transforming a large set of variables into a smaller
# one that still contains most of the information in the large set.


# https://scikit-learn.org/stable/modules/generated/sklearn.decomposition.PCA.html
# https://scikit-learn.org/stable/auto_examples/decomposition/plot_pca_iris.html

data_dict = datasets.load_iris()
X = data_dict.data  # X - independent features (excluding target variable)
y = data_dict.target  # y - dependent variables, called (target)

# print(f'Columns: {data_dict.feature_names[:3]}')

# https://stackoverflow.com/questions/1985856/how-to-make-a-3d-scatter-plot
fig1 = plt.figure()
ax1 = fig1.add_subplot(projection='3d')

scatter1 = ax1.scatter(  # scatter3D(
    xs=X[:, 0],  # sequence containing x values
    ys=X[:, 1],  # sequence containing y values
    zs=X[:, 2],  # sequence containing z values
    c=X[:, 3],  # sequence containing color values
    cmap=plt.hot()
)

ax1.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1],
    zlabel=data_dict.feature_names[2]
)

# -----


fig2 = plt.figure()
ax2 = fig2.add_subplot(projection="3d")

pca = decomposition.PCA(n_components=3)
pca.fit(X)
for feature_name, explained_variance_ratio, in zip(pca.get_feature_names_out(), pca.explained_variance_ratio_):
    print(f"{feature_name}: {100*explained_variance_ratio:.2f}%")
print(f"Total explained variance: {100*sum(pca.explained_variance_ratio_):.2f}%")

pca_data_points = pca.transform(X)

# Reorder the labels to have colors matching the cluster results
ax2.scatter(
    xs=pca_data_points[:, 0],
    ys=pca_data_points[:, 1],
    zs=pca_data_points[:, 2],
    c=y
)

ax2.set(
    xlabel=f"PC1 ({pca.explained_variance_ratio_[0] * 100:.2f}%)",
    ylabel=f"PC2 ({pca.explained_variance_ratio_[1] * 100:.2f}%)",
    zlabel=f"PC3 ({pca.explained_variance_ratio_[2] * 100:.2f}%)"
)

plt.show()
