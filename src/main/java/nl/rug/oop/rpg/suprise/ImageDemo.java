package nl.rug.oop.rpg.suprise;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * loads an image from disk with the
 * ImageIO class. Also shows how to display the image by creating an
 * ImageIcon, placing that icon an a JLabel, and placing that label on
 * a JFrame.
 */

public class ImageDemo
{

    public ImageDemo(String picPath)
    {
        SwingUtilities.invokeLater(() -> {
            JFrame editorFrame = new JFrame("Image Demo");
            //editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            BufferedImage image = null;
            try
            {
                image = ImageIO.read(new File("suprise" + File.separator +picPath));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            }
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel jLabel = new JLabel();
            jLabel.setIcon(imageIcon);
            editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

            editorFrame.pack();
            editorFrame.setLocationRelativeTo(null);
            editorFrame.setVisible(true);
        });
    }
}