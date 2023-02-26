package controller.commands;

import model.imagefile.ImageFile;

import java.util.Map;
import java.util.Objects;

/**
 * An abstracted version of a transformation command. Reduces duplication by abstracting all the
 * similar code for the transformation commands and allows for easier additions of transformation
 * commands.
 */
public abstract class AbstractFilterTransCommand extends AbstractCommand {

  private final double[][] filter;
  protected final String newName;

  /**
   * Basic constructor for an AbstractFilterTransCommand. Initializes the name to the given name,
   * disallowing null names, and delegates the creation of the kernel to the specific
   * transformation.
   *
   * @param name    the name of the file to be referred to.
   * @param newName the new name to save the changed file as.
   */
  protected AbstractFilterTransCommand(String name, String newName) {
    super(name);
    this.newName = Objects.requireNonNull(newName);
    this.filter = this.makeArray();
  }


  /**
   * Adds the color and inc values together and clamps the sum to 0 if negative and 255 if greater
   * than 255.
   *
   * @param color the value of the hue of a Pixel.
   * @return the sum of color and inc, with a minimum of 0 and a maximum of 255
   */
  public int clamp(int color, int max) {
    int min = Math.min(color, max);
    return Math.max(min, 0);
  }

  /**
   * Creates the kernel for the specific transformation. This task is delegated to the specific
   * transformation's class.
   *
   * @return the kernel for the filter.
   */
  protected abstract double[][] makeArray();

  /**
   * Creates a new ImageFile with the given color transformation filter applied to all the pixels.
   *
   * @param filter the color transformation filter to be applied to the ImageFile
   * @return a new ImageFile
   */
  protected abstract ImageFile applyFilter(double[][] filter, ImageFile image);

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile image = images.get(this.name);
    ImageFile newImage = this.applyFilter(this.filter, image);
    images.put(newName, newImage);
  }
}