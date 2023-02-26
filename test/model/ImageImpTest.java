package model;

import controller.commands.*;
import org.junit.Test;

import model.imagefile.IPixel;
import model.imagefile.ImageFile;
import model.imagefile.Image;
import model.imagefile.Pixel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Image model. Tests mutating Image Files
 */
public class ImageImpTest {


  IPixel pix00 = new Pixel(1, 2, 3, 255);
  IPixel pix01 = new Pixel(2, 1, 3, 255);
  IPixel pix10 = new Pixel(3, 3, 3, 255);
  IPixel pix11 = new Pixel(0, 2, 1, 255);

  IPixel pix02 = new Pixel(5, 6, 7, 255);
  IPixel pix12 = new Pixel(10, 15, 20, 255);
  IPixel[][] pixArrayInitial2x2 = {{pix00, pix01}, {pix10, pix11}};
  ImageFile ppimpInitial2x2 = new Image(pixArrayInitial2x2, 255);

  IPixel[][] pixArrayInitial3x2 = {{pix00, pix01, pix02}, {pix10, pix11, pix12}};
  ImageFile ppimpInitial3x2 = new Image(pixArrayInitial3x2, 255);

  IPixel pix0 = new Pixel(0, 0, 0, 255);
  IPixel pix1 = new Pixel(1, 1, 1, 255);
  IPixel pix2 = new Pixel(2, 2, 2, 255);
  IPixel pix3 = new Pixel(3, 3, 3, 255);

  IPixel[][] pixArrayRed = {{pix1, pix2}, {pix3, pix0}};
  ImageFile ppimpRed = new Image(pixArrayRed, 255);

  IPixel[][] pixArrayGreen = {{pix2, pix1}, {pix3, pix2}};
  ImageFile ppimpGreen = new Image(pixArrayGreen, 255);

  IPixel[][] pixArrayBlue = {{pix3, pix3}, {pix3, pix1}};
  ImageFile ppimpBlue = new Image(pixArrayBlue, 255);

  IPixel[][] pixArrayValue = {{pix3, pix3}, {pix3, pix2}};
  ImageFile ppimpValue = new Image(pixArrayValue, 255);

  IPixel[][] pixArrayLuma = {{pix2, pix1}, {pix3, pix2}};
  ImageFile ppimpLuma = new Image(pixArrayLuma, 255);

  IPixel[][] pixArrayIntensity = {{pix2, pix2}, {pix3, pix1}};
  ImageFile ppimpIntensity = new Image(pixArrayIntensity, 255);

  IPixel[][] pixArrayHorizontalFlip = {{pix01, pix00}, {pix11, pix10}};
  ImageFile ppimpHorizontalFlip = new Image(pixArrayHorizontalFlip, 255);
  IPixel[][] pixArrayVerticalFlip = {{pix10, pix11}, {pix00, pix01}};
  ImageFile ppimpVertical = new Image(pixArrayVerticalFlip, 255);

  IPixel[][] pixArrayHorizontalFlip3x2 = {{pix02, pix01, pix00}, {pix12, pix11, pix10}};
  ImageFile ppimpHorizontalFlip3x2 = new Image(pixArrayHorizontalFlip3x2, 255);

  IPixel[][] pixArrayVerticalFlip3x2 = {{pix10, pix11, pix12}, {pix00, pix01, pix02}};
  ImageFile ppimpVertical3x2 = new Image(pixArrayVerticalFlip3x2, 255);

  IPixel[][] pixArraySepia = {{new Pixel(1, 1, 0, 255),
          new Pixel(2, 2, 1, 255)}, {new Pixel(4, 3, 3, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpSepia = new Image(pixArraySepia, 255);

  IPixel[][] pixArrayGray = {{new Pixel(1, 1, 1, 255),
          new Pixel(2, 2, 2, 255)}, {new Pixel(3, 3, 3, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpGray = new Image(pixArrayGray, 255);

  IPixel[][] pixArrayBlur = {{new Pixel(0, 0, 0, 255),
          new Pixel(0, 0, 0, 255)}, {new Pixel(0, 0, 0, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpBlur = new Image(pixArrayBlur, 255);

  IPixel[][] pixArrayOneFilter = {{new Pixel(6, 6, 6, 255),
          new Pixel(6, 6, 6, 255)}, {new Pixel(6, 6, 6, 255),
          new Pixel(6, 6, 6, 255)}};
  ImageFile ppimpOne = new Image(pixArrayOneFilter, 255);

  IPixel[][] pixArraySharpen = {{new Pixel(1, 1, 1, 255),
          new Pixel(2, 2, 2, 255)}, {new Pixel(3, 3, 3, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpSharpen = new Image(pixArraySharpen, 255);

  IPixel[][] pixArrayEqualTrans = {{new Pixel(0, 1, 0, 255),
          new Pixel(0, 2, 0, 255)}, {new Pixel(0, 3, 0, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpEqualsTrans = new Image(pixArrayEqualTrans, 255);

  IPixel[][] pixZeroTrans = {{new Pixel(0, 0, 0, 255),
          new Pixel(0, 0, 0, 255)}, {new Pixel(0, 0, 0, 255),
          new Pixel(0, 0, 0, 255)}};
  ImageFile ppimpZeroTrans = new Image(pixZeroTrans, 255);

  IPixel[][] pixFilterInit = {{pix1, pix2}, {pix3, pix0}};
  ImageFile ppimpFilterInit = new Image(pixFilterInit, 255);

  double[][] sepia = new double[][]{
          new double[]{0.393, 0.769, 0.189},
          new double[]{0.349, 0.686, 0.168},
          new double[]{0.272, 0.534, 0.131}};

  double[][] gray = new double[][]{
          new double[]{0.2126, 0.7152, 0.0722},
          new double[]{0.2126, 0.7152, 0.0722},
          new double[]{0.2126, 0.7152, 0.0722}};

  double[][] blur = new double[][]{
          new double[]{0.0625, 0.125, 0.0625},
          new double[]{0.125, 0.25, 0.125},
          new double[]{0.0625, 0.125, 0.0625}};

  double[][] sharpen = new double[][]{
          new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
          new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
          new double[]{-0.125, 0.25, 1, 0.25, -0.125},
          new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
          new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}};

  double[][] one = new double[][]{
          new double[]{1.0, 1.0, 1.0},
          new double[]{1.0, 1.0, 1.0},
          new double[]{1.0, 1.0, 1.0}};

  double[][] equals = new double[][]{
          new double[]{0.0, 0.0, 0.0},
          new double[]{0.0, 1.0, 0.0},
          new double[]{0.0, 0.0, 0.0}};

  double[][] zero = new double[][]{
          new double[]{0.0, 0.0, 0.0},
          new double[]{0.0, 0.0, 0.0},
          new double[]{0.0, 0.0, 0.0}};

  Map<String, ImageFile> images = new HashMap<>();


  @Test(expected = NullPointerException.class)
  public void testConstructorNullPix() {
    ImageFile nullPix = new Image(null, 255);
  }

  @Test
  public void grayscaleTest() {
    images.put("ppimpInitial2x2", ppimpInitial2x2);
    Grayscale grayR = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-r", "red");
    grayR.command(images);
    assertEquals(ppimpRed.imageToString(), images.get("ppimpInitial2x2-r")
            .imageToString());
    Grayscale grayG = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-g", "green");
    grayG.command(images);
    assertEquals(ppimpGreen.imageToString(), images.get("ppimpInitial2x2-g")
            .imageToString());
    Grayscale grayB = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-b", "blue");
    grayB.command(images);
    assertEquals(ppimpBlue.imageToString(), images.get("ppimpInitial2x2-b")
            .imageToString());
    Grayscale grayV = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-v", "value");
    grayV.command(images);
    assertEquals(ppimpValue.imageToString(), images.get("ppimpInitial2x2-v")
            .imageToString());
    Grayscale grayL = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-l", "luma");
    grayL.command(images);
    assertEquals(ppimpLuma.imageToString(), images.get("ppimpInitial2x2-l")
            .imageToString());
    Grayscale grayI = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2-i", "intensity");
    grayI.command(images);
    assertEquals(ppimpIntensity.imageToString(), images.get("ppimpInitial2x2-i")
            .imageToString());
    images.clear();
  }

  @Test
  public void testGrayscaleException() {
    try {
      images.put("ppimpInitial2x2", ppimpInitial2x2);
      Grayscale gray = new Grayscale("ppimpInitial2x2", "ppimpInitial2x2", "hello");
      gray.command(images);
    } catch (IllegalArgumentException i) {
      assertEquals("Not a permitted grayscale type.", i.getMessage());
    }
    images.clear();
  }

  @Test
  public void horizontalFlipTest() {
    images.put("ppimpInitial2x2", ppimpInitial2x2);
    ImageCommand flip = new Flip("ppimpInitial2x2", "ppimpInitial2x2", "horizontal");
    flip.command(images);
    assertEquals(ppimpHorizontalFlip.imageToString(), images.get("ppimpInitial2x2").imageToString());
    images.put("ppimpInitial3x2", ppimpInitial3x2);
    ImageCommand flip2 = new Flip("ppimpInitial3x2", "ppimpInitial3x2", "horizontal");
    flip2.command(images);
    assertEquals(ppimpHorizontalFlip3x2.imageToString(), images.get("ppimpInitial3x2").imageToString());
    images.clear();
  }

  @Test
  public void verticalFlipTest() {
    images.put("ppimpInitial2x2", ppimpInitial2x2);
    ImageCommand flip = new Flip("ppimpInitial2x2", "ppimpInitial2x2", "vertical");
    flip.command(images);
    assertEquals(ppimpVertical.imageToString(), images.get("ppimpInitial2x2").imageToString());
    images.put("ppimpInitial3x2", ppimpInitial3x2);
    ImageCommand flip2 = new Flip("ppimpInitial3x2", "ppimpInitial3x2", "vertical");
    flip2.command(images);
    assertEquals(ppimpVertical3x2.imageToString(), images.get("ppimpInitial3x2").imageToString());
    images.clear();
  }


  @Test
  public void brightenTest() {
    //normal case
    Pixel pix00_inc3 = new Pixel(4, 5, 6, 255);
    Pixel pix01_inc3 = new Pixel(5, 4, 6, 255);
    Pixel pix10_inc3 = new Pixel(6, 6, 6, 255);
    Pixel pix11_inc3 = new Pixel(3, 5, 4, 255);
    Pixel[][] pixArray2x2_inc3 = {{pix00_inc3, pix01_inc3}, {pix10_inc3, pix11_inc3}};
    ImageFile ppimp2x2_inc3 = new Image(pixArray2x2_inc3, 255);


    // negative increment - above zero and below zero
    Pixel pix00_dec1 = new Pixel(0, 1, 2, 255);
    Pixel pix01_dec1 = new Pixel(1, 0, 2, 255);
    Pixel pix10_dec1 = new Pixel(2, 2, 2, 255);
    Pixel pix11_dec1 = new Pixel(0, 1, 0, 255);
    Pixel[][] pixArray2x2_dec3 = {{pix00_dec1, pix01_dec1}, {pix10_dec1, pix11_dec1}};
    ImageFile ppimp2x2_dec3 = new Image(pixArray2x2_dec3, 255);


    // positive increment - above 255 and below 255
    Pixel pix00_inc253 = new Pixel(254, 255, 255, 255);
    Pixel pix01_inc253 = new Pixel(255, 254, 255, 255);
    Pixel pix10_inc253 = new Pixel(255, 255, 255, 255);
    Pixel pix11_inc253 = new Pixel(253, 255, 254, 255);
    Pixel[][] pixArray2x2_inc253 = {{pix00_inc253, pix01_inc253}, {pix10_inc253, pix11_inc253}};
    ImageFile ppimp2x2_inc253 = new Image(pixArray2x2_inc253, 255);

    images.put("ppimpInitial2x2", ppimpInitial2x2);
    ImageCommand bright = new Brighten(3, "ppimpInitial2x2", "ppimpInitial2x2-b");
    bright.command(images);
    assertEquals(ppimp2x2_inc3.imageToString(), images.get("ppimpInitial2x2-b").imageToString());
    ImageCommand dark = new Brighten(-1, "ppimpInitial2x2", "ppimpInitial2x2-d");
    dark.command(images);
    assertEquals(ppimp2x2_dec3.imageToString(), images.get("ppimpInitial2x2-d").imageToString());
    ImageCommand bright2 = new Brighten(253, "ppimpInitial2x2", "ppimpInitial2x2-b2");
    bright2.command(images);
    assertEquals(ppimp2x2_inc253.imageToString(), images.get("ppimpInitial2x2-b2").imageToString());
  }


  @Test
  public void getPixTest() {
    assertEquals(pixArrayRed, ppimpRed.getPix());
    assertEquals(pixArrayBlue, ppimpBlue.getPix());
    assertEquals(pixArrayGreen, ppimpGreen.getPix());
  }

  @Test
  public void testFilterColorTrans() {
    images.put("ppimpFilterInit", ppimpFilterInit);
    ImageCommand sep = new Sepia("ppimpFilterInit", "ppimpFilterInit-s");
    sep.command(images);
    assertEquals(ppimpSepia.imageToString(), images.get("ppimpFilterInit-s").imageToString());

    ImageCommand gray = new GrayTransformation("ppimpFilterInit", "ppimpFilterInit-g");
    gray.command(images);
    assertEquals(ppimpGray.imageToString(), images.get("ppimpFilterInit-g").imageToString());

    ImageCommand blur = new Blur("ppimpFilterInit", "ppimpFilterInit-b");
    blur.command(images);
    assertEquals(ppimpBlur.imageToString(), images.get("ppimpFilterInit-b").imageToString());

    ImageCommand sharp = new Sharpen("ppimpFilterInit", "ppimpFilterInit-s");
    sharp.command(images);
    assertEquals(ppimpSharpen.imageToString(),
            images.get("ppimpFilterInit-s").imageToString());
    images.clear();
  }

  @Test
  public void testAbstractFilter() {
    AbstractFilterCommand absFilter = new AbstractFilterCommand("x", "x") {
      @Override
      protected double[][] makeArray() {
        return null;
      }
    };
    assertEquals(ppimpZeroTrans.imageToString(), absFilter.applyFilter(zero, ppimpFilterInit).imageToString());
    assertEquals(ppimpFilterInit.imageToString(), absFilter.applyFilter(equals, ppimpFilterInit).imageToString());
    assertEquals(ppimpOne.imageToString(), absFilter.applyFilter(one, ppimpFilterInit).imageToString());
    assertEquals(ppimpBlur.imageToString(), absFilter.applyFilter(blur, ppimpFilterInit).imageToString());
    assertEquals(ppimpSharpen.imageToString(), absFilter.applyFilter(sharpen, ppimpFilterInit).imageToString());
  }

  @Test
  public void testAbstractColorTransformation() {
    AbstractTransformationCommand absTrans = new AbstractTransformationCommand("n", "n") {
      @Override
      protected double[][] makeArray() {
        return null;
      }
    };
    assertEquals(ppimpZeroTrans.imageToString(), absTrans.applyFilter(zero, ppimpFilterInit).imageToString());
    assertEquals(ppimpEqualsTrans.imageToString(), absTrans.applyFilter(equals, ppimpFilterInit).imageToString());
    assertEquals(ppimpSepia.imageToString(), absTrans.applyFilter(sepia, ppimpFilterInit).imageToString());
    assertEquals(ppimpGray.imageToString(), absTrans.applyFilter(gray, ppimpFilterInit).imageToString());
  }

  @Test
  public void testImageToString() {
    assertEquals("P3\n2\n2\n255\n1\n2\n3\n2\n1\n3\n3\n3\n3\n0\n2\n1\n",
            ppimpInitial2x2.imageToString());
  }
}
