import glob
import pandas as pd
import pyarrow.csv
from collections import defaultdict

print("Loading file...")
dataset = pd.read_excel(
    "/Users/lsliwko/workspace/MASB/000AI-task-allocation-difficulty-paper/datapoint-task-merged.xlsx")
print("File loaded")


