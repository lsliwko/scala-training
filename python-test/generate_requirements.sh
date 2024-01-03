#!/bin/bash

pip3 install pipreqs

# --mode=compat generates Flask~=1.1.2
# --mode=gt generates Flask>=1.1.2
# --mode=no-pin generates Flask
pipreqs --force --mode=gt --savepath=requirements.txt ./scripts
