package controller.commands;

import model.imagefile.ImageFile;
import java.util.Map;

/**
 * Command to Sharpen an image. Applies a specific sharpen kernel to the pixels of an image.
 */
public class Sharpen extends AbstractFilterCommand {

  /**
   * Simple constructor for the sharpen command.
   *
   * @param name    the name of the IImageModel to be sharpened.
   * @param newName the new name to save the image by in the image map.
   */
  public Sharpen(String name, String newName) {
    super(name, newName);
  }

  @Override
  protected double[][] makeArray() {
    return new double[][]{
            new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
            new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
            new double[]{-0.125, 0.25, 1, 0.25, -0.125},
            new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
            new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}};
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    super.command(images);
    System.out.println("Created new sharpened image: " + this.newName);
  }
}