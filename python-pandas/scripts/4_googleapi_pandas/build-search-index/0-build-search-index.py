import os
import googlemaps
import pandas as pd
import time

# INSTALLATION
# pip3 install googlemaps

# script requires env variable GOOGLE_MAPS_API_KEY (from https://console.cloud.google.com/google/maps-apis/home)


# SOURCE
# https://stackoverflow.com/questions/75369224/access-bulk-business-data-via-google-maps-api-with-python


# set up Google Maps Client
GOOGLE_MAPS_API_KEY = os.environ.get('GOOGLE_MAPS_API_KEY')
if not GOOGLE_MAPS_API_KEY:
    raise Exception('GOOGLE_MAPS_API_KEY not set')
map_client = googlemaps.Client(GOOGLE_MAPS_API_KEY)


def download_locations_from_latlon(search_string, location_latlon, locations_array):
    print(f'Querying locations "{search_string}" at {location_latlon}...')
    response = map_client.places(
        query=search_string,
        location=location_latlon
    )

    print('Loading locations...')
    locations_array.extend(response.get('results'))
    next_page_token = response.get('next_page_token')

    while next_page_token:
        time.sleep(3)
        print('Loading locations...')
        response = map_client.places(
            query=search_string,
            location=location_latlon,
            page_token=next_page_token
        )
        locations_array.extend(response.get('results'))
        next_page_token = response.get('next_page_token')


def download_locations_to_file(search_string, filepath):
    # create rectangle grid
    start_latlon = (51.5245059, -0.1920407)  # Little Venice
    end_latlon = (51.4988198, -0.0189291)  # The O2

    locations_array = []
    lat_delta, lon_delta = 0, 0
    while start_latlon[0] + lat_delta >= end_latlon[0]:
        lat_delta = lat_delta - 0.002
        lon_delta = 0
        while start_latlon[1] + lon_delta <= end_latlon[1]:
            location_latlon = (start_latlon[0] + lat_delta, start_latlon[1] + lon_delta)
            lon_delta = lon_delta + 0.005

            print(location_latlon)
            try:
                download_locations_from_latlon(
                    search_string=search_string,
                    location_latlon=location_latlon,
                    locations_array=locations_array
                )
            except Exception as e:
                print(f"Error listing locations. Error {str(e)}")

    # remove duplicates
    excl_df = pd.DataFrame(locations_array).drop_duplicates(['place_id'], keep='last')
    print(f'Saving {excl_df.shape[0]} locations...')
    excl_df.to_excel(filepath, index=False)


# start script
# download_locations_to_file(
#     search_string='beer',
#     filepath=f'temp/0-search-index-google-maps-beer.xlsx'
# )

# download_locations_to_file(
#    search_string='bar',
#    filepath=f'temp/0-search-index-google-maps-bar.xlsx'
# )

# download_locations_to_file(
#    search_string='food',
#    filepath=f'temp/0-search-index-google-maps-food.xlsx'
# )

# download_locations_to_file(
#    search_string='drink',
#    filepath=f'temp/0-search-index-google-maps-drink.xlsx'
# )


# merge files into one Excel
# https://www.geeksforgeeks.org/how-to-merge-multiple-excel-files-into-a-single-files-with-python/
excl_merged_df = pd.DataFrame()

excl_list = [f'temp/0-search-index-google-maps-beer.xlsx', f'temp/0-search-index-google-maps-bar.xlsx',
             f'temp/0-search-index-google-maps-food.xlsx', f'temp/0-search-index-google-maps-drink.xlsx']
for excl_file in excl_list:
    print(f'Merging {excl_file}...')
    excl_df = pd.read_excel(excl_file)
    excl_merged_df = pd.concat([excl_df, excl_merged_df])

excl_merged_df = excl_merged_df.drop_duplicates(['place_id'], keep='last')
print(f'Saving {excl_merged_df.shape[0]} locations...')
excl_merged_df.to_excel(f'temp/5-search-index-google-maps.xlsx', index=False)
print(f'Output file (all places): temp/5-search-index-google-maps.xlsx')

