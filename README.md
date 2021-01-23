# ASCII ART

This is a personal project that takes an **image file**, an **url image** or a **directory of images** as input and output an image or txt file of ASCII Art. Basically, it first convert RGB value of each pixels of an image into gray scale value. Then, since every gray scale value has a corresponding unique character, when translate the image into ASCII Art, it will map the value with its corresponding character. Currently, this project is supported to convert the image, the url of the image or the directory into ASCII Art with specific text color. It only could output ASCII Art as png format, text format or even to the terminal. In the future, My goal is to covert a list of ASCII Art into gif format. 

This project uses **AWT library** to scan the image and draw the text art of image into output image and **IO file handling library** to store and retrieve the file. There is a helper class that is defined inside the helper folder.


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
            -o    Path of output directory/image. (Second operation)
            -t    Path of characters file. default value: ASCII/Template1.txt (third operation)
            -n    Number of unique characters between 0 and 256 (**note that the input has to be in 2^n form). default value: 256 (fourth operation)
            -c    color of the ASCII Art. default value: black (fifth operation)
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