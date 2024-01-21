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


# Contrary to PCA, this estimator does not center the data before computing the singular value decomposition.
# https://dataaspirant.com/truncated-svd/


# https://www.geeksforgeeks.org/classification-of-text-documents-using-sparse-features-in-python-scikit-learn/


from sklearn.datasets import fetch_20newsgroups
from sklearn.feature_extraction.text import TfidfVectorizer

# importing train and test data
news_data = fetch_20newsgroups(
    subset="test",
    categories=["sci.electronics", "sci.space"],
    shuffle=False,
    random_state=4,
    remove=("headers", "footers", "quotes"),
)


# https://scikit-learn.org/stable/modules/generated/sklearn.feature_extraction.text.TfidfVectorizer.html
# initialize vectorizer - keeping the
# range of document frequency [0.5,5]
vectorizer = TfidfVectorizer(
    sublinear_tf=True,
    lowercase=True,
    decode_error="ignore",
    max_df=0.5,  # ignore words that have a document frequency higher than the given threshold
    min_df=5,  # ignore words that have a document frequency lower than the given threshold.
    stop_words="english",
    token_pattern=u'(?ui)\\b\\w*[a-z]+\\w*\\b'  # must contain letter, no numbers
)

# alternative to token_patter to remove numbers:
# https://scikit-learn.org/stable/auto_examples/bicluster/plot_bicluster_newsgroups.html

X = vectorizer.fit_transform(news_data.data)
y = news_data.target

feature_names = vectorizer.get_feature_names_out()
print(f'Feature names: {feature_names}')

news_data_file="newgroup-sparce-data.xlsx"
print(f"Saving to {news_data_file}: {X.shape}")
df = pd.DataFrame(columns=feature_names, data=X.toarray())
df.to_excel("newgroup-sparce-data.xlsx", index=False)
print("Done")




"""
from sklearn.linear_model import LogisticRegression, RidgeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn import metrics

models = {}
# inverse of regularization strength
models['LogisticRegression'] = LogisticRegression(C=5, max_iter=1000)

# gradient descent solver for sparse matrix "sparse_cg"
models['RidgeClassifier'] = RidgeClassifier(alpha=1.0, solver="sparse_cg")

# setting nearest 100 points as 1 label
models['KNeighborsClassifier'] = KNeighborsClassifier(n_neighbors=100)

"""
"""
# transforming the test data into existing train data vectorizer
X_test = vectorizer.transform(X_test_raw)

# training models and comparing accuracy
for k, v in models.items():
    print("\n=========Training model with classifier {0}===========".format(k))
    v.fit(X_train, y_train)
    pred = v.predict(X_test)
    score = metrics.accuracy_score(y_test, pred)
    print("accuracy after evaluation on test data: {0} %".format(score * 100))
    
"""
"""
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
"""
