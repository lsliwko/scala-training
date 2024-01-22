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
# https://analyticsindiamag.com/beginners-guide-to-truncated-svd-for-dimensionality-reduction/
# https://www.geeksforgeeks.org/classification-of-text-documents-using-sparse-features-in-python-scikit-learn/

# Some of the examples where sparse data generates are: Recommendation system, Text classification, Bag of words, TF-IDF
# TF-IDF stands for term frequency-inverse document frequency, and it is a measure, used in the fields of information
# retrieval (IR) and machine learning, that can quantify the importance or relevance of string representations (words,
# phrases, lemmas, etc.)  in a document amongst a collection of documents (also known as a corpus).


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
    token_pattern=u'(?ui)\\b\\w*[a-z]+\\w*\\b'  # must contain at least one letter, no numbers
)

# TODO disallow single-character words

# alternative to token_patter to remove numbers:
# https://scikit-learn.org/stable/auto_examples/bicluster/plot_bicluster_newsgroups.html

# Vectorize and standardize the data
X_vectorized = vectorizer.fit_transform(news_data.data)
y = news_data.target

newsgroup_sparce_data_file = "newgroups-sparce-data.xlsx"
print(f"Saving to {newsgroup_sparce_data_file}: {X_vectorized.shape}")
df_sparce = pd.DataFrame(columns=vectorizer.get_feature_names_out(), data=X_vectorized.toarray())
df_sparce['#TARGET'] = y
df_sparce.to_excel(newsgroup_sparce_data_file, index=False)
print("Done")

# Create a TruncatedSVD object and fit the data
truncated_svd = TruncatedSVD(n_components=2)
X_truncated = truncated_svd.fit_transform(X_vectorized)

newsgroup_truncated_file = "newgroups-truncated.xlsx"
print(f"Saving to {newsgroup_truncated_file}: {X_truncated.shape}")
df_sparce = pd.DataFrame(columns=truncated_svd.get_feature_names_out(), data=X_truncated)
df_sparce['#TARGET'] = y
df_sparce.to_excel(newsgroup_truncated_file, index=False)
print("Done")

sns.scatterplot(data=df_sparce, x='truncatedsvd0', y='truncatedsvd1', hue='#TARGET')
plt.show()
