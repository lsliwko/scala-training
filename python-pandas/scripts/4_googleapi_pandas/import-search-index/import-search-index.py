import csv
import json
import os
import pandas
import itertools

# INSTALLATION
# to install aws cli: brew install awscli
# to install python: brew install python
# to install pandas: pip3 install pandas
# to install pandas: pip3 install openpyxl


# EDITING
# Open search-index.xlsx in Excel and save data


# RUN
# run from IntelliJ, right-click import-search-index.py and click Run...
# to run: python3 import-search-index.py


# NOTES:
# 1. Location should be copied from Google Maps (here: 51.5258321,-0.0860262):
#  https://www.google.com/maps/place/Swift+Shoreditch/@51.5258321,-0.0860262,17z/data=!3m1!4b1!4m6!3m5!1s0x48761d99787c80a7:0xe724fcf30809a0b0!8m2!3d51.5258321!4d-0.0834513!16s%2Fg%2F11j_sx460v?entry=ttu
# 2. seach-index.csv must be UTF8 compatible, when saving in Excel make sure to save as: 'CSV UTF-8 (Comma delimited)'
# 3. on any error, check the last 'Line:' data


# IDE SETUP
# setup Pyton3.10 (or higher) in Project -> SDK (use System interpreter)
# use /opt/homebrew/bin/python3

# Search Endpoint: search-koop-search-hrj6u7sqwqoudypg7vklcbeaeq.eu-central-1.cloudsearch.amazonaws.com
# Document Endpoint: doc-koop-search-hrj6u7sqwqoudypg7vklcbeaeq.eu-central-1.cloudsearch.amazonaws.com
# Console: https://eu-central-1.console.aws.amazon.com/cloudsearch/home?region=eu-central-1#search,koop-search

CLOUDSEARCH_ENDPOINT = 'http://doc-koop-search-hrj6u7sqwqoudypg7vklcbeaeq.eu-central-1.cloudsearch.amazonaws.com'


def parse_non_empty_string(fieldname, row):
    data = row[fieldname].strip()
    if not data:
        raise Exception(fieldname + ' is empty in row:\n' + repr(row))
    return data


def parse_non_empty_float(fieldname, row):
    data = parse_non_empty_string(fieldname, row)
    try:
        return float(data)
    except ValueError:
        raise Exception(fieldname + ' is not float number in row:\n' + repr(row))


def parse_string_list(fieldname, row, delimiter):
    data = list(
        # trim and remove empty strings
        filter(None, [x.strip() for x in parse_non_empty_string(fieldname, row).split(delimiter)])
    )
    if not data or len(data) == 0:
        raise Exception(fieldname + ' is empty in row:\n' + repr(row))
    return data


print('---')
# Convert XLSX file into csv
print('Converting search-index.xlsx...')
xlsx_file = pandas.read_excel("search-index.xlsx")
xlsx_file.to_csv("temp/search-index-no-edit.csv", index=None, header=True)

print('---')
print('Processing temp/search-index-no-edit.csv...')
documents_array = []
keywords_array = []
with open('temp/search-index-no-edit.csv', 'r', encoding='utf-8-sig') as file:  # ignore BOM character
    csvreader = csv.DictReader(file)
    for row in csvreader:
        _id = row['id'].strip()

        # skip empty or comment lines
        if (not _id) or _id.startswith('#'):
            continue

        print('Line: ', row)

        _type = parse_non_empty_string('type', row)
        if _type == 'add':

            opening_hours = parse_string_list('opening_hours', row, '|')
            if len(opening_hours) != 7:
                raise Exception('opening_hours must be seven days or none')

            keywords = [x.lower() for x in parse_string_list('keywords', row, '|')]
            keywords_array.extend(keywords)

            documents_array.append(
                {
                    'id': _id,
                    'type': _type,
                    'fields': {
                        'name': parse_non_empty_string('name', row),
                        'description': parse_non_empty_string('description', row),
                        'location': parse_non_empty_string('location', row),
                        'address': parse_non_empty_string('address', row),
                        'keywords': keywords,
                        'image_main': parse_non_empty_string('image_main', row),
                        'rating': parse_non_empty_float('rating', row),
                        'price': parse_non_empty_float('price', row),
                        'opening_hours': opening_hours
                    }
                })
        elif _type == 'delete':
            documents_array.append(
                {
                    'id': _id,
                    'type': _type
                })
        else:
            raise Exception('type must be add or delete. Type found: ' + _type)

print('All search-index documents loaded: ', len(documents_array))

# pretty print documents batch to file
with open('temp/search-index-batch-no-edit.json', 'w') as fp:
    json.dump(documents_array, fp, indent=4)

# log file
print('---')
print('Generated documents batch:')
print(open('temp/search-index-batch-no-edit.json', 'r').read())

print('---')
print('Keywords (make sure they are normalized):')
keywords_array_groups = []
for k, g in itertools.groupby(sorted(set(keywords_array)), key=lambda x: x[0]):
    keywords_array_groups.append(list(g))
for keywords in keywords_array_groups:
    print(keywords)

print('---')
print('Updating documents in CloudSearch...')
os.system(
    'aws cloudsearchdomain upload-documents --endpoint-url ' + CLOUDSEARCH_ENDPOINT + ' --content-type application/json --documents temp/search-index-batch-no-edit.json'
)
print('---')

print('To search use (wait few minutes for index to update):')
print(
    'https://search-koop-search-hrj6u7sqwqoudypg7vklcbeaeq.eu-central-1.cloudsearch.amazonaws.com/2013-01-01/search?q=beer'
)
