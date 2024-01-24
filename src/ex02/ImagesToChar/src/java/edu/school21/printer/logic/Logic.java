package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Parameters(separators = "=")
class argsString {
    @Parameter(
            names = { "--white"},
            arity = 1,
            required = true
    )
    public  String mama;

    @Parameter(
            names = { "--black"},
            arity = 1,
            required = true
    )
    public String kaka;

     Map<String, String> colors = new HashMap<>();


    public Map<String, String> getColors() {
        return colors;
    }
}

public class Logic {
    String[] args;
    public Logic(String[] args) {
        this.args = args;
    };
    public void printImage() {
        argsString as = new argsString();
        JCommander jCommander = JCommander.newBuilder().addObject(as).build();
        jCommander.parse(args);
        System.out.println(as.kaka + " " + as.mama);
        Map<String, String> colors = as.getColors();
        for (Map.Entry<String, String> entry : colors.entrySet()) {
            System.out.println("Color " + entry.getKey() + ": " + entry.getValue());
        }
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();
        byte[][] result;
        try {
            result = seeBMPImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int y = 0; y < result[0].length; y++) {
            for (byte[] ints : result) {
                //System.out.println(ints[y]);
                if(ints[y] < 0) cp.setBackgroundColor(Ansi.BColor.RED);
                else cp.setBackgroundColor(Ansi.BColor.GREEN);
                cp.print("   ");
            }
            System.out.println();
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
