# Task description

Add items to the shopping basket Remove items from the shopping basket Empty the shopping basket Additionally, we must be able to calculate the total value of the shopping basket accounting for:

Buy-one-get-one-free discounts on items 10% off on totals greater than £20 (after bogof) 2% off on total (after all other discounts) for customers with loyalty cards. We must be able to see the code running via passing tests or Terminal output.



# API description
curl localhost:9000/shopping-cart/new
curl localhost:9000/shopping-cart/111/items/add/apple
curl localhost:9000/shopping-cart/111/items/remove/apple
curl localhost:9000/shopping-cart/111/items/empty

docker-compose up

# Install Docker via Cask
brew uninstall --cask docker --force
brew install --force homebrew/cask/docker


# Install MiniKube
brew install minikube
minikube start