#!/bin/bash
#
# Usage: cloudsearch-clone-domain <domain> <newdomain>
# 
# After you run this script, you'll have a file named define-fields-<newdomain>.sh, which
# you can run to re-create all the fields from the cloned domain. If you haven't yet created
# the new CS domain, then run `aws cloudsearch create-domain --domain-name <newdomain>` before
# running the define-fields script that is produced by this script.

die () {
    echo >&2 "$@"
    exit 1
}
[ "$#" -eq 2 ] || die "Usage: cloudsearch-clone-domain <domain> <newdomain>"

aws cloudsearch describe-index-fields --domain $1 | jq ".[][] | {\"DomainName\": \"$2\", \"IndexField\": .Options} | tostring" | sed 's/^/aws cloudsearch define-index-field --cli-input-json /' > define-fields-$2.sh
chmod +x define-fields-$2.sh
echo Run these commands to create your new domain, and then define its fields:
echo aws cloudsearch create-domain --domain-name $2
echo ./define-fields-$2.sh
