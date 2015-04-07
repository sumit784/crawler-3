#!/bin/bash


######################### compress JS ##################
cd ~/tomcat/webapps/ROOT/resources/js
dir=`pwd`

compress() { 
    echo "start to compress $1"
    java -jar /home/qinyuan/compiler.jar --js $dir/$1 --js_output_file $dir/$1.compress
    mv -f $dir/$1.compress $dir/$1
    echo "compress $1 successfully"
    echo
}

for f in `find -name '*.js'`; do
    if [[ ! $f == ./lib/* ]]; then
        compress $f
    fi
done

compress lib/linecharts/linecharts.js
#compress lib/jsutils.js
#compress lib/angular-patch.js


######################### compress CSS ##################
compressCSS() { 
    echo "start to compress $1"
    java -jar /home/qinyuan/yuicompressor-2.4.6.jar --type css --charset utf-8 -v $dir/$1 > $dir/$1.compress
    mv -f $dir/$1.compress $dir/$1
    echo "compress $1 successfully"
    echo
}

cd ~/tomcat/webapps/ROOT/resources/css
dir=`pwd`
for f in `find -name '*.css'`; do
    compressCSS $f
done


