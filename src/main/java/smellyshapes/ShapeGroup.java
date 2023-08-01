package smellyshapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShapeGroup extends ComplexShape {

    private List<Shape> shapes = new ArrayList<>();

    public ShapeGroup() {
    }

    public ShapeGroup(Shape[] shapes, boolean readOnly) {
        this.shapes = List.of(shapes);
        this.readOnly = readOnly;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public int getSize() {
        return this.shapes.size();
    }

    public void add(Shape shape) {
        if (readOnly || contains(shape)) {
            return;
        }
        shapes.add(shape);
    }

    public boolean contains(Shape shape) {
        return shapes.contains(shape);
    }

    public boolean contains(int x, int y) {
        return shapes.stream()
                .filter(Objects::nonNull)
                .anyMatch(shape -> shape.contains(x, y));
    }
}
