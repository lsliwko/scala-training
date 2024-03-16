import glob
import pandas as pd
import pyarrow.csv

datapoints_merged_filename = "datapoint-task-merged.csv"
dataset = pd.read_csv("datapoint-task-merged.csv", low_memory=False)


def append_datapoints(datapoints_file):
    global dataset

    dataset_tmp = pd.read_csv(datapoints_file, low_memory=False)
    print(f"Appending {datapoints_file} ({dataset_tmp.size} new datapoints)...")

    # merge datapoints, remove duplicates
    dataset = pd.concat([dataset, dataset_tmp], ignore_index=True)
    dataset = dataset.drop_duplicates(subset='TASK ID', keep='last')

    # save cleared file
    print(f"Saving {datapoints_merged_filename} ({dataset.size} datapoints)")
    dataset.to_csv(datapoints_merged_filename, index=False)


for datapoints_file in sorted(glob.glob('/Users/lsliwko/workspace/MASB/datapoint-bak/*.csv')):

    # append_datapoints(datapoints_file)