package model.imagefile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents an implementation of an Image. An Image can have a PPM, JPEG, PNG, etc.
 * format.
 */
public class Image implements ImageFile {
  private final int height;
  private final int width;
  private final int max;
  private final IPixel[][] pix;

  /**
   * A basic constructor of an ImageFile that takes in a 2D array of Pixels, and determines the
   * height and width based on the array.
   *
   * @param pix A 2D array of Pixels of an image
   */
  public Image(IPixel[][] pix, int max) {
    this.pix = Objects.requireNonNull(pix);
    this.height = this.pix.length;
    this.width = this.pix[0].length;
    this.max = max;
  }

  // METHODS ---------------------------------------------------------------------------------------
  @Override
  public String imageToString() {
    Appendable image = new StringBuilder("P3\n" + width + "\n" + height + "\n" + max + "\n");
    for (IPixel[] iPixels : pix) {
      for (IPixel iPixel : iPixels) {
        String red = Integer.toString(iPixel.getRed());
        String green = Integer.toString(iPixel.getGreen());
        String blue = Integer.toString(iPixel.getBlue());
        try {
          image.append(red).append("\n").append(green).append("\n").append(blue).append("\n");
        } catch (IOException e) {
          throw new IllegalStateException("Error writing to appendable.");
        }
      }
    }

    return image.toString();
  }

  public IPixel[][] getPix() {
    return this.pix;
  }

  public BufferedImage createImage() {
    IPixel[][] pix = this.getPix();
    BufferedImage newImage = new BufferedImage(pix[0].length, pix.length,
            BufferedImage.TYPE_3BYTE_BGR);

    // create the new image
    for (int i = 0; i < pix.length; i++) {
      for (int j = 0; j < pix[0].length; j++) {
        Color c = new Color(pix[i][j].getRed(), pix[i][j].getGreen(), pix[i][j].getBlue());
        newImage.setRGB(j, i, c.getRGB());
      }
    }
    return newImage;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    Image other = (Image) o;
    return ((this.height == other.height) && (this.width == other.width) && (this.max == other.max)
            && Arrays.deepEquals(this.pix, other.pix));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width, this.max, Arrays.deepHashCode(this.pix));
  }

  public int getMax() {
    return this.max;
  }
}
