package controller;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ImageCommand;
import controller.commands.SaveImage;
import controller.commands.LoadImage;
import controller.commands.Flip;
import controller.commands.Sharpen;
import controller.commands.Grayscale;
import controller.commands.GrayTransformation;
import controller.commands.Sepia;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Class to test the exceptions for the command objects. Their functionality was already tested in
 * the model tests.
 */
public class CommandTest {

  @Test
  public void testBrightenExceptions() {
    try {
      ImageCommand nullName = new Brighten(5, null, "new name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullNewName = new Brighten(5, "name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testFlipExceptions() {
    try {
      ImageCommand nullName = new Flip(null, "new name", "type");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullNewName = new Flip("name", null, "type");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullType = new Flip("name", "new name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testGrayscaleExceptions() {
    try {
      ImageCommand nullName = new Grayscale(null, "type", "new name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullType = new Grayscale("name", null, "new name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullNewName = new Grayscale("name", "type", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testLoadExceptions() {
    try {
      ImageCommand nullFilename = new LoadImage(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullName = new LoadImage("file name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testBlurExceptions() {
    try {
      ImageCommand nullFilename = new Blur(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullName = new Blur("file name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testSharpenException() {
    try {
      ImageCommand nullFilename = new Sharpen(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullName = new Sharpen("file name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testGrayscaleException() {
    try {
      ImageCommand nullFilename = new GrayTransformation(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullName = new GrayTransformation("file name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testSepiaExceptions() {
    try {
      ImageCommand nullFilename = new Sepia(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullName = new Sepia("file name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }

  @Test
  public void testSaveExceptions() {
    try {
      ImageCommand nullName = new SaveImage(null, "name");
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }

    try {
      ImageCommand nullFileName = new SaveImage("name", null);
    } catch (NullPointerException i) {
      assertNull(i.getMessage());
    }
  }
}

