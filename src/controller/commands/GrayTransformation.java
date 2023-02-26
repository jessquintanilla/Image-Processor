package controller.commands;

import model.imagefile.ImageFile;
import java.util.Map;

/**
 * Command to create a grayscale image Applies a luma grayscale kernel to the pixels of an
 * image.
 */
public class GrayTransformation extends AbstractTransformationCommand {

  /**
   * Simple constructor for the grayscale transformation.
   *
   * @param name    the name of the IImageModel to be brightened/darkened.
   * @param newName the new name to save the image by in the image map.
   */
  public GrayTransformation(String name, String newName) {
    super(name, newName);
  }

  @Override
  protected double[][] makeArray() {
    return new double[][]{
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722}};
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    super.command(images);
    System.out.println("Created new grayscaled image: " + this.newName);
  }
}