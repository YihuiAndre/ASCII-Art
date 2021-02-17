#!/bin/bash
: '
# Abondon because too complicate

input="NULL"
output="NULL"
text="NULL"
num="NULL"
color="NULL"

while :
do
    read -p "Enter the input file path/directory/URL or type help for more info: " input
    if [ "$input" = "" ]
    then
        echo "Please enter a valid file path!"
    elif [ "$input" != "h" ] && [ "$input" != "help" ]
    then
        break
    fi
    echo ""
    echo "ex. ./Sample/img/example_5.jpg"
    echo ""
done

while :
do
    read -p "Enter the output file path/directory/gif/txt or leave it blank or type help for more info: " output
    if [ "$output" = "" ]
    then
        output=NULL
        break
    elif [ "$output" != "h" ] && [ "$output" != "help" ]
    then
        break
    fi
    echo ""
    echo "Path of output directory/image/txt which can be empty if no output file."
    echo "note: directory and gif only apply when input is directory, otherwie appply when input is either file/URL"
    echo "ex. ./Sample_Output/Output.png"
    echo ""
done

while :
do
    read -p "Enter the ASCII file path or type help for more info: " text
    if [ "$text" = "" ]
    then
        text="./ASCII/Template1.txt"
        break
    elif [ "$text" != "h" ] && [ "$text" != "help" ]
    then
        break
    fi
    echo ""
    echo "Path of characters file."
    echo "Leave it blank will use the ASCII file at ./ASCII/Template1.txt"
    echo "ex. ./ASCII/Template1.txt"
    echo ""
done

while :
do
    read -p "How many characters used to represent the image or type help for more info: " num
    if [ "$num" = "" ]
    then
        num=256
        break
    elif [ $num > 256 ] || [ $num <= 0 ]
    then
        echo "Please enter the number between 1 to 256!"
    elif [ "$num" != "h" ] && [ "$num" != "help" ]
    then
        break
    fi
    echo ""
    echo "Number of unique characters between 1 and 256"
    ehco "note: the input has to be in 2^n form and leave it blank will set as 256"
    echo ""
done

while [ $output != NULL ] && [ ${output:(-3)} != "txt" ] :
do
    read -p "Enter the color your want or type help for more info: " color
    if [ "$color" = "" ]
    then
        color=black
        break
    elif [ "$color" != "h" ] && [ "$color" != "help" ]
    then
        break
    fi
    echo ""
    echo "Color of the ASCII Art. (red, blue, green, orange, yellow, black)"
    ehco "note: leave it blank will be black"
    echo ""
done


javac -d build -cp ./lib/*.jar ./helper/*.java ./Main/*.java ./Main/Main.java
java -cp ./build:./lib/gifencoder-0.10.1.jar Main -i $input -o $output -t $text -n $num -c $color
'

input=${1:- NULL}
output=${2:- NULL}
text=${3:-ASCII/Template1.txt}
num=${4:-256}
color=${5:-black}

javac -d build -cp ./lib/*.jar ./helper/*.java ./Main/*.java ./Main/Main.java
if [ "$input" = "h" ]
then
    java -cp ./build:./lib/gifencoder-0.10.1.jar Main -h
    
else
    java -cp ./build:./lib/gifencoder-0.10.1.jar Main/Main -i $input -o $output -t $text -n $num -c $color
fi

