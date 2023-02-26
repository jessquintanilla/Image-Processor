package controller.commands;

import model.imagefile.ImageFile;
import java.util.Map;

/**
 * Command to blur an image. Applies a specific blur kernel to the pixels of an image.
 */
public class Blur extends AbstractFilterCommand {

  /**
   * Simple constructor for the blur command.
   *
   * @param name    the name of the IImageModel to be brightened/darkened.
   * @param newName the new name to save the image by in the image map.
   */
  public Blur(String name, String newName) {
    super(name, newName);
  }

  @Override
  protected double[][] makeArray() {
    return new double[][]{
            new double[]{0.0625, 0.125, 0.0625},
            new double[]{0.125, 0.25, 0.125},
            new double[]{0.0625, 0.125, 0.0625}};
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    super.command(images);
    System.out.println("Created new blurred image: " + this.newName);
  }
}