from sklearn import datasets
import pandas as pd

# pip3 install scikit-learn
# NOTE: Execute from terminal in IntelliJ to use virtual environment

# sklearn.show_versions()
print('-----')

# https://learn-scikit.oneoffcoder.com/visualizing.html

data_dict = datasets.load_iris()

print(type(data_dict))
print(data_dict.keys())  # dict: data, target_names, feature_names, etc.

print(f'Size: {data_dict.data.shape}')
print(f'Columns: {data_dict.feature_names}')
print(f'Targets: {data_dict.target_names}')  # or dataset['target']

# https://tutorialspoint.com/how-to-convert-sklearn-dataset-to-pandas-dataframe-in-python

df = pd.DataFrame(data_dict.data, columns=data_dict.feature_names)
df = df.astype(float)  # convert to float32

df['target'] = data_dict.target  # append target

print(df.dtypes)

df_filename = 'iris.xlsx'
df.to_excel(df_filename, index=False)
print(f'Saved file: {df_filename}')

print(df.head(5))
