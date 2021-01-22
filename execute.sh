#!/bin/bash

input=${1:- NULL}
output=${2:- NULL}
text=${3:-characters/ASCII.txt}
num=${4:-256}
color=${5:-black}

javac Main.java
if [ "$input" = "h" ]
then
    java Main -h
    
else
    java Main -i $input -o $output -t $text -n $num -c $color
fi

rm *.class
rm ./helper/*.class