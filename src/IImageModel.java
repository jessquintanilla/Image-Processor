package model;

import controller.commands.ImageCommand;
import model.imagefile.ImageFile;

/**
 * This is a model for image manipulation. 
 */
public interface IImageModel {
  /**
   * Applies the given command with the map of ImageFile the model has.
   *
   * @param cmd the command to be made
   */
  public void apply(ImageCommand cmd);

  /**
   * Lists the names of all the images in the model as a String.
   *
   * @return a String with a list of image names.
   */
  public String imageList();

  /**
   * Gets the image from the given map if it exists.
   *
   * @param name the name of the ImageFile to be look for.
   * @return the ImageFile we are looking for.
   * @throws IllegalArgumentException if the image name does not exist.
   */
  public ImageFile getImage(String name);
}
