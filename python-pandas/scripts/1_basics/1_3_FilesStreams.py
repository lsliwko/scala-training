try:
    file_sample = open("sample_novel.txt", "r", encoding="utf-8")
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

try:
    with open("sample_novel.txt", "r", encoding="utf-8") as file_sample:
        count = 0
        for line in file_sample:
            line_stripped = line.strip()
            if not line_stripped: continue
            count += 1
            print(f"Line {count}: {line_stripped}")
except IOError as io_error:
    error = f"File read exception {repr(io_error)}"
    raise Exception(error)
