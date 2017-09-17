package com.company.IO;

import com.company.Main;
import com.company.Structure.Position;

import java.io.IOException;

public abstract class PositionReader {
    public Position readPosition() throws IOException {
        Position position;
        try {
            position = readPositionWithNoValidation();
        } catch (Exception e) {
            throw new IOException("Position entered in the wrong format, use x,y");
        }
        validate(position);
        return position;
    }

    private void validate(Position position) {
        if (position.x < -Main.WORLD_SIZE / 2 || position.y < -Main.WORLD_SIZE / 2 ||
                position.x > (Main.WORLD_SIZE - 1) / 2 || position.y > (Main.WORLD_SIZE - 1) / 2)
            throw new IllegalArgumentException("Out of bound position entered: " + position +
                    ". World is [" + (-Main.WORLD_SIZE / 2) + ".." + ((Main.WORLD_SIZE - 1) / 2) + "]");
    }

    protected abstract Position readPositionWithNoValidation();
}
