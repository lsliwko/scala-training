#!/usr/bin/env bash

#git config filter.compress.clean gzip
#git config filter.compress.smudge gzip -d

GIT_SSH_COMMAND="ssh -i ~/.ssh/lsliwko_rsa" git status
GIT_SSH_COMMAND="ssh -i ~/.ssh/lsliwko_rsa" git add *
GIT_SSH_COMMAND="ssh -i ~/.ssh/lsliwko_rsa" git commit -u -m updates
GIT_SSH_COMMAND="ssh -i ~/.ssh/lsliwko_rsa" git push
