package smellyshapes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShapeGroup implements Shape {

    protected boolean readOnly = false;
    Set<Shape> shapes = new HashSet<>();

    public ShapeGroup() {
    }

    public ShapeGroup(Set<Shape> shapes, boolean readOnly) {
        this.shapes.addAll(shapes);
        this.readOnly = readOnly;
    }

    public void add(Shape shape) {
        if (readOnly || contains(shape)) {
            return;
        }

        addToShapes(shape);
    }

    private void addToShapes(Shape shape) {
        this.shapes.add(shape);
    }

    public boolean contains(Shape shape) {
        return this.shapes.contains(shape);
    }

    public boolean contains(int x, int y) {
        return this.shapes.stream()
                .filter(Objects::nonNull)
                .anyMatch(shape -> shape.contains(x, y));
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder();

        builder.append("<shapegroup>\n");
        this.shapes.stream().map(Shape::toXml).forEach(builder::append);
        builder.append("</shapegroup>\n");

        return builder.toString();
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public int size() {
        return this.shapes.size();
    }
}
