package controller.commands;

import java.util.Map;

import model.imagefile.ImageFile;

/**
 * Commands for image manipulation.
 */
public interface ImageCommand {
  /**
   * Alters the image in a certain way and updates the given map with the new altered image.
   * @param images the map to put the new image in
   */
  public void command(Map<String, ImageFile> images);
}
