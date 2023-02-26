package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import controller.commands.ImageCommand;
import model.imagefile.ImageFile;

/**
 * This is a model for image manipulation. Contains a map of ImageFiles and a specific name for
 * each of them to be able to manipulate.
 */
public class ImageModelImp implements IImageModel {
  private final Map<String, ImageFile> images;

  /**
   * Constructor for an ImageModelImp. Instantiates the images field to an empty hash map.
   */
  public ImageModelImp() {
    this.images = new HashMap<>();
  }

  /**
   * Constructor for an ImageModelImp, takes in a map of images.
   */
  public ImageModelImp(Map<String, ImageFile> images) {
    this.images = Objects.requireNonNull(images);
  }

  public void apply(ImageCommand cmd) {
    cmd.command(this.images);
  }

  @Override
  public String imageList() {
    Appendable s = new StringBuilder();
    for (Map.Entry<String, ImageFile> m : images.entrySet()) {
      try {
        s.append(m.getKey());
        s.append("\n");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return s.toString();
  }

  @Override
  public ImageFile getImage(String name) {
    ImageFile image;
    for (Map.Entry<String, ImageFile> i : this.images.entrySet()) {
      if (i.getKey().equals(name)) {
        image = i.getValue();
        return image;
      }
    }
    throw new IllegalArgumentException("No such file.");
  }
}