import os
import googlemaps
import pandas as pd
import humanize
import json
from pathlib import Path

# INSTALLATION
# pip3 install humanize

# https://googlemaps.github.io/google-maps-services-python/docs/#googlemaps.Client.places_photo

# set up Google Maps Client
GOOGLE_MAPS_API_KEY = os.environ.get('GOOGLE_MAPS_API_KEY')
if not GOOGLE_MAPS_API_KEY:
    raise Exception('GOOGLE_MAPS_API_KEY not set')
map_client = googlemaps.Client(GOOGLE_MAPS_API_KEY)


def download_place_details(place_id):
    response = map_client.place(
        place_id=place_id,
        language='en'
    )
    result = response['result']

    photos_saved = []
    if 'photos' in result:
        photos = result['photos']
        photos_sorted = sorted(list(photos), key=lambda x: x['photo_reference'])

        for photo_details in photos_sorted[:8]:  # get 8 photos
            photo_reference = photo_details['photo_reference']
            photo_folderpath = f'temp-photos/{place_id}'
            photo_filepath = f'{photo_folderpath}/{photo_reference}.jpg'

            # append photo
            photos_saved.append(photo_details)

            # skip downloaded photos
            if Path(photo_filepath).exists() and os.stat(photo_filepath).st_size > 0:
                continue

            # create folder path if not exists
            Path(photo_folderpath).mkdir(parents=True, exist_ok=True)
            # download photo
            photo_file = open(photo_filepath, 'wb')
            print(f'Downloading photo {photo_filepath}...', end='')
            for photo_chunk in map_client.places_photo(photo_reference, max_width=None, max_height=1024):
                if photo_chunk:
                    photo_file.write(photo_chunk)
            photo_file.close()
            print(f'{humanize.naturalsize(os.stat(photo_filepath).st_size)}')

    # carry-over only saved photos
    result['photos'] = photos_saved

    return result


# place_details('ChIJZVc33hoQdkgRs9TqDlUO4jo')

excl_merged_df = pd.read_excel(f'temp/5-search-index-google-maps.xlsx')
for index, row in excl_merged_df.iterrows():  # .head(5) for first 5 rows
    place_id = row['place_id']
    name = row['name']
    print(f'Enriching place {place_id}: {name}...')

    place_details = download_place_details(place_id=place_id)
    excl_merged_df.loc[index, 'place_details'] = json.dumps(place_details)  # column R

excl_merged_df.to_excel(f'temp/10-search-index-google-maps-with-details.xlsx', index=False)
print(f'Output file (all places enriched): temp/temp/10-search-index-google-maps-with-details.xlsx')
