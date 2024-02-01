# https://youtu.be/4jBJhCaNrWU

def order_pizza(size, *args, **kwargs):
    print(f"Ordered a {size} pizza with the following toppings:")
    for args in args:
        print(f"- {args}")
    print(kwargs)  # dictionary


order_pizza("pepperoni", "olives", delivery=True, tip=10)
