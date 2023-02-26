package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Downscaling;
import controller.commands.ImageCommand;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.LoadImage;
import controller.commands.SaveImage;
import controller.commands.Grayscale;
import controller.commands.GrayTransformation;
import controller.commands.Flip;
import controller.commands.Brighten;
import controller.commands.Mask;
import controller.commands.Mosaic;

import model.IImageModel;
import model.ImageModelImp;
import view.IView;

/**
 * Represents an implementation of a Controller for image manipulation. Has a IImageModel
 * as a field.
 */
public class ControllerImp implements IController, Features {
  final IImageModel model;
  IView view;
  final Readable rd;
  final Map<String, Function<Scanner, ImageCommand>> knownCommands;

  /**
   * A basic constructor for a ControllerImp.
   *
   * @param model the model that all the files will refer to.
   * @param rd    the user input the Controller should read commands from
   */
  public ControllerImp(IImageModel model, Readable rd) {
    this.model = Objects.requireNonNull(model);
    this.rd = Objects.requireNonNull(rd);
    this.knownCommands = new HashMap<>();
    this.view = null;
    this.putCommands();
  }

  /**
   * A basic constructor for a ControllerImp that also takes in a view.
   *
   * @param model the model that all the files will refer to.
   * @param rd    the user input the Controller should read commands from
   * @param view  the view to be used (most likely a GUI).
   */
  public ControllerImp(IImageModel model, Readable rd, IView view) {
    this.model = Objects.requireNonNull(model);
    this.rd = Objects.requireNonNull(rd);
    this.knownCommands = new HashMap<>();
    this.view = Objects.requireNonNull(view);
    this.putCommands();
  }

  /**
   * A simple constructor that takes in a readable and instantiates a new ImageModelImpl as the
   * model.
   *
   * @param rd the user input the Controller should read commands from
   */
  public ControllerImp(Readable rd) {
    this.model = new ImageModelImp();
    this.rd = Objects.requireNonNull(rd);
    this.knownCommands = new HashMap<>();
    this.view = null;
    this.putCommands();
  }

  @Override
  public void setView(IView v) {
    view = v;
    view.addFeatures(this);
    this.view.makeVisible();
  }

  void putCommands() {
    this.knownCommands.put("load", s -> new LoadImage(s.next(), s.next()));
    this.knownCommands.put("save", s -> new SaveImage(s.next(), s.next()));
    this.knownCommands.put("vertical-flip", s -> new Flip(s.next(), s.next(),
            "vertical"));
    this.knownCommands.put("horizontal-flip", s -> new Flip(s.next(), s.next(),
            "horizontal"));
    this.knownCommands.put("red-component", s -> new Grayscale(s.next(), s.next(), "red"));
    this.knownCommands.put("green-component", s -> new Grayscale(s.next(), s.next(),
            "green"));
    this.knownCommands.put("blue-component", s -> new Grayscale(s.next(), s.next(), "blue"));
    this.knownCommands.put("value-component", s -> new Grayscale(s.next(), s.next(),
            "value"));
    this.knownCommands.put("luma-component", s -> new Grayscale(s.next(), s.next(), "luma"));
    this.knownCommands.put("intensity-component", s -> new Grayscale(s.next(), s.next(),
            "intensity"));
    this.knownCommands.put("brighten", s -> new Brighten(Integer.parseInt(s.next()), s.next(),
            s.next()));
    this.knownCommands.put("blur", s -> new Blur(s.next(), s.next()));
    this.knownCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    this.knownCommands.put("sepia", s -> new Sepia(s.next(), s.next()));
    this.knownCommands.put("grayscale", s -> new GrayTransformation(s.next(), s.next()));
    this.knownCommands.put("downscale", s -> new Downscaling(s.next(), s.next(), s.nextInt(), s.nextInt()));
    this.knownCommands.put("mosaic", s -> new Mosaic(Integer.parseInt(s.next()), s.next(), s.next()));
    this.knownCommands.put("mask", s -> new Mask(s.next(), this.knownCommands.getOrDefault(s.next(), null), s.nextLine()));
  }

  @Override
  public void readCommand() {
    try {
      Scanner sc = new Scanner(this.rd);
      while (sc.hasNext()) {
        ImageCommand c;
        String in = sc.next();
        Function<Scanner, ImageCommand> cmd = this.knownCommands.getOrDefault(in.toLowerCase(),
                null);
        if (cmd == null) {
          throw new IllegalArgumentException("Command not supported.");
        } else {
          c = cmd.apply(sc);
          model.apply(c);
        }
      }
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("Not enough arguments.");
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Need to input an image.");
    }
  }

  // FEATURES ----------------------------------------------------------------------------------------------------------
  @Override
  public void openFile() {
    try {
      ImageCommand cmd = new LoadImage(this.view.openFile(), "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void saveFile() {
    try {
      ImageCommand cmd = new SaveImage(this.view.saveFile(), "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void blurImage() {
    try {
      ImageCommand cmd = new Blur("current", "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void sharpenImage() {
    try {
      ImageCommand cmd = new Sharpen("current", "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void hFlip() {
    try {
      ImageCommand cmd = new Flip("current", "current", "horizontal");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void vFlip() {
    try {
      ImageCommand cmd = new Flip("current", "current", "vertical");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void sepiaImage() {
    try {
      ImageCommand cmd = new Sepia("current", "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void grayImage() {
    try {
      ImageCommand cmd = new GrayTransformation("current", "current");
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void brightenImage() {
    try {
      String value = this.view.popUps("bright");
      if (value != null) {
        ImageCommand cmd = new Brighten(Integer.parseInt(value), "current",
                "current");
        this.model.apply(cmd);
        this.view.displayImage(this.model.getImage("current")); // display the new image
        this.view.makeVisible();
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else if (e instanceof NumberFormatException) {
        this.view.showErrorMessage("Increment needs to be an integer value.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void grayTypes() {
    try {
      String value = this.view.popUps("gray");
      if (value != null) {
        ImageCommand cmd = new Grayscale("current", "current", value);
        this.model.apply(cmd);
        this.view.displayImage(this.model.getImage("current")); // display the new image
        this.view.makeVisible();
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }

  @Override
  public void downscale() {
    try {
      String value1 = this.view.popUps("downscaleWidth");
      if (Integer.parseInt(value1) < 0 ||
              Integer.parseInt(value1) > this.model.getImage("current").getPix()[0].length) {
        this.view.showErrorMessage("Please input a valid width below "
                + this.model.getImage("current").getPix()[0].length);
        return;
      }
      String value2 = this.view.popUps("downscaleHeight");
      if (Integer.parseInt(value2) < 0 ||
              Integer.parseInt(value2) > this.model.getImage("current").getPix().length) {
        this.view.showErrorMessage("Please input a valid height below "
                + this.model.getImage("current").getPix().length);
        return;
      }
      ImageCommand cmd = new Downscaling("current",
              "current", Integer.parseInt(value1), Integer.parseInt(value2));
      this.model.apply(cmd);
      this.view.displayImage(this.model.getImage("current")); // display the new image
      this.view.makeVisible();
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage("Something went wrong. Please try again.");
      }
    }
  }

  @Override
  public void mosaic() {
    try {
      String value = this.view.popUps("mosaic");
      if (value != null) {
        ImageCommand cmd = new Mosaic(Integer.parseInt(value), "current", "current");
        this.model.apply(cmd);
        this.view.displayImage(this.model.getImage("current")); // display the new image
        this.view.makeVisible();
      }
    } catch (Exception e) {
      if (e instanceof NullPointerException) {
        this.view.showErrorMessage("Need to input an image.");
      } else {
        this.view.showErrorMessage(e.getMessage());
      }
    }
  }
}
