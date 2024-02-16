1. Install Python and PyCharm:

brew install python3

brew install --cask pycharm

2. Make sure Python 3.11 (or higher is selected as interpreter in PyCharm):

Python 3.11 path by brew is: /opt/homebrew/bin/python3



PCA - optimised on highest variability (especially for PC1)
truncated SVD - work for sparce data
LDA - optimised on largest differences between categories (target)


dataset x |PCA| = transformed_dataset 
run linear regression on transformed_dataset (2 dimensions instead of 4)

transformed_dataset x |PCA|\-1

PC1 + PC2 = 98%
