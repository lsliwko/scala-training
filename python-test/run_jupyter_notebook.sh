#!/bin/bash

pip3 install jupyter

export JUPYTER_PATH=/opt/homebrew/share/jupyter
export JUPYTER_CONFIG_PATH=/opt/homebrew/etc/jupyter jupyter notebook

jupyter notebook