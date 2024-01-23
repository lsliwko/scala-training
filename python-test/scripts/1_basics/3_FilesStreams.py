try:
    file_sample = open(
        file="sample_novel.txt",
        mode="r",
        encoding="utf-8"
    )
    try:
        count = 0
        for line in file_sample:
            line_stripped = line.strip()
            if not line_stripped: continue
            count += 1
            print(f"Line {count}: {line_stripped}")
    finally:
        file_sample.close()
except IOError as io_error:
    error = f"File read exception {repr(io_error)}"
    raise Exception(error)


# Generator functions allow you to declare a function that behaves like an iterator, i.e. it can be used in a for loop
# https://wiki.python.org/moin/Generators
def nonblank_lines_generator_function(lines):
    for line_elem in lines:
        line_elem = line_elem.strip()
        if line_elem:
            yield line_elem


try:
    with open(
            file="sample_novel.txt",
            mode="r",
            encoding="utf-8"
    ) as file_sample:
        count = 0
        for line_stripped in nonblank_lines_generator_function(file_sample):
            count += 1
            print(f"Line {count}: {line_stripped}")
except IOError as io_error:
    error = f"File read exception {repr(io_error)}"
    raise Exception(error)


def error_method():
    # ExceptionGroup discussion: https://peps.python.org/pep-0654/
    raise ExceptionGroup(
        'group name 1',
        [
            OSError(1),
            OSError(2),
            SystemError(2),
            ExceptionGroup(
                'group name 2',
                [
                    OSError(3)
                ]
            )
        ]
    )


# BaseException is the common base class of all exceptions. One of its subclasses, Exception, is the base class of all
# the non-fatal exceptions. Exceptions which are not subclasses of Exception are not typically handled, because they are
# used to indicate that the program should terminate.
def flatten_exceptions(exception: BaseException) -> list[BaseException]:
    exceptions_list = []

    def flatten_exceptions_inner(exception_inner: BaseException):
        if isinstance(exception_inner, ExceptionGroup):
            for exception_inner_tmp in exception_inner.exceptions:
                flatten_exceptions_inner(exception_inner_tmp)  # recursion
        else:
            exceptions_list.append(exception_inner)

    flatten_exceptions_inner(exception)
    return exceptions_list


try:
    error_method()
except* OSError as list_exceptions:
    print(f"There were OSErrors: {flatten_exceptions(list_exceptions)}")
except* SystemError as list_exceptions:
    print(f"There were SystemErrors: {flatten_exceptions(list_exceptions)}")
