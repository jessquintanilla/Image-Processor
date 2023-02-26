package controller.commands;

import java.util.Map;
import java.util.Objects;

import model.imagefile.Image;
import model.imagefile.ImageFile;
import model.imagefile.IPixel;
import model.imagefile.Pixel;

/**
 * Class to downscale an image to one with a given width and height.
 */
public class Downscaling extends AbstractCommand {
  int newWidth;
  int newHeight;
  String newName;
  private int aRed;

  /**
   * Basic constructor for a downscale command.
   *
   * @param name      the name of the IImageModel to be downscaled.
   * @param newName   the new name to save the image by in the image map.
   * @param newWidth  the width of the new downsized image.
   * @param newHeight the height of the new downsized image.
   */
  public Downscaling(String name, String newName, int newWidth, int newHeight) {
    super(name);
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.newName = Objects.requireNonNull(newName);
  }

  @Override
  public void command(Map<String, ImageFile> images) {
    IPixel[][] newImageArray = new IPixel[newHeight][newWidth];
    ImageFile oldImage = images.get(this.name);
    IPixel[][] oldImageArray = oldImage.getPix();
    int oldHeight = oldImageArray.length;
    int oldWidth = oldImageArray[0].length;
    int pRed;
    int pGreen;
    int pBlue;
    IPixel newPixel;

    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {

        float x = ((float) (j + 1) * (float) oldWidth / (float) newWidth) - 1;
        float y = ((float) (i + 1) * (float) oldHeight / (float) newHeight) - 1;

        aRed = oldImageArray[(int) Math.floor(y)][(int) Math.floor(x)].getRed();
        int bRed = oldImageArray[(int) Math.ceil(y)][(int) Math.floor(x)].getRed();
        int cRed = oldImageArray[(int) Math.floor(y)][(int) Math.ceil(x)].getRed();
        int dRed = oldImageArray[(int) Math.ceil(y)][(int) Math.ceil(x)].getRed();
        int aGreen = oldImageArray[(int) Math.floor(y)][(int) Math.floor(x)].getGreen();
        int bGreen = oldImageArray[(int) Math.ceil(y)][(int) Math.floor(x)].getGreen();
        int cGreen = oldImageArray[(int) Math.floor(y)][(int) Math.ceil(x)].getGreen();
        int dGreen = oldImageArray[(int) Math.ceil(y)][(int) Math.ceil(x)].getGreen();
        int aBlue = oldImageArray[(int) Math.floor(y)][(int) Math.floor(x)].getBlue();
        int bBlue = oldImageArray[(int) Math.ceil(y)][(int) Math.floor(x)].getBlue();
        int cBlue = oldImageArray[(int) Math.floor(y)][(int) Math.ceil(x)].getBlue();
        int dBlue = oldImageArray[(int) Math.ceil(y)][(int) Math.ceil(x)].getBlue();

        pRed = this.makeP(aRed, bRed, cRed, dRed, x, y);
        pGreen = this.makeP(aGreen, bGreen, cGreen, dGreen, x, y);
        pBlue = this.makeP(aBlue, bBlue, cBlue, dBlue, x, y);

        if (pRed == 0 && pGreen == 0 && pBlue == 0) {
          pRed = aRed;
          pGreen = aGreen;
          pBlue = aBlue;
        }

        newPixel = new Pixel(pRed, pGreen, pBlue, oldImage.getMax());
        newImageArray[i][j] = newPixel;
      }
    }
    ImageFile newImage = new Image(newImageArray, oldImage.getMax());
    images.put(newName, newImage);
  }

  private int makeP(int a, int b, int c, int d, float x, float y) {
    float m = (float) b * (x - (float) Math.floor(x)) + (float) a * ((float) Math.ceil(x) - x);
    float n = (float) d * (x - (float) Math.floor(x)) + (float) c * ((float) Math.ceil(x) - x);
    return (int) (n * (y - Math.floor(y)) + m * (Math.ceil(y) - y));
  }
}
