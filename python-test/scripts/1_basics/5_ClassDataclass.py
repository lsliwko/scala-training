from dataclasses import dataclass

class MyUser(object):  # MyUser(object) is a new-style class
    """
    MyClass does blah blah blah.
    """

    # class attributes are variables of a class that are shared between all of its instances
    # MyUser.__dict__
    user_name_public = 'Les-shared-public'  # public
    _user_name_protected = 'Les-shared-protected'  # protected (ONLY BY CONVENTION!)
    __user_name_private = 'Les-shared-private'  # private ('__')

    def __init__(self, name: str, age: int):
        # instance attributes are owned by one specific instance of the class and are not shared between instances
        self.name = name  # instance attribute
        self.age = age  # instance attribute
        # Note: it's the same as self.__dict__['name'] = name

    def function_public(self):
        return f'hello world public. my name is {self.name} and my age is {self.age}'

    def __function_private(self):
        return f'hello world private. my name is {self.name} and my age is {self.age}'


# inheritance
class MyBetterUser(MyUser):

    def function_public(self):
        return f'better hello world public. my name is {self.name} and my age is {self.age}'


myUser1 = MyUser("Les1", 25)
myUser2 = MyUser("Les2", 40)
# myUser1.__dict__["name"] = "Tom"

print(myUser1.function_public())

print(myUser1.name)

myUser1.user_name_public = 'new shared username'
print(myUser2.user_name_public)


@dataclass
class InventoryItem:
    """Class for keeping track of an item in inventory."""
    name: str
    unit_price: float
    quantity_on_hand: int = 0

    def total_cost(self) -> float:
        return self.unit_price * self.quantity_on_hand


# NOTE:
if __name__ == "__main__":
    print('Executed as script (not as module)')
