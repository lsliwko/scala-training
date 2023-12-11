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
