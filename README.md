# Text Animation

This is a java personal project that takes an **image file**, an **url image** or a **directory of images** as input and output an image or gif that represents the input images but in text art. Basically, it uses the RGB value in the image and converts it into a corresponding unique character. This is inspired by the flipbook. Currently, this project is supported to convert the image, the url image or the directory of the images into text art. 

This project uses **AWT library** to scan the image and draw the text art of image into output image and **IO file handling library** to store and retrieve the file.


## Usage
There are **two** ways to execute the programs:

---

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

----

second:

```bash
bash execute.sh img/example_5.jpg output/output.png
```

or

```bash
bash execute.sh img/example_5.jpg output/output.png characters/ASCII.txt 256 1
```

---

To see each commend functionality

```bash
javac Main.java
java Main -h 
```

or

```bash
bash execute.sh h
```


## Demonstrations
<img src="./readmeFile/demonstration1.gif" width="1000" height="300" />

------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration3.png" width="512" height="512" /> |


If you can also try the commends under to see different output:

------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration4.png" width="512" height="512" /> |
```bash
bash execute.sh img/example_5.jpg output/output.png characters/ASCII.txt 128 1
```

------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration5.png" width="512" height="512" /> |

```bash
bash execute.sh img/example_5.jpg output/output.png characters/ASCII.txt 256 2
```

------------------
| before | after |
| :---:  | :---: |
| <img src="https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration6.png" width="512" height="512" /> |

```bash
bash execute.sh https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg output/output.png characters/ASCII.txt 256 1
```