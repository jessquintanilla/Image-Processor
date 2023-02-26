package view;

import controller.Features;
import model.imagefile.ImageFile;

/**
 * This interface represents a View. It has methods to display various commands, images,
 * and other messages for a View.
 */
public interface IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   *
   * @param error the error message
   */
  void showErrorMessage(String error);

  /**
   * Shows the file dialog to open an image.
   *
   * @return the filename of the image to be opened.
   */
  String openFile();

  /**
   * Shows the file dialog to save an image.
   *
   * @return the filename to be saved as.
   */
  String saveFile();

  /**
   * Displays an image to the screen.
   *
   * @param image the image to be displayed.
   */
  void displayImage(ImageFile image);

  /**
   * Handles any kind of pop up that the program may need.
   *
   * @param type the type of popup
   * @return the user response to the popup.
   */
  String popUps(String type);

  /**
   * Calls the controller to implement the features on each button in the view.
   *
   * @param features A Features object, which will implement the actual features.
   */
  void addFeatures(Features features);
}
