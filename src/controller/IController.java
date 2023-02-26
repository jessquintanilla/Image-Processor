package controller;

import view.IView;

/**
 * Represents a controller for image manipulation.
 */
public interface IController {

  // public void putCommands();

  /**
   * Reads the commands given by the user and delegates the given commands to the model.
   */
  public void readCommand();

  /**
   * Sets the view by adding the controller features to it.
   *
   * @param view the view of the MVC of the image processor.
   */
  public void setView(IView view);

}
