package controller.commands;

import model.imagefile.ImageFile;

import java.util.Map;

/**
 * Command to create a sepia version of an image. Applies a sepia kernel to the pixels of an
 * image.
 */
public class Sepia extends AbstractTransformationCommand {

  /**
   * Simple constructor for the sepia transformation.
   *
   * @param name    the name of the IImageModel to be transformed.
   * @param newName the new name to save the image by in the image map.
   */
  public Sepia(String name, String newName) {
    super(name, newName);
  }

  @Override
  protected double[][] makeArray() {
    return new double[][]{
            new double[]{0.393, 0.769, 0.189},
            new double[]{0.349, 0.686, 0.168},
            new double[]{0.272, 0.534, 0.131}};
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    super.command(images);
    System.out.println("Created new image with sepia filter: " + this.newName);
  }
}