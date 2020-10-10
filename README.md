# Text Animation

This is a java personal project that takes an image file or a directory that contains a list of images as input and output an image or gif that represents the input images but in text format. Basically, it uses the RGB value in the image and converts it into a corresponding unique character. This is inspired by the flipbook. Currently, this project is able to convert an image file or a directory of images into an image that represent as text format. 

This project uses ImageIO to scan the image and AWT library to draw the text onto the image.


## Usage
There are two ways to execute the programs:

First:
```bash
javac Main.java
java Main -i img/example_5.jpg -o output/output.png 
```

or

```bash
javac Main.java
java Main -i img/example_5.jpg -o output/output.png -t characters/ASCII.txt -n 256 -c 1 
```

second:

```bash
bash execute.sh img/example_5.jpg output/output.png
```

or

```bash
bash execute.sh img/example_5.jpg output/output.png characters/ASCII.txt 256 1
```

To see each commend functionality

```bash
javac Main.java
java Main -h 
```

or

```bash
bash execute.sh h
```

## Screenshots

