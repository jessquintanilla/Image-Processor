package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.imagefile.ImageFile;
import model.imagefile.Image;
import model.imagefile.Pixel;

import javax.imageio.ImageIO;

/**
 * Command to load an image from an external source.
 */
public class LoadImage extends AbstractCommand {
  private final String filename;

  /**
   * Simple constructor for a LoadImage command.
   *
   * @param filename the file to be loaded for image manipulation.
   * @param name     the name the image should be referred to as.
   */
  public LoadImage(String filename, String name) {
    super(name);
    this.filename = Objects.requireNonNull(filename);
  }

  /**
   * Creates an array of Pixels.
   *
   * @param height the height of the array.
   * @param width  the width of the array.
   * @param sc     the data to make each individual Pixel.
   * @return a 2D array of Pixels.
   */
  private Pixel[][] makePixels(int height, int width, Scanner sc, int maxValue) {
    Pixel[][] pix = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel p = new Pixel(r, g, b, maxValue);
        pix[i][j] = p;
      }
    }
    return pix;
  }

  /**
   * Loads a PPM image type.
   *
   * @param images the map of images to save this new ImageFile to.
   */
  private void ppmLoad(Map<String, ImageFile> images) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] pix = this.makePixels(height, width, sc, maxValue);
    ImageFile image = new Image(pix, maxValue);
    images.put(name, image);
    System.out.println("Loaded new image: " + this.name);
  }

  /**
   * Loads different types of image files (such as jpeg, png, etc).
   *
   * @param images the map of images to save this new ImageFile to.
   */
  private void imgLoad(Map<String, ImageFile> images) {
    BufferedImage image;
    Pixel[][] pix;
    int height;
    int width;

    try {
      image = ImageIO.read(new File(filename));
      height = image.getHeight();
      width = image.getWidth();
      pix = new Pixel[height][width];
    } catch (IOException e) {
      System.out.println("File not found.");
      return;
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int pRGB = image.getRGB(j, i);
        Color color = new Color(pRGB, false);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        Pixel p = new Pixel(r, g, b, 255);
        pix[i][j] = p;
      }
    }

    ImageFile img = new Image(pix, 255);
    images.put(name, img);
    System.out.println("Loaded new image: " + this.name);
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    String fileType = filename.substring(filename.length() - 3);
    if (fileType.equals("ppm")) {
      this.ppmLoad(images);
    } else {
      this.imgLoad(images);
    }
  }
}
