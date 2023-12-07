import glob
import pandas as pd
from datetime import datetime


# install pandas:
# brew install python
# pip3 install pandas
# pip3 install openpyxl


# {
#     "timestamp": "2022-12-23T12:34:56Z",
#     "level": "error",
#     "message": "APP_ERR_204:There was an error processing the request"
# }

def log_line_slf4j(log_line):
    log_line_segments = log_line.split(' ', 2)  # split into no more than three segments
    timestamp = log_line_segments[0][:23]  # read timestamp, limit to 23 characters
    level = log_line_segments[1].lower()
    message = log_line_segments[2].strip()  # copy everything after 2nd segment
    return timestamp, level, message


def append_logs(log_dataframe, log_filename, func_log_line):
    print(f'Reading {log_filename}...')
    line_no = 0
    log__file = open(log_filename, 'r')
    for log_line in log__file.read().splitlines():
        if not log_line: continue  # ignore empty lines
        # print(f'{log_line}')

        try:
            (timestamp, level, message) = func_log_line(log_line)  # parse line and return three values
        except Exception as error:
            error_message = f'Error parsing line {line_no} from {log_filename}: {log_line}'
            raise Exception(error_message) from error

        # sanity check
        if level not in ('debug', 'info', 'warn', 'error', 'fatal'):
            error_message = f'Illegal level {level} parsed from log line: {log_line}'
            raise Exception(error_message)

        # sanity check
        try:
            timestamp_date = datetime.strptime(timestamp, "%Y-%m-%dT%H:%M:%S,%f")
        except Exception as error:
            error_message = f'Illegal timestamp {timestamp} parsed from log line: {log_line}'
            raise Exception(error_message)

        # append to dataframe
        log_dataframe.loc[-1] = [timestamp_date, level, message]
        log_dataframe.index = log_dataframe.index + 1

        line_no = line_no + 1

    log__file.close()
    print(f'Read {line_no} lines from {log_filename}')


# create dataframe with columns: timestamp, level, message
df = pd.DataFrame({'timestamp': [], 'level': [], 'message': []})

for log_file in glob.glob('logs-parse/*.log'):
    append_logs(df, log_file, log_line_slf4j)

df \
    .sort_values(by=['timestamp']) \
    .to_excel('logs-20151018.xlsx',
              index=False)

df[(df['timestamp'].astype(str).str.startswith('2015-10-18 18:06'))] \
    .sort_values('timestamp') \
    .to_json("logs-20151018-1806.json",
             orient="records",
             lines=True)

df[(df['timestamp'].astype(str).str.startswith('2015-10-18 18:07'))] \
    .sort_values('timestamp') \
    .to_json("logs-20151018-1807.json",
             orient="records",
             lines=True)

df[(df['timestamp'].astype(str).str.startswith('2015-10-18 18:08'))] \
    .sort_values('timestamp') \
    .to_json("logs-20151018-1808.json",
             orient="records",
             lines=True)

df[(df['timestamp'].astype(str).str.startswith('2015-10-18 18:09'))] \
    .sort_values('timestamp') \
    .to_json("logs-20151018-1809.json",
             orient="records",
             lines=True)

df[(df['timestamp'].astype(str).str.startswith('2015-10-18 18:10'))] \
    .sort_values('timestamp') \
    .to_json("logs-20151018-1810.json",
             orient="records",
             lines=True)
