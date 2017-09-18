package com.company.IO;

import com.company.Structure.Position;

import java.util.Scanner;

public class ConsolePositionReader extends PositionReader {
    @Override
    protected Position readPositionWithNoValidation() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please Input Coordinates:");
        String[] input = reader.next().split(",");
        return new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
    }
}
