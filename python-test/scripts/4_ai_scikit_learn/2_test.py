import matplotlib.pyplot as plt
from sklearn import datasets, decomposition

data_dict = datasets.load_iris()
print(f'Columns: {data_dict.feature_names[:3]}')


# https://stackoverflow.com/questions/1985856/how-to-make-a-3d-scatter-plot
fig1 = plt.figure()
ax1 = fig1.add_subplot(projection='3d')

scatter1 = ax1.scatter(  # scatter3D(
    xs=data_dict.data[:, 0],  # sequence containing x values
    ys=data_dict.data[:, 1],  # sequence containing y values
    zs=data_dict.data[:, 2],  # sequence containing z values
    c=data_dict.data[:, 3],  # sequence containing color values
    cmap=plt.hot(),
)

ax1.set(
    xlabel=data_dict.feature_names[0],
    ylabel=data_dict.feature_names[1],
    zlabel=data_dict.feature_names[2]
)

fig2 = plt.figure()
ax2 = fig2.add_subplot(projection="3d")

pca = decomposition.PCA(n_components=3)
pca.fit(data_dict.data)
pca_data_points = pca.transform(data_dict.data)

# Reorder the labels to have colors matching the cluster results
ax2.scatter(
    xs=pca_data_points[:, 0],
    ys=pca_data_points[:, 1],
    zs=pca_data_points[:, 2],
    c=data_dict.target
)


ax2.set(
    xlabel=f"PC1 ({pca.explained_variance_ratio_[0] * 100:.2f}%)",
    ylabel=f"PC2 ({pca.explained_variance_ratio_[1] * 100:.2f}%)",
    zlabel=f"PC3 ({pca.explained_variance_ratio_[2] * 100:.2f}%)"
)

plt.show()
