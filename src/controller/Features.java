package controller;

/**
 * A Features interface that has all the commands that would allow the view to make a
 * new image based on the ImageCommands.
 */
public interface Features {
  /**
   * The features method to open a file in the view.
   */
  void openFile();

  /**
   * Save an image using the Features interface for the view.
   */
  void saveFile();

  /**
   * Blur an image using the Features interface for the view.
   */
  void blurImage();

  /**
   * Sharpen an image using the Features interface for the view.
   */
  void sharpenImage();

  /**
   * Horizontally flip an image using the Features interface for the view.
   */
  void hFlip();

  /**
   * Vertically flip an image using the Features interface for the view.
   */
  void vFlip();

  /**
   * Sepia an image using the Features interface for the view.
   */
  void sepiaImage();

  /**
   * Grayscale an image using the Features interface for the view.
   */
  void grayImage();

  /**
   * Brighten or Darken an image using the Features interface for the view.
   */
  void brightenImage();

  /**
   * Create a grayscaled image of a specific type using the Features interface for the view.
   */
  void grayTypes();

  /**
   * Downscale an image using the Features interface for the view.
   */
  void downscale();

  /**
   * Mosaic an image using the Features interface for the view.
   */
  void mosaic();

}
