package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

/**
 * An abstracted version of a transformation command. Reduces duplication by abstracting all the
 * similar code for the transformation commands and allows for easier additions of transformation
 * commands.
 */
public abstract class AbstractTransformationCommand extends AbstractFilterTransCommand {

  private final double[][] filter;
  protected final String newName;

  /**
   * Basic constructor for an AbstractTransformationCommand. Initializes the name to the given name,
   * disallowing null names, and delegates the creation of the kernel to the specific
   * transformation.
   *
   * @param name    the name of the file to be referred to.
   * @param newName the new name to save the changed file as.
   */
  protected AbstractTransformationCommand(String name, String newName) {
    super(name, newName);
    this.newName = Objects.requireNonNull(newName);
    this.filter = this.makeArray();
  }

  /**
   * Creates the kernel for the specific transformation. This task is delegated to the specific
   * transformation's class.
   *
   * @return the kernel for the filter.
   */
  protected abstract double[][] makeArray();

  @Override
  public ImageFile applyFilter(double[][] filter, ImageFile image) {
    int height = image.getPix().length;
    int width = image.getPix()[0].length;
    IPixel[][] newImage = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = image.getPix()[i][j].getRed();
        int g = image.getPix()[i][j].getGreen();
        int b = image.getPix()[i][j].getBlue();
        r = this.clamp((int) ((filter[0][0] * r) + (filter[0][1] * g) + (filter[0][2] * b)), image.getMax());
        g = this.clamp((int) ((filter[1][0] * r) + (filter[1][1] * g) + (filter[1][2] * b)), image.getMax());
        b = this.clamp((int) ((filter[2][0] * r) + (filter[2][1] * g) + (filter[2][2] * b)), image.getMax());

        newImage[i][j] = new Pixel(r, g, b, image.getMax());
      }
    }
    return new Image(newImage, image.getMax());
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile image = images.get(this.name);
    ImageFile newImage = this.applyFilter(this.filter, image);
    images.put(newName, newImage);
  }
}