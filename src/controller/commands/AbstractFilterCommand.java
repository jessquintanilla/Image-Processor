package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

/**
 * An abstracted version of a filter command. Reduces duplication by abstracting all the similar
 * code for the filter commands and allows for easier additions of filter commands.
 */
public abstract class AbstractFilterCommand extends AbstractFilterTransCommand {
  private final double[][] filter;
  protected String newName;

  /**
   * Basic constructor for an AbstractFilterCommand. Initializes the name to the given name,
   * disallowing null names, and delegates the creation of the kernel to the specific filter.
   *
   * @param name    the name of the file to be referred to.
   * @param newName the new name to save the changed file as.
   */
  protected AbstractFilterCommand(String name, String newName) {
    super(name, newName);
    this.newName = Objects.requireNonNull(newName);
    this.filter = this.makeArray();
  }

  /**
   * Creates the kernel for the specific filter. This task is delegated to the specific filter's
   * class.
   *
   * @return the kernel for the filter.
   */
  protected abstract double[][] makeArray();

  private IPixel getColor(IPixel[][] pix, double[][] filter, ImageFile image) {
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < pix.length; i++) {
      for (int j = 0; j < pix[0].length; j++) {
        r += (int) (pix[i][j].getRed() * filter[i][j]);
        g += (int) (pix[i][j].getGreen() * filter[i][j]);
        b += (int) (pix[i][j].getBlue() * filter[i][j]);
      }
    }
    r = this.clamp(r, image.getMax());
    g = this.clamp(g, image.getMax());
    b = this.clamp(b, image.getMax());
    return new Pixel(r, g, b, image.getMax());
  }

  @Override
  public ImageFile applyFilter(double[][] filter, ImageFile image) {
    int filterHeight = filter.length;
    int filterWidth = filter[0].length;
    IPixel[][] newArray = new Pixel[filterHeight][filterWidth];
    IPixel[][] newImage = new Pixel[image.getPix().length][image.getPix()[0].length];
    for (int i = 0; i < image.getPix().length; i++) {
      for (int j = 0; j < image.getPix()[0].length; j++) {

        int sr = i - filter.length / 2;
        for (int x = 0; x < filterHeight; x++) {
          int sc = j - filter.length / 2;
          for (int y = 0; y < filterWidth; y++) {
            try {
              newArray[x][y] = image.getPix()[sr][sc];
            } catch (IndexOutOfBoundsException e) {
              newArray[x][y] = new Pixel(0, 0, 0, image.getMax());
            }
            sc++;
          }
          sr++;
        }
        newImage[i][j] = this.getColor(newArray, filter, image);
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