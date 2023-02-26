Command Line -----------------------------------------------------------------------
Supported Commands:
-file <filename>: add a script file in the command line, with commands to run in this program
-text: open up the text based interface, where you can type each indvidual command.
default (no inputs): if there are no arguments in the command line, the default interface is the GUI. This will open a page on your screen that you can interact with. Read more about the GUI interface below.

GUI Interface ----------------------------------------------------------------------
	The GUI interface has four major components. At the top of the screen, there are 10 buttons for the different commands. Right below that, there is an "Info" panel that provides details about the histogram. At the bottom, there are two larger panels: the "Current Image" panel and the "Histogram" panel, which will display the image and histogram when a user inputs one in. Every time a new image is opened, or the current image is edited, the image and histogram will update.
	
Operations:
	- Open: When you click this button, it will open a file dialog prompt. You can search through your own files and directories, or manually input a file path. You can also filter the file types to search more easily. Once you find an acceptable file, it will show up on the screen. 
	- Save: This button will also open the same prompt. You can manually type a file name and the current image will save to that directory with that file name. If you use an existing filename, it will override that file. If you try to save an image when there is no current image, an error will pop up.
	- Blur: Clicking this button will blur the current image. If you try to blur an image when there is no current image, an error will pop up. 
	- Sharpen: Clicking this button will sharpen the current image. If you try to sharpen an image when there is no current image, an error will pop up. 
	- Horizontal Flip: This will flip the image horizontally. If you try to flip an image when there is no current image, an error will pop up. 
	- Vertical Flip: This will flip the image vertically. If you try to flip an image when there is no current image, an error will pop up. 
	- Sepia Filter: This button will apply a sepia filter to the current image. If you try to apply a filter when there is no current image, an error will pop up.
	- Grayscale Filter: This button will automatically apply a grayscale filter, with the luma option. If you try to apply a filter when there is no current image, an error will pop up.
	- Brighten: When you click this button, a prompt will show up to prompt the user into inputting an increment value to brighten or darken the image by. Inputting a positive integer will brighten the image and a negative integer will darken the image. If you try to brighten when there is no current image, an error will pop up.
	- Grayscale Options: When you click this button, a prompt will show up with more grayscale option buttons. These buttons include: red, green, blue, intensity, luma, and value components to grayscale by. The user can choose how they want to grayscale their image. If you try to use one of these options when there is no current image, an error will pop up.
	- Mosaic: When you click this button, you will be prompted to input a mosaic scale. This scale is the amount of seeds you want to make the mosaic with. If you try to create a mosaic when there is no current image, an error will pop up.
	- Downscale: When you click this button, a prompt will show up prompting you to enter the width, then the height. The user can enter an integer value to downscale by, which must be lower than the original image size. If you try to downscale when there is no current image, an error will pop up.

Text Interface ---------------------------------------------------------------------
load <source-file> <name>: source-file is the image to be uploaded, and name is the new name to refer to it as while using the program
save <path-file> <name>: saves the image with the given name in the program to the users device with the new path name
vertical-flip <name> <name>: vertically flips the image with the first name and saves it to an image with the second name
horizontal-flip <name> <name>: horizontally flips the image with the first name and saves it to an image with the second name
red-component <name> <name>: creates a grayscaled image with the first name, using the red component of the image and saves it to an image with the second name
green-component <name> <name>: creates a grayscaled image with the first name, using the green component of the image and saves it to an image with the second name
blue-component <name> <name>: creates a grayscaled image with the first name, using the blue component of the image and saves it to an image with the second name
luma <name> <name>: creates a grayscaled image with the first name, using the red component of the image and saves it to an image with the second name
intensity <name> <name>: creates a grayscaled image with the first name, using the intensity component of the image and saves it to an image with the second name
value <name> <name>: creates a grayscaled image with the first name, using the value component of the image and saves it to an image with the second name
brighten <increment> <name> <name>: creates a brightened or darkened image with the first name, using the integer incremented value (positive integer for brightening, negative integer for darkening), and saves it to an image with the second name
blur <name> <name>: applies a blurring filter to an image with the first name, and saves it to an image with the second name
sharpen <name> <name>: applies a sharpening filter to an image with the first name, and saves it to an image with the second name
sepia <name> <name>: applies a sepia filter to an image with the first name, and saves it to an image with the second name
grayscale <name> <name>: applies a grayscale filter to an image with the first name, and saves it to an image with the second name
mask <gray-image-name> <image-command> <image-command-args ...>: applies a given image command filter to an image with a given gray-scaled image as a mask
downscale <name> <name> <width> <height> downscales a given image to the width and height (must be lower than the original image) given
mosaic <num-seeds> <name> <name> creates a mosaic of an image with the given number of seeds

Example Usages:
load res/vidoje.png vido-png
load res/vidoje.bmp vido-bmp
load gray-image.pmg gray

vertical-flip vidoje vidoje-vert
horizontal-flip vidoje vidoje-hor
brighten 100 vidoje vidoje-bright
mask gray sharpen vido-png vido-mask
mask gray brighten 100 vido-png vido-bright-mask
downscale vido-bmp vido-small 100 100

save Vidoje-vert.ppm vidoje-vert
save Vidoje-hor.ppm vidoje-hor
save Vidoje-bright.ppm vidoje-bright

Using the Image Commands -----------------------------------------------------------
	The load command should always be used before any other command. An image needs to always be loaded from the users device to be able to be edited. Afterward, loading a new image, editing an image, or saving an image can be done in any order.
	The first name argument in any command (other than load) must be the name of an image that has already been loaded to the program. If a name has not been loaded to the program, an error will be thrown.
	File types such as PPM, JPG, PNG, BMP, among others, are supported in this program.
	Using the -file command in the command line will run the given script (if it is a valid script), and will exit once done. The script should use the commands similarly to the text interface.
	Using the -text command will allow you to input the commands through typing.
No command will default to the GUI interface.
