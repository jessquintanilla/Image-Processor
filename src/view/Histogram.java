package view;

import model.imagefile.IPixel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a Histogram chart. This chart represents red, green, blue, and intensity
 * components of an array of IPixels.
 */
public class Histogram extends JPanel {
  int maxX;
  int maxY;
  int[] red;
  int[] blue;
  int[] green;
  int[] intensity;
  IPixel[][] pix;

  /**
   * A basic constructor for a histogram. Takes in a array of pixels.
   *
   * @param pix the array of Pixels
   */
  public Histogram(IPixel[][] pix) {
    this.pix = Objects.requireNonNull(pix);
    this.setVisible(true);
    this.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.setFields();
  }

  /**
   * Set the fields of the histograms. Creates the array of values for the histogram.
   */
  private void setFields() {
    this.maxX = pix[0][0].getMax();
    this.red = new int[maxX + 1];
    this.blue = new int[maxX + 1];
    this.green = new int[maxX + 1];
    this.intensity = new int[maxX + 1];

    // these arrays have the count for each pixel value
    for (int i = 0; i < this.pix.length; i++) {
      for (int j = 0; j < this.pix[0].length; j++) {
        int r = this.pix[i][j].getRed();
        int g = this.pix[i][j].getGreen();
        int b = this.pix[i][j].getBlue();
        int in = (r + g + b) / 3;
        red[r] = red[r] + 1;
        blue[b] = blue[b] + 1;
        green[g] = green[g] + 1;
        intensity[in] = intensity[in] + 1;
      }
    }

    this.maxY = Math.max(this.maxArray(red), Math.max(this.maxArray(blue),
            Math.max(this.maxArray(green), this.maxArray(intensity)))); // the max y value
  }

  /**
   * Find the max value of an array.
   *
   * @param array the array to look through
   * @return the max value
   */
  private int maxArray(int[] array) {
    int max = 0;
    for (int i = 0; i < array.length; i++) {
      max = Math.max(array[i], max);
    }
    return max;
  }

  @Override
  public void paintComponent(Graphics graphic) {
    super.paintComponent(graphic);

    double index = (double) this.getWidth() / this.maxX; // value to scale x by
    double indexY = (double) this.getHeight() / this.maxY; // value to scale y by

    // draw each histogram
    for (int j = 0; j < red.length - 1; j++) {
      graphic.setColor(new Color(255, 0, 0));
      graphic.drawLine((int) (index * j), getHeight() - (int) (indexY * red[j]),
              (int) (index * (j + 1)), (getHeight() - (int) (indexY * red[j + 1])));
      graphic.setColor(new Color(0, 0, 255));
      graphic.drawLine((int) (index * j), getHeight() - (int) (indexY * blue[j]),
              (int) (index * (j + 1)), (getHeight() - (int) (indexY * blue[j + 1])));
      graphic.setColor(new Color(0, 255, 0));
      graphic.drawLine((int) (index * j), getHeight() - (int) (indexY * green[j]),
              (int) (index * (j + 1)), (getHeight() - (int) (indexY * green[j + 1])));
      graphic.setColor(new Color(0, 0, 0));
      graphic.drawLine((int) (index * j), getHeight() - (int) (indexY * intensity[j]),
              (int) (index * (j + 1)), (getHeight() - (int) (indexY * intensity[j + 1])));
    }
  }

  /**
   * Draws to the graphic for the panel that displays information about the histogram.
   */
  public JLabel histogramInfo() {
    int sum = 0;

    for (int i = 0; i < red.length; i++) {
      sum += red[i] + blue[i] + green[i] + intensity[i];
    }
    int mean = sum / (red.length * 4);

    String info = "Maximum Pixel Value: " + this.maxX + "  Maximum instances of pixels: "
            + this.maxY + "  Mean Pixel Value: " + mean + "  Red Median Pixel Value: "
            + this.red[this.red.length / 2] + "  Green Median Pixel Value: "
            + this.green[this.green.length / 2] + "  Blue Median Pixel Value: "
            + this.blue[this.blue.length / 2] + "  Intensity Median Pixel Value: "
            + this.intensity[this.intensity.length / 2];

    JLabel jlabel = new JLabel(info);
    jlabel.setFont(new Font("Verdana", Font.ITALIC, 13));
    return jlabel;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Histogram)) {
      return false;
    }
    Histogram other = (Histogram) o;
    return ((this.maxX == other.maxX) && (this.maxY == other.maxY)
            && Arrays.equals(this.red, other.red)
            && (Arrays.equals(this.blue, other.blue)) && Arrays.equals(this.green, other.green)
            && Arrays.equals(this.intensity, other.intensity)
            && Arrays.deepEquals(this.pix, other.pix));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.maxX, this.maxY, Arrays.hashCode(this.red),
            Arrays.hashCode(this.blue), Arrays.hashCode(this.green),
            Arrays.hashCode(this.intensity),
            Arrays.deepHashCode(this.pix));
  }
}
