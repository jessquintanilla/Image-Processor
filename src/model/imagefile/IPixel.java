package model.imagefile;

/**
 * This interface represents an IPixel, which can have red, green, and blue values.
 */
public interface IPixel {

  /**
   * Calls the red field of a Pixel.
   *
   * @return the red field.
   */
  public int getRed();

  /**
   * Calls the green field of a Pixel.
   *
   * @return the green field.
   */
  public int getGreen();

  /**
   * Calls the blue field of a Pixel.
   *
   * @return the blue field.
   */
  public int getBlue();

  /**
   * Calls the max field of a Pixel.
   *
   * @return the max value.
   */
  public int getMax();
}
