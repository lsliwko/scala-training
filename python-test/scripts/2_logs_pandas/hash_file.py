import pandas as pd
import hashlib

df = pd.read_csv("../../data-git-ignore/checks.csv", header=None, sep=",")

hashes = []
for data_row in df.itertuples():
    print(data_row)
    hash = hashlib.md5(f"{data_row._4} {data_row._5}".encode('utf-8')).hexdigest()[:22]
    hashes.append(hash)

print(hashes)
df.insert(0, 'hash', hashes)

df.to_csv("checks-with-hash-ids.csv", index=False, header=False)

