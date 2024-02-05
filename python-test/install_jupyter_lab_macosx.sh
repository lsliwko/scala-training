#!/bin/bash

find /opt/homebrew/lib/python3.11/site-packages -empty -type d -delete

brew install npm
brew install node

pip3 uninstall jsonschema
pip3 install jsonschema -U

pip3 uninstall jupyterlab
pip3 install jupyterlab -U

jupyter lab build

jupyter lab
