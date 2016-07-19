#!/bin/bash
#
# gitfreshlocal
# Creates a zip file that inclues the entire git repository, including .git directory,
# checked out to the given branch,
# based on a clone (so that it doesn't include any git-ignored files)
#
# This script assumes you are in the working directory of git repository, 
# and that you have write access to the directory one level above the current
#
# usage: gitfreshlocal projectname [branchname]
# projectname can be any reasonable string, but should probably be the name of the github repository
# branchname (optional, defaults to 'master')
#

projectname=$1
branchname=$2
cloneroot="$projectname"_clone
clonedir="$cloneroot/$projectname"
ziploc="$projectname""_`date +%Y%m%d_%H`.zip"

echo "createing directory ../$cloneroot ..."
mkdir ../$cloneroot

if [ -z "$branchname" ]
then
  echo "defauling to master branch..."
  branchname=master
else
  echo "using branch $branchname..."
fi

echo "cloning to ../$clonedir ..."
git clone -q -b $branchname . "../$clonedir"

echo "zip it up to ../$ziploc"
cd ../$cloneroot
zip -rq "../$ziploc" "$projectname"

echo "clean up the clone directory"
cd ..
rm -rf $cloneroot
