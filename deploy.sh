#!/usr/bin/env bash
if test  -z $1
then
    echo "parameter couldn't found"
    exit -1
fi
OUTPUT=`activator playGenerateSecret | grep "Generated new secret: " | sed 's/.*: // '`
OUTPUT="'${OUTPUT}'"
echo $OUTPUT
activator dist
boxfuse run -env=$1 -jvm.args=-DAPPLICATION_SECRET=$OUTPUT
exit 0
