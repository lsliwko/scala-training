class MyUser:
    """
    MyClass does blah blah blah.
    """

    # class attributes are variables of a class that are shared between all of its instances
    user_name_public = 'Les-shared-public'  # public
    _user_name_protected = 'Les-shared-protected'  # protected (ONLY BY CONVENTION!)
    __user_name_private = 'Les-shared-private'  # private ('__')

    def __init__(self, name: str, age: int):
        # instance attributes are owned by one specific instance of the class and are not shared between instances
        self.name = name  # instance attribute
        self.age = age  # instance attribute

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

print(myUser1.function_public())

print(myUser1.name)

myUser1.user_name_public = 'new shared username'
print(myUser2.user_name_public)
