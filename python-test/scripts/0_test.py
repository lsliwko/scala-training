def get_classification(available_nodes_count_tmp):
    if available_nodes_count_tmp <= 1:
        return 'A'
    elif available_nodes_count_tmp <= 500:
        return 'B'
    elif available_nodes_count_tmp <= 1000:
        return 'C'
    elif available_nodes_count_tmp <= 1500:
        return 'D'
    elif available_nodes_count_tmp <= 2000:
        return 'E'
    elif available_nodes_count_tmp <= 2500:
        return 'F'
    elif available_nodes_count_tmp <= 3000:
        return 'G'
    elif available_nodes_count_tmp <= 3500:
        return 'H'
    elif available_nodes_count_tmp <= 4000:
        return 'I'
    elif available_nodes_count_tmp <= 4500:
        return 'J'
    elif available_nodes_count_tmp <= 5000:
        return 'K'
    elif available_nodes_count_tmp <= 5500:
        return 'L'
    elif available_nodes_count_tmp <= 6000:
        return 'M'
    elif available_nodes_count_tmp <= 6500:
        return 'N'
    elif available_nodes_count_tmp <= 7000:
        return 'O'
    elif available_nodes_count_tmp <= 7500:
        return 'P'
    elif available_nodes_count_tmp <= 8000:
        return 'Q'
    elif available_nodes_count_tmp <= 8500:
        return 'R'
    elif available_nodes_count_tmp <= 9000:
        return 'S'
    elif available_nodes_count_tmp <= 9500:
        return 'T'
    elif available_nodes_count_tmp <= 10000:
        return 'U'
    elif available_nodes_count_tmp <= 10500:
        return 'V'
    elif available_nodes_count_tmp <= 11000:
        return 'W'
    elif available_nodes_count_tmp <= 11500:
        return 'X'
    elif available_nodes_count_tmp <= 12000:
        return 'Y'
    else:
        return 'Z'


def get_classification2(count):
    if count <= 1:
        return 'A'
    elif count > 12000:
        return 'Z'
    return chr((count - 1) // 500 + 66)


for x in [0,1,2,498,499,500,501,502,1000,1001,1500,1501,12000, 12001]:
    print(f'true: {x}: {get_classification(x)}')
    print(f'pred: {x}: {get_classification2(x)}')
