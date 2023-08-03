package smellyshapes;

import java.util.Arrays;
import java.util.Objects;

// TODO: Speculative generality
// Can move down required functions
public class ShapeGroup extends AbstractShape {

    protected boolean readOnly = false;
    Shape[] shapes = new Shape[10];
    int size = 0;

    public ShapeGroup() {
    }

    public ShapeGroup(Shape[] shapes, boolean readOnly) {
        this.shapes = shapes;
        this.size = shapes.length;
        this.readOnly = readOnly;
    }

    public void add(Shape shape) {
        if (readOnly || contains(shape)) {
            return;
        }

        if (shouldGrow()) {
            grow();
        }

        addToShapes(shape);
    }

    private void addToShapes(Shape shape) {
        shapes[size++] = shape;
    }

    private void grow() {
        var newShapes = new Shape[shapes.length + 10];
        for (int i = 0; i < size; i++) {
            newShapes[i] = shapes[i];
        }
        shapes = newShapes;
    }

    private boolean shouldGrow() {
        return size + 1 > shapes.length;
    }

    public boolean contains(Shape shape) {
        for (int i = 0; i < size; i++) {
            if (shapes[i].equals(shape)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(int x, int y) {
        return Arrays.stream(shapes)
                .filter(Objects::nonNull)
                .anyMatch(shape -> shape.contains(x, y));
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
