# https://youtu.be/4jBJhCaNrWU


# *args = unpack operator '*'
# **kwargs = keyboard args

def order_pizza(size, *args, **kwargs):
    print(f"Ordered a {size} pizza with the following toppings:")
    for args in args:
        print(f"- {args}")
    print(kwargs)  # dictionary


order_pizza("pepperoni", "olives", "mozzarella", delivery=True, tip=10)
