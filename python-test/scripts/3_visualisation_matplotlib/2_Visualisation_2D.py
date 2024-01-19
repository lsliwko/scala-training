from sklearn import datasets
import matplotlib.pyplot as plt

# needed to run on windows
import PyQt5.QtCore

# pip3 install matplotlib

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names[:2]}')

# https://scikit-learn.org/stable/auto_examples/datasets/plot_iris_dataset.html

# https://matplotlib.org/stable/api/_as_gen/matplotlib.pyplot.subplots.html
fig, ax = plt.subplots()

plt.cla()  # clear current axes

# create scatter 2D image, column 0 and 1
scatter = ax.scatter(
    x=data_dict.data[:, 0],  # sequence containing x values
    y=data_dict.data[:, 1],  # sequence containing y values
    c=data_dict.target  # sequence containing colors index
)

# for data_point, target in zip(data_dict.data, data_dict.target):
#     label = data_dict.target_names[target]
#     ax.text(data_point[0], data_point[1], label)

ax.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1]
)

# ax.legend([line1, line2, line3], ['label1', 'label2', 'label3'])
ax.legend(
    scatter.legend_elements()[0],
    data_dict.target_names
)

ax.grid(True)

plt.show()
