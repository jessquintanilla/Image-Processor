package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import model.imagefile.ImageFile;

import javax.imageio.ImageIO;

/**
 * Command to save an image to an external source.
 */
public class SaveImage extends AbstractCommand {
  private final String filename;

  /**
   * A simple constructor for the SaveImage command.
   *
   * @param name     the name of the ImageFile to be saved.
   * @param filename the pathname the ImageFile should be saved to.
   */
  public SaveImage(String filename, String name) {
    super(name);
    this.filename = Objects.requireNonNull(filename);
  }

  private void savePPM(Map<String, ImageFile> images) {
    try {
      File newFile = new File(filename);
      FileWriter ourFileWriter = new FileWriter(newFile);
      ourFileWriter.write(images.get(this.name).imageToString());
      ourFileWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("An error occurred: " + e);
    }
  }

  private void saveImg(String fileType, Map<String, ImageFile> images) {
    ImageFile img = images.get(this.name);
    BufferedImage image = img.createImage();

    try {
      File file = new File(filename);
      ImageIO.write(image, fileType, file);
    } catch (IOException e) {
      throw new IllegalStateException("An error occurred: " + e);
    }
  }


  @Override
  public void command(Map<String, ImageFile> images) {
    String fileType = filename.substring(filename.length() - 3);
    if (fileType.equals("ppm")) {
      this.savePPM(images);
    } else {
      this.saveImg(fileType, images);
    }
    System.out.println("Saved image " + this.name + " to " + this.filename);
  }
}
