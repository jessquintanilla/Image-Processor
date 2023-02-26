package model;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.Grayscale;
import controller.commands.LoadImage;
import controller.commands.ImageCommand;
import controller.commands.SaveImage;
import model.imagefile.IPixel;
import model.imagefile.ImageFile;
import model.imagefile.Image;
import model.imagefile.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test that the commands work correctly on the model.
 */
public class ImageModelTest {

  IPixel pix00 = new Pixel(1, 2, 3, 255);
  IPixel pix01 = new Pixel(2, 1, 3, 255);
  IPixel pix10 = new Pixel(3, 3, 3, 255);
  IPixel pix11 = new Pixel(0, 2, 1, 255);
  IPixel[][] pixArrayInitial2x2 = {{pix00, pix01}, {pix10, pix11}};
  ImageFile ppimpInitial2x2 = new Image(pixArrayInitial2x2, 255);


  IPixel pix00v2 = new Pixel(50, 100, 150, 255);
  IPixel pix01v2 = new Pixel(75, 125, 175, 255);
  IPixel pix02v2 = new Pixel(110, 180, 220, 255);
  IPixel pix10v2 = new Pixel(10, 200, 150, 255);
  IPixel pix11v2 = new Pixel(0, 40, 255, 255);
  IPixel pix12v2 = new Pixel(5, 120, 230, 255);
  IPixel[][] pixArrayInitial2x3 = {{pix00v2, pix01v2, pix02v2}, {pix10v2, pix11v2, pix12v2}};
  ImageFile ppimpInitial2x3 = new Image(pixArrayInitial2x3, 255);
  IImageModel model = new ImageModelImp();
  ImageCommand load1 = new LoadImage("res/pix.ppm", "image1");

  Map<String, ImageFile> map = new HashMap<>();

  String brightenExpected = "P3\n"
          + "3\n"
          + "3\n"
          + "255\n"
          + "10\n"
          + "10\n"
          + "10\n"
          + "138\n"
          + "10\n"
          + "10\n"
          + "255\n"
          + "10\n"
          + "138\n"
          + "138\n"
          + "138\n"
          + "138\n"
          + "10\n"
          + "138\n"
          + "10\n"
          + "10\n"
          + "255\n"
          + "138\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "10\n"
          + "40\n"
          + "138\n"
          + "10\n"
          + "10\n"
          + "255\n";

  String brightenExpected2 = "P3\n"
          + "3\n"
          + "3\n"
          + "255\n"
          + "130\n"
          + "130\n"
          + "130\n"
          + "255\n"
          + "130\n"
          + "130\n"
          + "255\n"
          + "130\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "130\n"
          + "255\n"
          + "130\n"
          + "130\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "130\n"
          + "160\n"
          + "255\n"
          + "130\n"
          + "130\n"
          + "255\n";

  String brightenExpected3 = "P3\n"
          + "3\n"
          + "3\n"
          + "255\n"
          + "0\n"
          + "0\n"
          + "0\n"
          + "118\n"
          + "0\n"
          + "0\n"
          + "245\n"
          + "0\n"
          + "118\n"
          + "118\n"
          + "118\n"
          + "118\n"
          + "0\n"
          + "118\n"
          + "0\n"
          + "0\n"
          + "245\n"
          + "118\n"
          + "245\n"
          + "245\n"
          + "245\n"
          + "0\n"
          + "20\n"
          + "118\n"
          + "0\n"
          + "0\n"
          + "245\n";

  String horizontalFlip1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "2\n"
          + "1\n"
          + "3\n"
          + "1\n"
          + "2\n"
          + "3\n"
          + "0\n"
          + "2\n"
          + "1\n"
          + "3\n"
          + "3\n"
          + "3\n";

  String horizontalFlip2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "110\n"
          + "180\n"
          + "220\n"
          + "75\n"
          + "125\n"
          + "175\n"
          + "50\n"
          + "100\n"
          + "150\n"
          + "5\n"
          + "120\n"
          + "230\n"
          + "0\n"
          + "40\n"
          + "255\n"
          + "10\n"
          + "200\n"
          + "150\n";

  String verticalFlip1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "0\n"
          + "2\n"
          + "1\n"
          + "1\n"
          + "2\n"
          + "3\n"
          + "2\n"
          + "1\n"
          + "3\n";

  String verticalFlip2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "10\n"
          + "200\n"
          + "150\n"
          + "0\n"
          + "40\n"
          + "255\n"
          + "5\n"
          + "120\n"
          + "230\n"
          + "50\n"
          + "100\n"
          + "150\n"
          + "75\n"
          + "125\n"
          + "175\n"
          + "110\n"
          + "180\n"
          + "220\n";

  String redGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "1\n"
          + "1\n"
          + "1\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "0\n"
          + "0\n"
          + "0\n";

  String redGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "50\n"
          + "50\n"
          + "50\n"
          + "75\n"
          + "75\n"
          + "75\n"
          + "110\n"
          + "110\n"
          + "110\n"
          + "10\n"
          + "10\n"
          + "10\n"
          + "0\n"
          + "0\n"
          + "0\n"
          + "5\n"
          + "5\n"
          + "5\n";

  String greenGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "1\n"
          + "1\n"
          + "1\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "2\n"
          + "2\n"
          + "2\n";

  String greenGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "100\n"
          + "100\n"
          + "100\n"
          + "125\n"
          + "125\n"
          + "125\n"
          + "180\n"
          + "180\n"
          + "180\n"
          + "200\n"
          + "200\n"
          + "200\n"
          + "40\n"
          + "40\n"
          + "40\n"
          + "120\n"
          + "120\n"
          + "120\n";

  String blueGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "1\n"
          + "1\n"
          + "1\n";

  String blueGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "150\n"
          + "150\n"
          + "150\n"
          + "175\n"
          + "175\n"
          + "175\n"
          + "220\n"
          + "220\n"
          + "220\n"
          + "150\n"
          + "150\n"
          + "150\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "230\n"
          + "230\n"
          + "230\n";

  String valueGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "2\n"
          + "2\n"
          + "2\n";

  String valueGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "150\n"
          + "150\n"
          + "150\n"
          + "175\n"
          + "175\n"
          + "175\n"
          + "220\n"
          + "220\n"
          + "220\n"
          + "200\n"
          + "200\n"
          + "200\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "230\n"
          + "230\n"
          + "230\n";

  String lumaGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "1\n"
          + "1\n"
          + "1\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "2\n"
          + "2\n"
          + "2\n";

  String lumaGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "93\n"
          + "93\n"
          + "93\n"
          + "118\n"
          + "118\n"
          + "118\n"
          + "168\n"
          + "168\n"
          + "168\n"
          + "156\n"
          + "156\n"
          + "156\n"
          + "47\n"
          + "47\n"
          + "47\n"
          + "103\n"
          + "103\n"
          + "103\n";

  String intensityGrayscale1 = "P3\n"
          + "2\n"
          + "2\n"
          + "255\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "2\n"
          + "3\n"
          + "3\n"
          + "3\n"
          + "1\n"
          + "1\n"
          + "1\n";

  String intensityGrayscale2 = "P3\n"
          + "3\n"
          + "2\n"
          + "255\n"
          + "100\n"
          + "100\n"
          + "100\n"
          + "125\n"
          + "125\n"
          + "125\n"
          + "170\n"
          + "170\n"
          + "170\n"
          + "120\n"
          + "120\n"
          + "120\n"
          + "98\n"
          + "98\n"
          + "98\n"
          + "118\n"
          + "118\n"
          + "118\n";

  String image1ToString = "P3\n"
          + "3\n"
          + "3\n"
          + "255\n"
          + "0\n"
          + "0\n"
          + "0\n"
          + "128\n"
          + "0\n"
          + "0\n"
          + "255\n"
          + "0\n"
          + "128\n"
          + "128\n"
          + "128\n"
          + "128\n"
          + "0\n"
          + "128\n"
          + "0\n"
          + "0\n"
          + "255\n"
          + "128\n"
          + "255\n"
          + "255\n"
          + "255\n"
          + "0\n"
          + "30\n"
          + "128\n"
          + "0\n"
          + "0\n"
          + "255\n";

  @Test(expected = NullPointerException.class)
  public void testConstructor() {
    IImageModel modelNull = new ImageModelImp(null);
  }

  @Test
  public void testLoadCommand() {
    assertEquals("", model.imageList());
    model.apply(load1);
    assertEquals("image1\n", model.imageList());
    assertEquals(image1ToString, model.getImage("image1").imageToString());
  }

  @Test
  public void testGetImage() {
    map.put("image2", ppimpInitial2x2);
    map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);
    assertEquals(ppimpInitial2x2.imageToString(), model1.getImage("image2").imageToString());
    assertEquals(ppimpInitial2x3.imageToString(), model1.getImage("image3").imageToString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageException() {
    map.put("image2", ppimpInitial2x2);
    map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);
    model1.getImage("image1");
  }

  @Test
  public void testBrightenCommand() {
    model.apply(load1);
    //brighten the original by 10
    ImageCommand brighten = new Brighten(10, "image1",  "image1brightened");
    model.apply(brighten);
    assertEquals("image1brightened\nimage1\n", model.imageList());
    assertEquals(brightenExpected, model.getImage("image1brightened").imageToString());
    // brighten the brightened picture by 120, some values reach the max
    ImageCommand brighten2 = new Brighten(120, "image1brightened", "image1brightenedAgain");
    model.apply(brighten2);
    assertEquals(brightenExpected2, model.getImage("image1brightenedAgain").imageToString());
    //brighten the original by -10, some values reach the min (0)
    ImageCommand brighten3 = new Brighten(-10, "image1",  "image1dulled");
    model.apply(brighten3);
    assertEquals(brightenExpected3, model.getImage("image1dulled").imageToString());
  }

  @Test
  public void testFlipHorizontal() {
    map.put("image2", ppimpInitial2x2);
    map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);
    ImageCommand flip1 = new Flip("image2", "image2Flipped", "horizontal");
    ImageCommand flip2 = new Flip("image3", "image3Flipped", "horizontal");
    model1.apply(flip1);
    model1.apply(flip2);
    assertEquals(horizontalFlip1, model1.getImage("image2Flipped").imageToString());
    assertEquals(horizontalFlip2, model1.getImage("image3Flipped").imageToString());
  }

  @Test
  public void testFlipVertical() {
    map.put("image2", ppimpInitial2x2);
    map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);
    ImageCommand flip1 = new Flip("image2", "image2Flipped", "vertical");
    ImageCommand flip2 = new Flip("image3", "image3Flipped", "vertical");
    model1.apply(flip1);
    model1.apply(flip2);
    assertEquals(verticalFlip1, model1.getImage("image2Flipped").imageToString());
    assertEquals(verticalFlip2, model1.getImage("image3Flipped").imageToString());
  }

  @Test
  public void testGrayscale() {
    map.put("image2", ppimpInitial2x2);
    map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);

    // test red grayscale
    ImageCommand red1 = new Grayscale("image2", "image2Red", "red");
    ImageCommand red2 = new Grayscale("image3", "image3Red", "red");
    model1.apply(red1);
    model1.apply(red2);
    assertEquals(redGrayscale1, model1.getImage("image2Red").imageToString());
    assertEquals(redGrayscale2, model1.getImage("image3Red").imageToString());

    // test green grayscale
    ImageCommand green1 = new Grayscale("image2", "image2Green", "green");
    ImageCommand green2 = new Grayscale("image3", "image3Green", "green");
    model1.apply(green1);
    model1.apply(green2);
    assertEquals(greenGrayscale1, model1.getImage("image2Green").imageToString());
    assertEquals(greenGrayscale2, model1.getImage("image3Green").imageToString());

    // test blue grayscale
    ImageCommand blue1 = new Grayscale("image2", "image2blue", "blue");
    ImageCommand blue2 = new Grayscale("image3", "image3blue", "blue");
    model1.apply(blue1);
    model1.apply(blue2);
    assertEquals(blueGrayscale1, model1.getImage("image2blue").imageToString());
    assertEquals(blueGrayscale2, model1.getImage("image3blue").imageToString());

    // test value grayscale
    ImageCommand value1 = new Grayscale("image2", "image2value", "value");
    ImageCommand value2 = new Grayscale("image3", "image3value", "value");
    model1.apply(value1);
    model1.apply(value2);
    assertEquals(valueGrayscale1, model1.getImage("image2value").imageToString());
    assertEquals(valueGrayscale2, model1.getImage("image3value").imageToString());

    // test luma grayscale
    ImageCommand luma1 = new Grayscale("image2", "image2luma", "luma");
    ImageCommand luma2 = new Grayscale("image3", "image3luma", "luma");
    model1.apply(luma1);
    model1.apply(luma2);
    assertEquals(lumaGrayscale1, model1.getImage("image2luma").imageToString());
    assertEquals(lumaGrayscale2, model1.getImage("image3luma").imageToString());

    // test intensity grayscale
    ImageCommand intensity1 = new Grayscale("image2", "image2intensity", "intensity");
    ImageCommand intensity2 = new Grayscale("image3", "image3intensity", "intensity");
    model1.apply(intensity1);
    model1.apply(intensity2);
    assertEquals(intensityGrayscale1, model1.getImage("image2intensity").imageToString());
    assertEquals(intensityGrayscale2, model1.getImage("image3intensity").imageToString());
  }


  @Test
  public void testSaveCommand() {
    this.map.put("image2", ppimpInitial2x2);
    this.map.put("image3", ppimpInitial2x3);
    IImageModel model1 = new ImageModelImp(this.map);
    ImageCommand saveImage2 = new SaveImage("res/image2.ppm", "image2");
    model1.apply(saveImage2);
    ImageCommand saveImage3 = new SaveImage("res/image3.ppm", "image3");
    model1.apply(saveImage3);
    assertTrue(new File("res/image2.ppm").exists());
    assertTrue(new File("res/image3.ppm").exists());
  }
}