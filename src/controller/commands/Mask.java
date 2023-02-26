package controller.commands;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;

import java.io.StringReader;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Implementation of a masking filter.
 */
public class Mask implements ImageCommand {
  private final Function<Scanner, ImageCommand> cmd;
  private final String line;

  private String name;
  private String newName;
  private final String grayImage;

  /**
   * Basic constructor for an AbstractCommand.
   *
   * @param line the name of the file to be referred to
   */
  public Mask(String grayImage, Function<Scanner, ImageCommand> cmd, String line) {
    this.cmd = Objects.requireNonNull(cmd);
    this.line = Objects.requireNonNull(line);
    Scanner args = new Scanner(new StringReader(line));
    Scanner args2 = new Scanner(new StringReader(line));
    try {
      Integer.parseInt(args.next());
      this.name = args.next();
      this.newName = args.next();
    } catch (NumberFormatException e) {
      this.name = args2.next();
      this.newName = args2.next();
    }
    this.grayImage = Objects.requireNonNull(grayImage);
  }

  /**
   * Finds all the black pixels in the grayImage and creates a new image accordingly.
   *
   * @param oldImage      the old Image to apply the mask to
   * @param grayImage     the grayscale Image that acts as the mask
   * @param filteredImage the filtered Image to apply on top of the mask
   * @return a new Image with pixels from the original and filtered image based on the mask
   */
  private ImageFile getBlackPixels(ImageFile oldImage, ImageFile grayImage,
                                   ImageFile filteredImage) {
    IPixel[][] pix = oldImage.getPix();
    IPixel[][] gray = grayImage.getPix();

    int height = gray.length;
    int width = gray[0].length;
    IPixel[][] newPixels = new IPixel[height][width];

    IPixel[][] filter = filteredImage.getPix();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (0 == gray[i][j].getGreen() && 0 == gray[i][j].getRed()
                && gray[i][j].getBlue() == 0) {
          newPixels[i][j] = filter[i][j];
        } else {
          newPixels[i][j] = pix[i][j];
        }
      }
    }
    return new Image(newPixels, newPixels[0][0].getMax());
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile oldImage = images.get(this.name);
    ImageFile grayImage = images.get(this.grayImage);

    if (oldImage.getPix().length != grayImage.getPix().length
            && oldImage.getPix()[0].length != grayImage.getPix()[0].length) {
      throw new IllegalArgumentException("The grayscale image and the "
              + "original image must be the same size.");
    }

    Scanner sc = new Scanner(new StringReader(this.line));
    ImageCommand command = this.cmd.apply(sc);
    command.command(images);

    ImageFile img = this.getBlackPixels(oldImage, grayImage, images.get(this.newName));
    images.put(this.newName, img);
    System.out.println("Created new masked image: " + this.newName);
  }
}
