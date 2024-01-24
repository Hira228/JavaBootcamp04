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
import java.util.HashMap;
import java.util.Map;

@Parameters(separators = "=")
class argsString {
    Map<String, Ansi.BColor> colors;
    argsString(){
        colors = new HashMap<>();
        colors.put("RED", Ansi.BColor.RED);
        colors.put("BLACK", Ansi.BColor.BLACK);
        colors.put("GREEN", Ansi.BColor.GREEN);
        colors.put("BLUE", Ansi.BColor.BLUE);
        colors.put("YELLOW", Ansi.BColor.YELLOW);
        colors.put("CYAN", Ansi.BColor.CYAN);
        colors.put("MAGENTA", Ansi.BColor.MAGENTA);
        colors.put("NONE", Ansi.BColor.NONE);
    }
    @Parameter(
            names = { "--white"},
            arity = 1,
            required = true
    )
    private  String white;

    @Parameter(
            names = { "--black"},
            arity = 1,
            required = true
    )
    private String black;

    public Map<String, Ansi.BColor> getColors() {
        return colors;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}

public class Logic {
    String[] args;
    public Logic(String[] args) {
        this.args = args;
    }
    public void printImage() {
        argsString as = new argsString();
        JCommander jCommander = JCommander.newBuilder().addObject(as).build();
        jCommander.setCaseSensitiveOptions(false);
        jCommander.parse(args);
        Map<String, Ansi.BColor> colors = as.getColors();
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();

        byte[][] result;
        try {
              result = seeBMPImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int y = 0; y < result[0].length; y++) {
            for (byte[] ints : result) {
                    if(ints[y] < 0) cp.setBackgroundColor(colors.get(as.getWhite().toUpperCase()));
                    else cp.setBackgroundColor(colors.get(as.getBlack().toUpperCase()));
                    cp.print("   ");
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
