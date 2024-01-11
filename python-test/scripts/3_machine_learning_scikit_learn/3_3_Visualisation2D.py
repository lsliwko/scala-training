from sklearn import datasets
import matplotlib.pyplot as plt

# pip3 install matplotlib

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names[:2]}')

# https://scikit-learn.org/stable/auto_examples/datasets/plot_iris_dataset.html


# https://matplotlib.org/stable/api/_as_gen/matplotlib.pyplot.subplots.html
fig, ax = plt.subplots()

# create scatter 2D image, column 0 and 1
scatter = ax.scatter(
    x=data_dict.data[:, 0],
    y=data_dict.data[:, 1],
    c=data_dict.target
)

ax.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1]
)

# ax.legend([line1, line2, line3], ['label1', 'label2', 'label3'])
ax.legend(
    scatter.legend_elements()[0],
    data_dict.target_names,
    # loc="lower right",
    # title="Targets"
)

ax.grid(True)


plt.show(block=True)
