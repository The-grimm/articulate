#!/bin/sh

set -e -u -x

cd articulate-repo
mvn package
cp target/*.jar ../artifacts
