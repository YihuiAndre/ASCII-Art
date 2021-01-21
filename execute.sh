#!/bin/bash

input=${1:- NULL}
output=${2:- NULL}
text=${3:-characters/ASCII.txt}
num=${4:-256}
compress=${5:-1}

javac Main.java
if [ "$input" = "h" ]
then
    java Main -h
else
    java Main -i $input -o $output -t $text -n $num -c $compress
fi

rm *.class
rm ./helper/*.class