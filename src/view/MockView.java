package view;

import java.io.IOException;

import controller.Features;
import model.imagefile.ImageFile;

/**
 * This is a mock view for the IView that has a log to check that all of the MVC
 * works together correctly.
 */
public class MockView implements IView {
  private Appendable log;

  /**
   * Simple constructor for a mock view that has a log to append the method information to.
   *
   * @param log the appendable to display the information
   */
  public MockView(Appendable log) {
    this.log = log;
  }

  @Override
  public void makeVisible() {
    try {
      log.append("made visible\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void showErrorMessage(String error) {
    try {
      log.append("error message thrown: " + error + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public String openFile() {
    try {
      log.append("opened\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return "current";
  }

  @Override
  public String saveFile() {
    try {
      log.append("saved\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return "current";
  }

  @Override
  public void displayImage(ImageFile image) {
    try {
      log.append("displayed\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public String popUps(String type) {
    try {
      log.append("pop up with type: " + type + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return "0";
  }

  @Override
  public void addFeatures(Features features) {
    try {
      log.append("new feature added\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
