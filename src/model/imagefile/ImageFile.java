package model.imagefile;

import java.awt.image.BufferedImage;

/**
 * This interface represents the operations for an Image file.
 */
public interface ImageFile {

  /**
   * Goes through the image and collects the data of the image as a string.
   *
   * @return String representation of an image
   */
  String imageToString();

  /**
   * Gets a copy of the pixel array for this Image.
   *
   * @return 2D array of IPixel
   */
  IPixel[][] getPix();

  /**
   * Gets the maximum value of the pixels in an image.
   *
   * @return the maximum value a pixel can have
   */
  int getMax();

  /**
   * Create a buffered image from this image.
   *
   * @return a new buffered image
   */
  BufferedImage createImage();
}
