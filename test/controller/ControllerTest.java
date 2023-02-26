package controller;

import controller.commands.Brighten;
import controller.commands.Grayscale;
import controller.commands.LoadImage;
import model.IImageModel;
import model.ImageModelImp;
import model.MockModel;
import view.IView;
import view.MockView;

import org.junit.Test;

import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Controller. Tests that readCommand method affects the map in the model and
 * certain exceptions.
 */
public class ControllerTest {

  // CORRECT ARGUMENTS TEST --------------------------------------------------------------
  // These tests check that something is added to the model map after a command is made -
  // verifying the test is read correctly and the list of images is affected - these tests
  // do not test that each command works properly (that is tested elsewhere)

  @Test
  public void readCommandLoadTest() {
    Readable rd = new StringReader("load res/vidoje.ppm vido");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    assertEquals("", model.imageList());
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test
  public void readCommandLoadJpgTest() {
    Readable rd = new StringReader("load res/vidoje.jpg vido");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    assertEquals("", model.imageList());
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test
  public void readCommandLoadPngTest() {
    Readable rd = new StringReader("load res/vidoje.png vido");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    assertEquals("", model.imageList());
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test
  public void readCommandLoadBmpTest() {
    Readable rd = new StringReader("load res/vidoje.bmp vido");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    assertEquals("", model.imageList());
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test
  public void readCommandSaveTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.ppm vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.ppm").exists());
  }

  @Test
  public void readCommandSaveJpgTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.jpg", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.jpg vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.jpg").exists());
  }

  @Test
  public void readCommandSavePngTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.png", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.png vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.png").exists());
  }

  @Test
  public void readCommandSaveBmpTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.bmp", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.bmp vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.bmp").exists());
  }

  @Test
  public void readCommandCrossSaveTypePPMPNG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.png vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.png").exists());
  }

  @Test
  public void readCommandCrossSaveTypePPMBMP() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.bmp vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.bmp").exists());
  }

  @Test
  public void readCommandCrossSaveTypePPMJPG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.jpg vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.jpg").exists());
  }

  @Test
  public void readCommandCrossSaveTypePNGBMP() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.png", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.bmp vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.bmp").exists());
  }

  @Test
  public void readCommandCrossSaveTypePNGPPM() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.png", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.ppm vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.ppm").exists());
  }

  @Test
  public void readCommandCrossSaveTypePNGJPG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.png", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.jpg vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.jpg").exists());
  }

  @Test
  public void readCommandCrossSaveTypeJPGBMP() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.jpg", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.bmp vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.bmp").exists());
  }

  @Test
  public void readCommandCrossSaveTypeJPGPPM() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.jpg", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.ppm vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.ppm").exists());
  }

  @Test
  public void readCommandCrossSaveTypeJPGPNG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.jpg", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.png vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.png").exists());
  }

  @Test
  public void readCommandCrossSaveTypeBMPPPM() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.bmp", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.ppm vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.ppm").exists());
  }

  @Test
  public void readCommandCrossSaveTypeBMPJPG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.bmp", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.jpg vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.jpg").exists());
  }

  @Test
  public void readCommandCrossSaveTypeBMPPNG() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.bmp", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));

    Readable rd = new StringReader("save v-bright.png vido-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-bright.png").exists());
  }

  @Test
  public void readCommandSaveTwoTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));
    model.apply(new Brighten(100, "vido", "vido-bright"));
    model.apply(new Grayscale("vido-bright", "vido-red-bright", "red"));

    Readable rd = new StringReader("save v-red-bright.ppm vido-red-bright");
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertTrue(new File("v-red-bright.ppm").exists());
  }

  @Test
  public void readCommandVerticalTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("vertical-flip vido vido-vertical");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-vertical\nvido\n", model.imageList());
  }

  @Test
  public void readCommandHorizontalTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("horizontal-flip vido vido-horizontal");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-horizontal\n", model.imageList());
  }

  @Test
  public void readCommandRedTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("red-component vido vido-red");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-red\n", model.imageList());
  }

  @Test
  public void readCommandGreenTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("green-component vido vido-green");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-green\n", model.imageList());
  }

  @Test
  public void readCommandBlueTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("blue-component vido vido-blue");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-blue\n", model.imageList());
  }

  @Test
  public void readCommandLumaTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("luma-component vido vido-luma");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-luma\nvido\n", model.imageList());
  }

  @Test
  public void readCommandValueTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("value-component vido vido-value");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-value\n", model.imageList());
  }

  @Test
  public void readCommandIntensityTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("intensity-component vido vido-intensity");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-intensity\nvido\n", model.imageList());
  }

  @Test
  public void readCommandBrightenTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("brighten 10 vido vido-brighten");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-brighten\nvido\n", model.imageList());
  }

  @Test
  public void readCommandBlurTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("blur vido vido-blur");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-blur\nvido\n", model.imageList());
  }


  @Test
  public void readCommandSharpenTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("sharpen vido vido-sharp");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\nvido-sharp\n", model.imageList());
  }

  @Test
  public void readCommandSepiaTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("sepia vido vido-sepia");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-sepia\nvido\n", model.imageList());
  }

  @Test
  public void readCommandGrayscaleTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("grayscale vido vido-gray");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido-gray\nvido\n", model.imageList());
  }

  @Test
  public void readCommandOverrideTest() {
    IImageModel model = new ImageModelImp();
    model.apply(new LoadImage("res/vidoje.ppm", "vido"));

    Readable rd = new StringReader("brighten 10 vido vido");
    IController controller = new ControllerImp(model, rd);
    assertEquals("vido\n", model.imageList());
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test
  public void readCommandTwoCommands() {
    Readable rd = new StringReader("load res/vidoje.ppm vido vertical-flip vido vido-vert");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertEquals("vido-vert\nvido\n", model.imageList());
  }

  @Test
  public void readCommandThreeCommands() {
    Readable rd = new StringReader("load res/vidoje.ppm vido vertical-flip vido vido-vert "
            + "brighten 100 vido-vert vido-vert-bright");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertEquals("vido-vert\nvido\nvido-vert-bright\n", model.imageList());
  }

  // TESTS FOR INVALID COMMANDS ---------------------------------------------------------------
  @Test(expected = IllegalArgumentException.class)
  public void readCommandInvalidCommand2Args() {
    Readable rd = new StringReader("invalid 1 2 ");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }


  @Test(expected = IllegalArgumentException.class)
  public void readCommandInvalidCommandTypo() {
    Readable rd = new StringReader("brigten 10 vido new-vido");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }

  // TESTS FOR INSUFFICIENT ARGUMENTS -----------------------------------------------------------
  @Test(expected = IllegalArgumentException.class)
  public void readCommandInvalidCommandNoArgs() {
    Readable rd = new StringReader("invalid");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void readCommandNotEnoughArgs() {
    Readable rd = new StringReader("vertical-flip name");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void readCommandNotEnoughArgsBrighten() {
    Readable rd = new StringReader("brighten 10 name");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void readCommandExtraArgs() {
    Readable rd = new StringReader("load res/vidoje.ppm vido heyyy hi ho");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void readCommandExtraArgs2() {
    Readable rd = new StringReader("load res/vidoje.ppm vido hey hi");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
    assertEquals("vido\n", model.imageList());
  }

  // TESTS FOR PARSE-INT EXCEPTION -----------------------------------------------------------------
  @Test(expected = NumberFormatException.class)
  public void brightenParseInt() {
    Readable rd = new StringReader("brighten String 10 name");
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, rd);
    controller.readCommand();
  }

  // BAD CONSTRUCTOR TEST --------------------------------------------------------------------------
  @Test(expected = NullPointerException.class)
  public void nullModel() {
    Readable rd = new StringReader("brighten String 10 name");
    IController controller = new ControllerImp(null, rd);
  }

  @Test(expected = NullPointerException.class)
  public void nullRead() {
    IImageModel model = new ImageModelImp();
    IController controller = new ControllerImp(model, null);
  }

  @Test(expected = NullPointerException.class)
  public void nullBoth() {
    IController controller = new ControllerImp(null, null);
  }

  // MVC AND FEATURES TESTS -----------------------------------------------------------------------
  @Test
  public void testOpenFile() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.openFile();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("opened\ndisplayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testSaveFile() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.openFile(); // need to open a file to save it
    controller.saveFile();
    assertEquals("applied\nimage to get: current\napplied\n"
            + "image to get: current\n", modelApp.toString());
    assertEquals("opened\ndisplayed\nmade visible\nsaved\ndisplayed\n"
            + "made visible\n", viewApp.toString());
  }

  @Test
  public void testBlur() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.blurImage();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testSharpen() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.sharpenImage();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testHFlip() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.hFlip();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testVFlip() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.vFlip();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testSepia() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.sepiaImage();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testGray() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.grayImage();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("displayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testBrighten() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.brightenImage();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("pop up with type: bright\ndisplayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testGrayTypes() {
    Appendable viewApp = new StringBuilder();
    Appendable modelApp = new StringBuilder();
    IImageModel model = new MockModel(modelApp);
    Readable read = new InputStreamReader(System.in);
    IView view = new MockView(viewApp);
    Features controller = new ControllerImp(model, read, view);
    controller.grayTypes();
    assertEquals("applied\nimage to get: current\n", modelApp.toString());
    assertEquals("pop up with type: gray\ndisplayed\nmade visible\n", viewApp.toString());
  }

  @Test
  public void testError() {
    Appendable viewApp = new StringBuilder();
    IView view = new MockView(viewApp);
    view.showErrorMessage("this is an example error");
    assertEquals("error message thrown: this is an example error\n", viewApp.toString());
  }
}
