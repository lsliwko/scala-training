import glob
import pandas as pd
import pyarrow.csv
from collections import defaultdict

print("Loading file...")
dataset = pd.read_excel(
    "/Users/lsliwko/workspace/MASB/000AI-task-allocation-difficulty-paper/datapoint-task-merged-distribution.xlsx")
print("File loaded")

distribution = defaultdict(lambda x: 0)


def update_distribution(available_nodes_count_tmp):
    if available_nodes_count_tmp == 1:
        distribution['1'] += 1
    elif available_nodes_count_tmp <= 500:
        distribution['500'] += 1
    elif available_nodes_count_tmp <= 1000:
        distribution['1000'] += 1
    elif available_nodes_count_tmp <= 1500:
        distribution['1500'] += 1
    elif available_nodes_count_tmp <= 2000:
        distribution['2000'] += 1
    elif available_nodes_count_tmp <= 2500:
        distribution['2500'] += 1
    elif available_nodes_count_tmp <= 3000:
        distribution['3000'] += 1
    elif available_nodes_count_tmp <= 3500:
        distribution['3500'] += 1
    elif available_nodes_count_tmp <= 4000:
        distribution['4000'] += 1
    elif available_nodes_count_tmp <= 4500:
        distribution['4500'] += 1
    elif available_nodes_count_tmp <= 5000:
        distribution['5000'] += 1
    elif available_nodes_count_tmp <= 5500:
        distribution['5500'] += 1
    elif available_nodes_count_tmp <= 6000:
        distribution['6000'] += 1
    elif available_nodes_count_tmp <= 6500:
        distribution['6500'] += 1
    elif available_nodes_count_tmp <= 7000:
        distribution['7000'] += 1
    elif available_nodes_count_tmp <= 7500:
        distribution['7500'] += 1
    elif available_nodes_count_tmp <= 8000:
        distribution['8000'] += 1
    elif available_nodes_count_tmp <= 8500:
        distribution['8500'] += 1
    elif available_nodes_count_tmp <= 9000:
        distribution['9000'] += 1
    elif available_nodes_count_tmp <= 9500:
        distribution['9500'] += 1
    elif available_nodes_count_tmp <= 10000:
        distribution['10000'] += 1
    elif available_nodes_count_tmp <= 10500:
        distribution['10500'] += 1
    elif available_nodes_count_tmp <= 11000:
        distribution['11000'] += 1
    elif available_nodes_count_tmp <= 11500:
        distribution['11500'] += 1
    elif available_nodes_count_tmp <= 12000:
        distribution['12000'] += 1
    else:
        distribution['12500'] += 1


for index, row in dataset.iterrows():
    available_nodes_count = int(row['AVAILABLE NODES COUNT'])
    update_distribution(available_nodes_count)

print(distribution)
