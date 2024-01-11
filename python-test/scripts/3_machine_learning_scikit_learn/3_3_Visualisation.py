from sklearn import datasets
import matplotlib.pyplot as plt

# pip3 install matplotlib

data_dict = datasets.load_iris()

# https://scikit-learn.org/stable/auto_examples/datasets/plot_iris_dataset.html

_, ax = plt.subplots()

# 2D image, column 0 and 1
scatter = ax.scatter(
    x=data_dict.data[:, 0],
    y=data_dict.data[:, 1],
    c=data_dict.target
)

# 2D image, column 0 and 1
ax.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1]
)

_ = ax.legend(
    scatter.legend_elements()[0], data_dict.target_names, loc="lower right", title="Classes"
)
