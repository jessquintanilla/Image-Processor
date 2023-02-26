import controller.ControllerImp;
import controller.IController;
import model.IImageModel;
import model.ImageModelImp;
import view.IView;
import view.ImageGraphicsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * This class contains utility methods to read an image from file and perform
 * image manipulations on it.
 */
public class ImageUtil {

  /**
   * Runs the image manipulation program.
   *
   * @param args the user inputs
   */
  public static void main(String[] args) {
    IImageModel model = new ImageModelImp();
    InputStreamReader read = new InputStreamReader(System.in);
    IView view = null;

    if (args.length >= 1) {
      if (args[0].equals("-file")) {
        try {
          File file = new File(args[1]);
          if (file.length() != 0) {
            read = new FileReader(args[1]);
          }
        } catch (FileNotFoundException e) {
          System.out.println("File not found.");
        }
      } else if (args[0].equals("-text")) {
        read = new InputStreamReader(System.in);
      }
    } else {
      read = new InputStreamReader(System.in);
      view = new ImageGraphicsView();
    }

    if (view != null) {
      IController controller = new ControllerImp(model, read, view);
      controller.setView(view);
    } else {
      IController controller = new ControllerImp(model, read);
      controller.readCommand();
    }
  }
}

