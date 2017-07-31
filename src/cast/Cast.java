/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast;

import cast.models.DAObject;
import cast.models.Pageant;
import cast.ui.CandidatePanel;
import com.alee.laf.WebLookAndFeel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.JFrame;

/**
 *
 * @author Zedlav
 */
public class Cast {
    
    private static DAObject database;
    private static List<Pageant> pageants;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        WebLookAndFeel.install();
        WebLookAndFeel.setDecorateAllWindows(true);
        JFrame j = new JFrame();
        j.getContentPane().add(new CandidatePanel());
        j.setSize(1024, 800);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public static DAObject getDatabase(){
        //if no database object is connected, create new object
        if (database == null) {
            database = new DAObject();
        }
        return database;
    }
    
    public static String getSystemName(){
        return "CAST: Computer-Aided Scoring and Tabulation";
    }
    
    public static String getSystemDirectory(){
        return System.getProperty("user.dir");
    }
    
    public static BufferedImage getScaledInstance(
        BufferedImage img, int targetWidth,
        int targetHeight, Object hint, 
        boolean higherQuality)
    {
        int type =
            (img.getTransparency() == Transparency.OPAQUE)
            ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality)
        {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        }
        else
        {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do
        {
            if (higherQuality && w > targetWidth)
            {
                w /= 2;
                if (w < targetWidth)
                {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight)
            {
                h /= 2;
                if (h < targetHeight)
                {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }

    public static void writeJPG(
        BufferedImage bufferedImage,
        OutputStream outputStream,
        float quality) throws IOException
    {
        Iterator<ImageWriter> iterator =
            ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter imageWriter = iterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(quality);
        ImageOutputStream imageOutputStream =
            new MemoryCacheImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        IIOImage iioimage = new IIOImage(bufferedImage, null, null);
        imageWriter.write(null, iioimage, imageWriteParam);
        imageOutputStream.flush();
    }    
}
