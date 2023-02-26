package controller.commands;

import java.util.Objects;


/**
 * An abstracted version of a PPMCommand.
 */
public abstract class AbstractCommand implements ImageCommand {
  protected String name;

  /**
   * Basic constructor for an AbstractCommand.
   *
   * @param name the name of the file to be referred to
   */
  protected AbstractCommand(String name) {
    this.name = Objects.requireNonNull(name);
  }


}
