package controller.commands;

import model.imagefile.IPixel;
import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.Pixel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mosaic extends AbstractCommand {
  Integer numSeeds;
  String newName;
  private final Map<Pair, List<Pair>> clusters;
  private final Map<Pair, IPixel> averages;

  /**
   * Constructs a command class argument for the image processing program that can make mosaics.
   *
   * @param name the source image name the desired command is to be implemented on.
   * @param newName the destination name in which the image name is to be saved under after the
   *                  command has been implemented.
   * @param numSeeds  the number of seeds to make the mosaic with
   */
  public Mosaic(int numSeeds, String name, String newName) {
    super(name);
    this.numSeeds = numSeeds;
    this.newName = newName;
    this.clusters = new HashMap<>();
    this.averages = new HashMap<>();
  }

  private void findSeeds(Map<String, ImageFile> images) {
    // create a map with all the seeds as positions of pixels
    ImageFile image = images.get(name);
    IPixel[][] pixels = image.getPix();
    int height = pixels.length;
    int width = pixels[0].length;
    while (numSeeds > clusters.size()) {
      Pair p = new Pair((int) (Math.random() * width), (int) (Math.random() * height));
      this.clusters.put(p, new ArrayList<>());
      this.averages.put(p, pixels[p.x][p.y]);
    }
    System.out.println("findSeeds");
  }

  private void findNearestSeed(Map<String, ImageFile> images) {
    ImageFile image = images.get(name);
    IPixel[][] pixels = image.getPix();
    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pair temp = null;
        for (Map.Entry<Pair, List<Pair>> c : this.clusters.entrySet()) {
          int diff = c.getKey().compare(row, col);
          if (temp == null) {
            temp = c.getKey();
          } else if (diff < temp.compare(row, col)) {
            temp = c.getKey();
          }
        }
        List<Pair> l = clusters.get(temp);
        l.add(new Pair(row, col));
        clusters.put(temp, l);

        IPixel average = this.averages.get(temp);
        int r = (average.getRed() + pixels[row][col].getRed()) / 2;
        int g = (average.getGreen() + pixels[row][col].getGreen()) / 2;
        int b = (average.getBlue() + pixels[row][col].getBlue()) / 2;
        IPixel newAverage = new Pixel(r, g, b, average.getMax());
        this.averages.put(temp, newAverage);
      }
    }
    System.out.println("find nearest seed");
  }


  @Override
  public void command(Map<String, ImageFile> images) {
    ImageFile image = images.get(name);
    IPixel[][] pixels = image.getPix();
    IPixel[][] newPixels = new IPixel[pixels.length][pixels[0].length];
    this.findSeeds(images);
    this.findNearestSeed(images);
    System.out.println("setting all the pixels");
    for (Map.Entry<Pair, List<Pair>> c : this.clusters.entrySet()) {
      for (Pair p : c.getValue()) {
        newPixels[p.x][p.y] = this.averages.get(c.getKey());
      }
    }
    System.out.println("adding new image");
    ImageFile newImage = new Image(newPixels, newPixels[0][0].getMax());
    images.put(this.newName, newImage);
    System.out.println("Created new mosaic image: " + this.newName + " with " + this.numSeeds + " seeds");
  }
}