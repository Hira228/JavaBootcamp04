package edu.school21.printer.application;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.util.Arrays;

public class ConsoleImageConverter {
    public static void main(String[] args) {
        edu.school21.printer.logic.Logic logic = new edu.school21.printer.logic.Logic(args);
        logic.printImage();
    }
}
