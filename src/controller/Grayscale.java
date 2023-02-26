package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

/**
 * Command to grayscale an image.
 */
public class Grayscale extends AbstractCommand {
  private final String type;
  private final String newName;

  /**
   * A simple constructor for a Grayscale command.
   *
   * @param name    the name of the IImageModel to be brightened/darkened.
   * @param type    the type of Grayscale to be made (red, green, blue, luma, value, or intensity).
   * @param newName the name of the IImageModel to be brightened/darkened.
   */
  public Grayscale(String name, String newName, String type) {
    super(name);
    this.type = Objects.requireNonNull(type);
    this.newName = Objects.requireNonNull(newName);
  }

  /**
   * Creates a new ImageFile Image that is a grayscaled version of the original.
   *
   * @param rgb The type of grayscale to be made, represented as a String
   * @return a new ImageFile
   */
  public ImageFile grayscale(String rgb, ImageFile image) {
    int height = image.getPix().length;
    int width = image.getPix()[0].length;
    IPixel[][] newImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int gray = this.grayscaleType(rgb, image.getPix()[i][j]);
        newImage[i][j] = new Pixel(gray, gray, gray, image.getMax());
      }
    }
    return new Image(newImage, image.getMax());
  }

  /**
   * Returns a grayscale value for a Pixel depending on what type of grayscale it is.
   *
   * @param type The type of grayscale
   * @param pix  The pixel to make a grayscaled version of
   * @return an integer to represent the grayscale value
   */
  private int grayscaleType(String type, IPixel pix) {
    switch (type.toLowerCase()) {
      case "red":
        return pix.getRed();
      case "blue":
        return pix.getBlue();
      case "green":
        return pix.getGreen();
      case "value":
        return Math.max(Math.max(pix.getRed(), pix.getBlue()), pix.getGreen());
      case "luma":
        return (int) Math.round((pix.getRed() * 0.2126) + (pix.getGreen() * 0.7152)
                + (pix.getBlue() * 0.0722));
      case "intensity":
        return (pix.getRed() + pix.getGreen() + pix.getBlue()) / 3;
      default:
        throw new IllegalArgumentException("Not a permitted grayscale type.");
    }
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile image = images.get(this.name);
    ImageFile newImage = this.grayscale(this.type, image);
    images.put(newName, newImage);
    System.out.println("Created new grayscale images with " + this.type + " type: " + this.newName);
  }
}
