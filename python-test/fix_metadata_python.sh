#!/bin/bash

find /opt/homebrew/lib/python3.11/site-packages -empty -type d -delete

pip3 uninstall jsonschema
pip3 install jsonschema

pip3 uninstall jupyterlab
pip3 install jupyterlab
jupyter lab build
jupyter lab
