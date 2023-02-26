package model;

import java.io.IOException;

import controller.commands.ImageCommand;
import model.imagefile.IPixel;
import model.imagefile.ImageFile;

/**
 * This is a mock model for the IImageModel to ensure all the components of the MVC
 * are working together correctly.
 */
public class MockModel implements IImageModel {
  private Appendable log;

  /**
   * Simple constructor for a mock model that has a log to append the method information to.
   *
   * @param log the appendable to display the information
   */
  public MockModel(Appendable log) {
    this.log = log;
  }

  @Override
  public void apply(ImageCommand cmd) {
    try {
      log.append("applied\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public String imageList() {
    try {
      log.append("applied\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return null;
  }

  @Override
  public ImageFile getImage(String name) {
    try {
      log.append("image to get: " + name + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return new model.imagefile.Image(new IPixel[1][1], 1);
  }
}
