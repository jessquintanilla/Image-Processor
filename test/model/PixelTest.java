package model;

import org.junit.Test;

import model.imagefile.IPixel;
import model.imagefile.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for the IPixel interface and the Pixel class.
 */
public class PixelTest {

  @Test
  public void testConstructorExceptions() {
    try {
     new Pixel(-1, 3, 5, 255);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel components cannot be negative.", i.getMessage());
    }

    try {
      new Pixel(3, -1, 5, 255);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel components cannot be negative.", i.getMessage());
    }

    try {
     new Pixel(3, 5, -1, 255);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel components cannot be negative.", i.getMessage());
    }

    try {
      new Pixel(11, 5, 3, 10);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel cannot be greater than 10", i.getMessage());
    }

    try {
     new Pixel(5, 11, 3, 10);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel cannot be greater than 10", i.getMessage());
    }

    try {
      new Pixel(5, 3, 11, 10);
    } catch (IllegalArgumentException i) {
      assertEquals("Pixel cannot be greater than 10", i.getMessage());
    }
  }

  @Test
  public void testGetRed() {
    IPixel pix = new Pixel(3, 5, 10, 15);
    assertEquals(3, pix.getRed());
    assertEquals(5, pix.getGreen());
    assertEquals(10, pix.getBlue());
  }

  @Test
  public void testGetMax() {
    IPixel pix = new Pixel(3, 5, 10, 15);
    assertEquals(15, pix.getMax());
    IPixel pix2 = new Pixel(3, 5, 10, 200);
    assertEquals(200, pix2.getMax());
  }
}