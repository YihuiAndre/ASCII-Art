# ASCII ART

Description: Transform an **image file**, an **url image** or a **directory of images** into ASCII Art as **PNG image** or **txt file** or **GIF** of ASCII Art. 

Currently Support Input File format: JPEG, JPG, PNG, URL

Currently Support Output File format: TXT, PNG, GIF

External Library Usage: [gifencoder](https://github.com/square/gifencoder), AWT library, ImageIO library

```
Directory Tree

|  
|–– ASCII
|–– Main
|–– Sample
|  |–– Animation
|  |–– anime#1
|  |–– anime#2
|  |–– bear
|  |–– img
|–– Sample_Output
|  |–– anime#1
|  |–– anime#2
|  |–– bear
|–– helper
|–– lib
|–– readmeFile
```

- **ASCII**: store all the characters files that use to represent the image
- **helper**: store several helper functions
- **lib**: store external JAR
- **Main**: Main classes that do the image processing
- **readmeFile**: store the image and gif of readme
- **Sample**: Some sample input files
- **Sample_Output**: Some sample output files

---

Basically, it first convert RGB value of each pixels of an image into gray scale value. Then, since every gray scale value has a corresponding unique character, when translate the image into ASCII Art, it will map the value with its corresponding character. Currently, this project is supported to convert the image, the url of the image or the directory into ASCII Art with specific text color. It only could output ASCII Art as png format, text format or even to the terminal. In the future, My goal is scanning a gif file and convert it into ASCII Art in gif file format. Also, there are some improvement need to be done in the future such as optimize the image file size, speed up the process to create gif and able to scan multiple large scale of images. 

**note**: If you want to convert the images into gif, you need to first split the gif into frames of images and then process those into ASCII Art.

## Usage
There is **one** ways to execute the programs with various operations:

---

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png
```

or

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 256 black
```

---

To see each commend functionality

```bash
bash execute.sh h
```

Output: 

```
Operations: -i    Path of input directory/image/image url. (First operation)
            -o    Path of output output directory/image/GIF/txt which can be NULL if no output file. (Second operation)
            -t    Path of characters file. default value: ASCII/Template1.txt (third operation)
            -n    Number of unique characters between 0 and 256 (**note that the input has to be in 2^n form). default value: 256 (fourth operation)
            -c    Color of the ASCII Art. (red, blue, green, orange, yellow, black) default value: black (fifth operation)
            -h    Help menus
            Example: bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 256 black
```

## Demonstrations
<img src="./readmeFile/demonstration1.gif" width="1000" height="300" />

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 256 black

bash execute.sh Sample/bear/ Sample_Output/bear/ ASCII/Template1.txt 256 red
```


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
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 64 black
```

------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration5.png" width="512" height="512" /> |

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 256 red
```

------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration9.png" width="512" height="512" /> |

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1-R.txt 256 black
```



------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration7.gif" width="512" height="512" /> |

```bash
bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.txt ASCII/Template1.txt 256
```


------------------
| before | after |
| :---:  | :---: |
| <img src="./readmeFile/demonstration2.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration8.gif" width="512" height="512" /> |

```bash
bash execute.sh Sample/img/example_5.jpg NULL ASCII/Template1.txt 256
```


------------------
| before | after |
| :---:  | :---: |
| <img src="https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg" width="512" height="512" /> | <img src="./readmeFile/demonstration6.png" width="512" height="512" /> |

```bash
bash execute.sh https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg Sample_Output/Output.png ASCII/Template1.txt 256 black
```

------------------
| before | after |
| :---:  | :---: |
| <img src="https://i.pinimg.com/originals/2d/7a/d9/2d7ad949b607a598c8f6fd65c1d91f75.gif" width="512" height="512" /> | <img src="./readmeFile/demonstration10.gif" width="512" height="512" /> |

```bash
bash execute.sh Sample/Animation/ ./Sample_Output/Output.gif ASCII/Template1.txt 256 black
```
