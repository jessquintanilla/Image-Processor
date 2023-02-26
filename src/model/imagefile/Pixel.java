package model.imagefile;

import java.util.Objects;

/**
 * This class represents a Pixel. A Pixel has a field for a red, green, and blue hue represented as
 * integers. These fields cannot be negative.
 */
public class Pixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;
  private final int max;

  /**
   * Represents a constructor for a Pixel. Takes in three integers for the red, green, and blue
   * hues of a Pixel.
   *
   * @param red   the red hue of a Pixel
   * @param green the green hue of a Pixel
   * @param blue  the blue hue of a Pixel
   */
  public Pixel(int red, int green, int blue, int max) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Pixel components cannot be negative.");
    }
    if (red > max || green > max || blue > max) {
      throw new IllegalArgumentException("Pixel cannot be greater than " + max);
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.max = max;
  }

  // METHODS ---------------------------------------------------------------------------------------

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public int getMax() {
    return this.max;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }
    Pixel other = (Pixel) o;
    return (((this.red == other.red) && (this.green == other.green) && (this.blue == other.blue)));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }
}
