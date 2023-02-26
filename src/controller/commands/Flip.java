package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

/**
 * Command to flip an image.
 */
public class Flip extends AbstractCommand {
  private final String type;
  private final String newName;

  /**
   * Simple constructor for the Flip command.
   *
   * @param name    the name of the IImageModel to be brightened/darkened.
   * @param newName the new name to save the image by in the image map.
   * @param type    the type of flip to be made (horizontal or vertical).
   */
  public Flip(String name, String newName, String type) {
    super(name);
    this.type = Objects.requireNonNull(type);
    this.newName = Objects.requireNonNull(newName);
  }

  /**
   * Creates a new ImageFile image that is horizontally flipped to the original.
   *
   * @return a new ImageFile
   */
  public ImageFile horizontalFlip(int height, int width, ImageFile image) {
    IPixel[][] newImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      int count = width - 1;
      for (int j = 0; j < width; j++) {
        newImage[i][j] = image.getPix()[i][count];
        count--;
      }
    }
    return new Image(newImage, image.getMax());
  }

  /**
   * Creates a new ImageFile image that is vertically flipped to the original.
   *
   * @return a new ImageFile
   */
  public ImageFile verticalFlip(int height, int width, ImageFile image) {
    IPixel[][] newImage = new Pixel[height][width];
    int count = height - 1;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newImage[i][j] = image.getPix()[count][j];
      }
      count--;
    }
    return new Image(newImage, image.getMax());
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile newImage;
    ImageFile image = images.get(this.name);
    int height = image.getPix().length;
    int width = image.getPix()[0].length;
    if (this.type.equals("horizontal")) {
      image = images.get(this.name);
      newImage = this.horizontalFlip(height, width, image);
    } else if (this.type.equals("vertical")) {
      image = images.get(this.name);
      newImage = this.verticalFlip(height, width, image);
    } else {
      throw new IllegalArgumentException("Not a compatible flip type.");
    }
    images.put(this.newName, newImage);
    System.out.println("Created new flipped image: " + this.newName);
  }
}
