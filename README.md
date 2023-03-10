# Image Manipulation

## How To Use

You can use this program in one of three ways: a text based interface, scripting, or with a GUI interface. 

For a text based interface simply type "-text" when running thr program.

For scripting type "-file filename", where filename is the file of the script you want to use. 

The default is a GUI interface, so you do not need to type anything for the GUI page to pop up. 

Once the program is running, there are commands to load, save, and edit images. Read the USEME to find out more about how to use the commands on each of these interfaces.
	
	
## Design Overview

### ImageUtil 

 - *ImageUtil:* This class runs the Image Manipulation program using main. The user can input commands from their terminal and can expect certain responses affirming that their commands are working (ex: Loaded image: image-name). 

### Controller
 
  - *IController:* In the controller package, we have an IController. This is an interface that specifies the methods that an implementation of a Controller should have. There is two public methods: readCommand, which is used for text or script based inputs, and setView which is used for GUI view inputs.
  - *Features:* This Features interface represents the commands that would allow the view to display a new image and use the ImageCommands. This interface has a method for each of the ImageCommands.
  - *ControllerImp:* The ControllerImp class implements an IController. There are two constructors: one that takes in two fields (an IImageModel and a Readable) one that simply takes in a Readable and instantiates a new ImageModelImp. These constructors call the private putCommands methods to make a HashMap of all of the ImageCommands. When running the program in the ImageUtil class, the Readable would be system in. This class also implements the readCommands method. This method reads through the Readable inputs, finds the command (which should be the first input), its arguments, and creates a new Command from this information. An IllegalArgumentException is thrown if the command is not supported and throws an IllegalStateException if there is not enough inputs to create a Command. Once a Command is successfully made, it is passed to the model (through the apply method). This controller also implements the Features interface. This allows the controller to make calls to the view. The setView method is implemented and instatiates the view, as well as adds the Features to the view and makes the view visible. It also implements the Features methods, which each call their respective ImageCommand, use the model to apply it, and update the view.
  
**Commands:**
 - *ImageCommand:* This interface specifies methods for a Command in Image Manipulation. There are two public methods: getImage (which would get a specific ImageFile from an IImageModel list) and a command method (which would run the specific command).
 - *AbstractCommand:* This abstracted class implements an ImageCommand. It has a basic constructor with just one field: a name for a specific command (usually the name of the image to be edited). It also implements the getImage method, which goes through a map of ImageFiles and returns the one that matches the name field. It abstracts the command method. 
 - *Brighten:* The Brighten command has two additional fields in addition to the abstracted name field: increment and newName. Increment is the value to brighten by and newName is the new name to associate the new ImageFile with. Brighten goes through the Pixel array and creates a new array with each Pixel value being incremented by the given amount. It calls a private clamp method to ensure the value does not go above the max and does not go below 0. The command method prints a helpful message when finished. 
  - *Flip:* The Flip command has two additional fields in addition to the abstracted name field: type and newName. Type is the type of Flip to be made (either horizontal or vertical) and newName is the new name to associate the new ImageFile with. HorizontalFlip also goes through the original Image Pixel array and creates a new one, but each width value is flipped (with length 4: 0 index value would now have 4 index value and vice versa - the middle would be the same). This also returns a new ImageFile. VerticalFlip does the same thing, but flips the height values instead of the width. The command method prints a helpful message when finished. 
  - *Grayscale:* The Grayscale command has two additional fields in addition to the abstracted name field: type and newName. Type is the type of Grayscale to be made (red, green, blue, value, luma, or intensity) and newName is the new name to associate the new ImageFile with. The grayscale method takes in a specific type of grayscale and goes through each Pixel in the array to make a new Pixel array with grayed out values instead of the original. It returns a new ImageFile. This method also calls the grayscaleType method, which reads the specific type of grayscale and creates a new Pixel based off of the original Pixel values and the type. The command method prints a helpful message when finished.
  - *LoadImage:* The LoadImage command has one field in addition to the abstracted name field: filename. Filename is the path to retrieve an ImageFile from. It uses the abstracted name field as the name to save the new ImageFile as. The command method is implemented and processes an ImageFile. It has a private makePixels file to go through each integer in the file and attribute it to a Pixel. It also prints a helpful message when finished.
  - *SaveImage:* The SaveImage command has one field in addition to the abstracted name field: filename. Filename is the path to save an ImageFile to. It uses the abstracted name field as the name to save the new ImageFile as. The command method is implemented and uses the "imageToString" method for an ImageFile to convert all of the ImageFile data to a PPM file. For other file types, it uses a BufferedImage to create a new image from our ImageFile object. It also prints a helpful message when finished.
  - *AbstractFilterTransCommand:* Abstracts the filter and transformation commands. This abstract class has a field for a filter and a new name to save the image as. It also has a clamp method which is useful for its inheritors (AbstractFilterCommand and AbstractTransformationCommand), as well as two abstracted methods and the command method. This class extends AbstractCommand.
    - *AbstractFilterCommand:* Abstracts the filter commands with fields for the filter matrix and the new name to save the image as (Blur and Sharpen inherit this). This abstract class also has a command method that gets the image with the specified name from the model map, then calls applyFilter method on that image. The applyFilter method uses a given matrix to create a new image with that filter matrix (Blur and Sharpen commands use this method). This method calls the getColor method to get the value of the filter applied to a specific pixel. This class extends the AbstractFilterTransCommand.
    - *AbstractTransformationCommand:* Abstracts the filter commands with fields for the filter matrix and the new name to save the image as (GrayTransformation and Sepia inherit this). This abstract class also has a command method that gets the image with the specified name from the model map. The applyFilter method also uses a given filter matrix to edit an Image (Sepia and GrayTransformation commands use this method). This class extends the AbstractFilterTransCommand.
  - *Blur:* This command inherits the fields from the AbstractFilterCommand, has a specific filter matrix to create the blurred image, and calls the AbstractFilterCommand command method.
  - *Sharpen:* This command inherits the fields from the AbstractFilterCommand, has a specific filter matrix to create the sharpened image, and calls the AbstractFilterCommand command method.
  - *GrayTransformation:* This command inherits the fields from the AbstractTransformationCommand, has a specific filter matrix to create the grayscaled image, and calls the AbstractTransformationCommand command method.
  -  *Sepia:* This command inherits the fields from the AbstractTransformationCommand, has a specific filter matrix to create the sepia filtered image, and calls the AbstractTransformationCommand command method.
  - *Mask:* This command implements the ImageCommand interface. This takes in a Function<Scanner, ImageCommand> as the command to apply on the mask. It also takes in a String to represent a grayscaled ImageFile that must be loaded into the model before the mask is applied. There is also a string field that represents the rest of the arguments for the ImageCommand to be applied to the mask. There is the command method, as well as a checkBlackPixels private method.
  - *Downscaling:* This class implements the AbstractCommand interface. It takes in a name, a new name, a new width, and a new height. It has the one inherited command method that focuses on creating the downscaled image. It also has a helper makeP to make a downscaled version of the pixel.
    
**Action Events:**
  - *Open file:* Will open a dialog prompt for the user to choose a file and load the given file.
  - *Save file:* Will also open a dialog prompt for the user to choose a filename to save the current image to and will save the image to that name.
  - *Blur Image:* will blur the current image.
  - *Sharpen image:* will sharpen the current image. 
  - *Sepia image:v will apply a sepia filter to the current image.
  - *Grayscale image:* will apply a grayscale filter to the current image.
  - *Horizonatlly flip:* will flip the current image horizontally.
  - *Vertically flip:* will flip the current image vertically. 
  - *Brighten image:* will open a prompt for the user to type in the increment to brighten by. Will then apply this increment and brighten or darken the image.
  - *Gray Type:* will open a prompt for the user to choose the type of grayscale they want. Will then apply the grayscale to the current image.
  
  
### Model

 - *IImageModel:* This interface represents a model for Image Manipulation. It specifies the methods an implementation of an IImageModel should have. There are two methods: apply and imageList. 
 - *ImageModelImp:* This class represents an implementation for an IImageModel. It has one field: a map of String and ImageFiles with the field name "images". There are two constructors: one that creates a new HashMap for the images, and one that takes in a map to instantiate the images field with. The apply method simply calls "command" on the given ImageCommand and passes its images field to it. The imageList method returns a String of all of the String names in the images map as a list, separated by a new line. This would be helpful if the user would want to see a list of their images made so far (although the controller command has yet to be implemented) and is useful for testing purposes. 
   - *MockModel:* This mock model implements all of the methods of the IImageModel interface and appends messages to an Appendable field. This allows us to make sure that the MVC is interacting correctly.
 
**ImageFiles:**
  - *IPixel:* An IPixel represents a Pixel, with methods getRed, getGreen, and getBlue. It also has the equals and hashCode methods for a new Object.
  - *Pixel:* The Pixel class represents an implementation of an IPixel. It has four fields: red, green, blue, and max values represented as integers. The constructor takes in those values and throws an IllegalArgumentException if these values are negative. It implements the getRed, getGreen, and getBlue methods and they simply return the field of the same name. The red, green, and blue values represent certain colors of a Pixel. The max is the maximum value a Pixel color field can be. It also has the equals and hashCode methods for a new Object. Two Pixels are equal if they have the same red, green, and blue values.
  - *ImageFile:* An ImageFile is an interface that represents an image. There are multiple methods to edit an image: grayscale, horizontalFlip, verticalFlip, and brighten. There is also an imageToString method that converts an ImageFile into a human readable String.
  - *Image:* An Image object implements ImageFile. It has four fields: a height, a width, a max, and a 2D array of Pixels. The max represents the max value of a Pixel. The 2D array is all of the Pixels in an ImageFile. It has one constructor that takes in an array of Pixels and a max value. The height and width are determined by the array of Pixels. ImageToString converts the PPM file into a human readable String with all of the height, width, max, and Pixel values. 
  
  
### View

  - *IView:* This interface represents a view for Image Manipulation. It specifies the methods an implementation of an IView should have. This interface has multiple methods: makeVisible, showErrorMessage, openFile, saveFile, displayImage, popUps, and addFeatures. 
  - *ImageGraphicsView:* This class implements an IView. It has multiple fields to represent different buttons, and a few different panels. The constructor instantiates these and creates the GUI interface. There is a private makeButton method that instantiates a button. The makeVisible methods sets the visibility of this class to true and the showErrorMessage displays an error popup with the given text. The openFile and saveFile methods open a dialog prompt for a user to choose a file path to either open that path or save an image to that path. The displayImage method displays the given image to the screen, as well as the histogram and histogram information. The popUps method handles pop up prompts, such as the ones to choose the grayscale type or the increment value for the brighten command. The addFeatures method adds the action listeners for each button, which convert the event to a call to the respective Features method. This class extends JFrame to display the GUI. 
  - *Histogram:* The histogram object has fields for the max x and y values of a histogram, the different arrays of instances of each pixel value for the red, blue, green, and intensity components, as well as an array of Pixels to derive this information from. The constructor takes in an IPixel 2D array, sets the visibility to true, creates a border, and calls a private setFields method. The setFields method derives the rest of the field values from the IPixel field. There is another private method to get the maximum value of an array to set the maxX and maxY fields. Since this class extends JPanel, we are able to override the paintComponent method. This method draws each of the values of each array field, (The index is the x and the value at that index is y), while scaling to the panel size. The histogramInfo method takes the information from this class's fields to create a JLabel with that information to display on thew view interface. We thought that displaying this information along with the histogram was the best design for the reader to understand the histogram values. There are also the equals and hashCode methods required to be overrided for new objects.
  - *MockView:* This mock view implements all of the methods of the IView interface and appends messages to an Appendable field. This allows us to make sure that the MVC is interacting correctly.
  
  
## Completed Features 

We have many image editing commands, including blurring, sharpening, and applying filters like grayscale or sepia to an image, among others. There are three ways to interact with our program, through text, through scripting, or using GUIs. You can input an image, edit it, and save it using any of these user-program interaction types. With the GUI, you will be able to see your image, a histogram and details of the image, as well as edit your image and see your changes in real time. There is also compatibility for other file types (such as jpg or png), which can be edited and exported as other file types (jpg to png).
