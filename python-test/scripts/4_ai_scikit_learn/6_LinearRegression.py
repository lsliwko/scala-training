from sklearn.model_selection import train_test_split

from sklearn.linear_model import LinearRegression
regressor = LinearRegression()
regressor.fit(X_train, y_train)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 1/3, random_state = 0)


y_pred = regressor.predict(X_test)