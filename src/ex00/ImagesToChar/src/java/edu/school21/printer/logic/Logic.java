package edu.school21.printer.logic;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {
    public void printImage() {
        byte[][] result;
        try {
            result = seeBMPImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int y = 0; y < result[0].length; y++) {
            for (byte[] ints : result) {
                System.out.print(ints[y] < 0 ? "." : "0");
            }
            System.out.println();
        }
    }
    public byte[][] seeBMPImage() throws IOException {
        File image = new File("./target/resource/it.bmp");
        BufferedImage bufferedImage = ImageIO.read(image);
        byte[][] arr = new byte[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for(int i = 0; i < bufferedImage.getWidth(); ++i) {
            for(int j = 0; j < bufferedImage.getHeight(); ++j) {
                arr[i][j] = (byte)(bufferedImage.getRGB(i, j) >> 8) ;
            }
        }
        return arr;
    }
}
