from sklearn import datasets
import matplotlib.pyplot as plt

# pip3 install matplotlib

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names[:3]}')

# https://stackoverflow.com/questions/1985856/how-to-make-a-3d-scatter-plot
fig = plt.figure()
ax = fig.add_subplot(projection='3d')

scatter = ax.scatter(   # scatter3D(
    xs=data_dict.data[:, 0],  # sequence containing x values
    ys=data_dict.data[:, 1],  # sequence containing y values
    zs=data_dict.data[:, 2],  # sequence containing z values
    c=data_dict.data[:, 3],  # sequence containing color values
    cmap=plt.hot(),
)

ax.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1],
    zlabel=data_dict.feature_names[2]
)

colorbar = fig.colorbar(scatter)
colorbar.set_label(data_dict.feature_names[3], rotation=270)

ax.grid(True)

plt.show()
