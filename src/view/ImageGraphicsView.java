package view;

import controller.Features;
import model.imagefile.IPixel;
import model.imagefile.ImageFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class handles the GUI view for the image processor. It creates the panels that will be
 * displayed to the user and draws to them.
 */
public class ImageGraphicsView extends JFrame implements IView {
  private final JPanel dialogBoxesPanel;
  private final JButton fileOpenButton;

  private final JPanel imagePanel;

  // Save controls
  private final JButton fileSaveButton;

  // Blur controls
  private final JButton fileBlurButton;

  // Sharpen controls
  private final JButton fileSharpenButton;

  // horizontal flip
  private final JButton fileHFlipButton;

  // vertical flip
  private final JButton fileVFlipButton;

  // sepia filter
  private final JButton sepiaButton;

  // grayscale filter
  private final JButton grayButton;

  // brighten
  private final JButton brightenButton;

  // grayscale types
  private final JButton grayTypesButton;

  // histogram panel
  private JPanel histogram;

  // info panel
  private final JPanel infoPanel;

  // downscale
  private final JButton downscaleButton;

  // mosaic
  private final JButton mosaicButton;


  /**
   * Constructor for the Image Graphics view class. It does not take any parameters, and initializes
   * each of the view components, including a main panel, a dialog box for the buttons, and an
   * image panel that displays the current image being worked on and a histogram of the pixel
   * values.
   */
  public ImageGraphicsView() {
    super();
    // local variables
    final JPanel mainPanel;
    final JScrollPane mainScrollPane;
    final JPanel fileSavePanel;
    final JPanel fileBlurPanel;
    final JPanel fileSharpenPanel;
    final JPanel fileHFlipPanel;
    final JPanel fileVFlipPanel;
    final JPanel sepiaPanel;
    final JPanel grayPanel;
    final JPanel brightenPanel;
    final JPanel grayTypesPanel;
    final JPanel downscalePanel;
    final JPanel mosaicPanel;

    this.setTitle("Image Manipulation");
    setSize(2000, 1100);

    mainPanel = new JPanel();
    // for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    // scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // dialog boxes --------------------
    dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.X_AXIS));
    dialogBoxesPanel.setSize(1500, 1);
    dialogBoxesPanel.setMaximumSize(new Dimension(1500, 500));
    mainPanel.add(dialogBoxesPanel);

    infoPanel = new JPanel();
    infoPanel.setSize(1500, 1);
    infoPanel.setMaximumSize(new Dimension(1800, 600));
    infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));
    infoPanel.setVisible(true);
    mainPanel.add(infoPanel);

    // file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenButton = this.makeButton(fileOpenPanel, "Open file", "Open");

    // show an image with a scrollbar ----------------
    imagePanel = new JPanel();
    // a border around the panel with a caption
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
    JPanel image = new JPanel();
    image.setBorder(BorderFactory.createTitledBorder("Current image"));
    imagePanel.add(image);
    mainPanel.add(imagePanel);

    // save an image ------------------
    fileSavePanel = new JPanel();
    fileSaveButton = this.makeButton(fileSavePanel, "Save file",
            "Save");

    // blur an image ------------------
    fileBlurPanel = new JPanel();
    fileBlurButton = this.makeButton(fileBlurPanel, "Blur image",
            "Blur");

    // sharpen an image ------------------
    fileSharpenPanel = new JPanel();
    fileSharpenButton = this.makeButton(fileSharpenPanel,
            "Sharpen image", "Sharpen");

    // horizontally flip an image ------------------
    fileHFlipPanel = new JPanel();
    fileHFlipButton = this.makeButton(fileHFlipPanel,
            "Horizontally flip", "Horizontal Flip");

    // vertically flip an image ------------------
    fileVFlipPanel = new JPanel();
    fileVFlipButton = this.makeButton(fileVFlipPanel,
            "Vertically flip", "Vertical Flip");

    // sepia filter an image ------------------
    sepiaPanel = new JPanel();
    sepiaButton = this.makeButton(sepiaPanel, "Sepia image",
            "Sepia Filter");

    // grayscale filter an image ------------------
    grayPanel = new JPanel();
    grayButton = this.makeButton(grayPanel, "Grayscale image",
            "Grayscale Filter");

    // brighten an image ------------------
    brightenPanel = new JPanel();
    brightenButton = this.makeButton(brightenPanel,
            "Brighten image", "Brighten");

    // grayscale ------------------
    grayTypesPanel = new JPanel();
    grayTypesButton = this.makeButton(grayTypesPanel,
            "Gray Type", "Grayscale Options");

    // mosaic ------------------
    mosaicPanel = new JPanel();
    mosaicButton = this.makeButton(mosaicPanel,
            "Mosaic image", "Mosaic");

    // downscale ------------------
    downscalePanel = new JPanel();
    downscaleButton = this.makeButton(downscalePanel, "Downscale Image", "Downscale");

    // histogram ------------------
    histogram = new JPanel();
    histogram.setBorder(BorderFactory.createTitledBorder("Histogram"));
    imagePanel.add(histogram);


  }

  /**
   * Creates a button with the given specifications and adds it to a panel.
   *
   * @param panel   the panel to add the button to
   * @param command the action command text
   * @param text    the text of the button
   * @return the JButton being made
   */
  private JButton makeButton(JPanel panel, String command, String text) {
    panel.setLayout(new FlowLayout());
    this.dialogBoxesPanel.add(panel);
    JButton button = new JButton(text);
    button.setActionCommand(command);
    panel.add(button);
    return button;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Method that handles how the view displays opening a file on the image processor. The method
   * displays the opened file onto the screen.
   *
   * @return a string of the path of the image.
   */
  public String openFile() {
    JFileChooser fChooser = new JFileChooser(".");

    // file filters
    FileNameExtensionFilter filterPPM = new FileNameExtensionFilter(
            "PPM Images", "ppm");
    FileNameExtensionFilter filterBMP = new FileNameExtensionFilter(
            "BMP Images", "bmp");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");
    fChooser.setFileFilter(filter);
    fChooser.setFileFilter(filterPPM);
    fChooser.setFileFilter(filterBMP);

    String images = null;
    int retvalue = fChooser.showOpenDialog(ImageGraphicsView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      images = f.getAbsolutePath();
    }
    return images;
  }

  /**
   * Method that handles how the view saves a file on the image processor. The method allows the
   * user to interactively choose the image they want to save.
   *
   * @return a string of the path of the image.
   */
  public String saveFile() {
    final JFileChooser fChooser = new JFileChooser(".");
    int retvalue = fChooser.showSaveDialog(ImageGraphicsView.this);
    String image = null;
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      image = f.getAbsolutePath();
    }
    return image;
  }

  /**
   * A method to display an image on the screen. The method displays the image, the histogram, and
   * the information panel.
   *
   * @param image the image to be displayed.
   */
  public void displayImage(ImageFile image) {
    IPixel[][] pix = image.getPix();
    BufferedImage newImage = image.createImage();

    // display the current image
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(newImage));
    imageScrollPane.setBorder(BorderFactory.createTitledBorder("Current image"));
    imageScrollPane.setPreferredSize(new Dimension(imagePanel.getWidth() / 2,
            imagePanel.getHeight()));

    imagePanel.removeAll();
    imagePanel.add(imageScrollPane);

    // display the histogram
    Histogram h = new Histogram(pix);
    histogram = h;
    histogram.setPreferredSize(new Dimension(imagePanel.getWidth() / 2, imagePanel.getHeight()));
    imagePanel.add(histogram);

    // display the histogram information
    infoPanel.removeAll();
    JLabel jlabel = h.histogramInfo();
    infoPanel.add(jlabel);
  }

  /**
   * Method to create pop-up messages based on the type of error that occurred. This is used to
   * display the error to the user if something goes wrong, so they can acknowledge the error and
   * continue using the image processor.
   *
   * @param type the type of popup.
   * @return a string of the error.
   */
  public String popUps(String type) {
    String result = null;
    if ("gray".equals(type)) { // grayscale pop up
      String[] options = {"Red", "Blue", "Green", "Luma", "Value", "Intensity"};
      int retvalue = JOptionPane.showOptionDialog(ImageGraphicsView.this,
              "Please choose grayscale type",
              "Options", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
              options, options[4]);
      if (retvalue == -1) {
        return null;
      }
      result = options[retvalue];
    } else if ("bright".equals(type)) { // brighten pop up
      result = JOptionPane.showInputDialog("Enter brighten value.\nPositive to brighten. "
              + "Negative to darken.");
    } else if ("downscaleWidth".equals(type)) { // downscale pop up
      result = JOptionPane.showInputDialog("Enter new width. ");
    } else if ("downscaleHeight".equals(type)) { // downscale pop up
      result = JOptionPane.showInputDialog("Enter new height. ");
    } else if ("mosaic".equals(type)) { // mosaic pop up
      result = JOptionPane.showInputDialog("Enter a mosaic scale. ");
    }
    return result;
  }

  @Override
  public void addFeatures(Features features) {
    this.fileOpenButton.addActionListener(evt -> features.openFile());
    this.fileSaveButton.addActionListener(evt -> features.saveFile());
    this.fileBlurButton.addActionListener(evt -> features.blurImage());
    this.fileSharpenButton.addActionListener(evt -> features.sharpenImage());
    this.fileHFlipButton.addActionListener(evt -> features.hFlip());
    this.fileVFlipButton.addActionListener(evt -> features.vFlip());
    this.sepiaButton.addActionListener(evt -> features.sepiaImage());
    this.grayButton.addActionListener(evt -> features.grayImage());
    this.brightenButton.addActionListener(evt -> features.brightenImage());
    this.grayTypesButton.addActionListener(evt -> features.grayTypes());
    this.downscaleButton.addActionListener(evt -> (features).downscale());
    this.mosaicButton.addActionListener(evt -> features.mosaic());
  }
}
