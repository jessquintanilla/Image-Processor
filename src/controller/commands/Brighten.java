package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

/**
 * Command to brighten or darken an image.
 */
public class Brighten extends AbstractCommand {
  private final int increment;
  private final String newName;

  /**
   * Simple constructor for the command to brighten an image.
   *
   * @param name      the name of the IImageModel to be brightened/darkened.
   * @param increment the value to increment the image by.
   * @param newName   the new name to save the image by in the image map.
   */
  public Brighten(int increment, String name, String newName) {
    super(name);
    this.increment = increment;
    this.newName = Objects.requireNonNull(newName);
  }

  /**
   * Adds the color and inc values together and clamps the sum to 0 if negative and 255 if greater
   * than 255.
   *
   * @param color the value of the hue of a Pixel.
   * @param inc   the value to increment or decrement that hue value
   * @return the sum of color and inc, with a minimum of 0 and a maximum of 255
   */
  private int clamp(int color, int inc, int max) {
    int min = Math.min(color + inc, max);
    return Math.max(min, 0);
  }

  /**
   * Creates a new ImageFile image that is either a brightened or darkened version of the original.
   *
   * @param increment the scale which to brighten (positive) or darken (negative) an image
   * @return a new ImageFile
   */
  private ImageFile brighten(int increment, ImageFile image) {
    IPixel[][] oldImage = image.getPix();
    int height = oldImage.length;
    int width = oldImage[0].length;
    Pixel[][] newImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = this.clamp(oldImage[i][j].getRed(), increment, image.getMax());
        int green = this.clamp(oldImage[i][j].getGreen(), increment, image.getMax());
        int blue = this.clamp(oldImage[i][j].getBlue(), increment, image.getMax());
        newImage[i][j] = new Pixel(red, green, blue, image.getMax());
      }
    }
    return new Image(newImage, image.getMax());
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile image = images.get(this.name);
    ImageFile newImage = this.brighten(increment, image);
    images.put(newName, newImage);
    System.out.println("Created new brightened image: " + this.newName);
  }
}
