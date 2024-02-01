# https://youtu.be/4jBJhCaNrWU

def order_pizza(size, *toppings, **details):
    print(f"Ordered a {size} pizza with the following toppings:")
    for topping in toppings:
        print(f"- {topping}")
    print(details)


order_pizza("pepperoni", "olives", delivery=True, tip=10)
