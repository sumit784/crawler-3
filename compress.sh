#!/bin/bash


dir=/opt/tomcat/apache-tomcat-7.0.27/webapps/mall/resources
cd $dir
compilerPath=/home/qinyuan/program/javascript/compiler.jar
compressorPath=/home/qinyuan/program/javascript/yuicompressor-2.4.6.jar

######################### compress JS ##################

compress() { 
    echo "start to compress $1"
    java -jar $compilerPath --js $dir/$1 --js_output_file $dir/$1.compress
    mv -f $dir/$1.compress $dir/$1
    echo "compress $1 successfully"
    echo
}

for f in `find -name '*.js'`; do
    if [[ ! $f == ./js/lib/* ]]; then
        compress $f
    fi
done

compress js/lib/linecharts/linecharts.js
compress js/lib/jsutils.js
compress js/lib/ie-patch.js


######################### compress CSS ##################
compressCSS() { 
    echo "start to compress $1"
    java -jar $compressorPath --type css --charset utf-8 -v $dir/$1 > $dir/$1.compress
    mv -f $dir/$1.compress $dir/$1
    echo "compress $1 successfully"
    echo
}

for f in `find -name '*.css'`; do
    compressCSS $f
done
