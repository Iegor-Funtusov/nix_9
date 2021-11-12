package ua.com.alevel;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ImageUtils {

    public static void test() throws IOException {
        BufferedImage bi = ImageIO.read(new URL("https://w7.pngwing.com/pngs/278/584/png-transparent-mac-book-pro-macbook-air-laptop-macbook.png"));
        byte[] bytes = toByteArray(bi, "png");
        String bytesBase64 = Base64.encodeBase64String(bytes);
        System.out.println(bytesBase64);
        byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);
        BufferedImage newBi = toBufferedImage(bytesFromDecode);
        ImageIO.write(newBi, "png", new File("some.png"));
    }

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }
}
