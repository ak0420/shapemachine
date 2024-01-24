import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

abstract class CShape {
    private static int shapeCount = 0;
    private int id;

    public CShape() {
        this.id = ++shapeCount;
    }

    abstract String getDimensions();

    public int getId() {
        return id;
    }
}

class COval extends CShape {
    private int horizontalRadius;
    private int verticalRadius;

    public COval(int horizontalRadius, int verticalRadius) {
        super();
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    @Override
    String getDimensions() {
        return "OVAL " + horizontalRadius + "x" + verticalRadius;
    }
}

class CCircle extends COval {
    public CCircle(int radius) {
        super(radius, radius);
    }

    @Override
    String getDimensions() {
        return "CIRCLE " + super.getDimensions().split(" ")[1];
    }
}

class CRectangle extends CShape {
    private int length;
    private int width;

    public CRectangle(int length, int width) {
        super();
        this.length = length;
        this.width = width;
    }

    @Override
    String getDimensions() {
        return "RECTANGLE " + length + "x" + width;
    }
}

class CSquare extends CRectangle {
    public CSquare(int sideLength) {
        super(sideLength, sideLength);
    }

    @Override
    String getDimensions() {
        return "SQUARE " + super.getDimensions().split(" ")[1];
    }
}

class CCanvas {
    private List<CShape> shapes;

    public CCanvas(List<CShape> shapes) {
        Set<String> shapeDimensions = new HashSet<>();
        this.shapes = new ArrayList<>();

        for (CShape shape : shapes) {
            if (shapeDimensions.add(shape.getDimensions())) {
                this.shapes.add(shape);
            }
        }
    }

    public List<CShape> getShapes() {
        return shapes;
    }
}

public class Main {
    public static void main(String[] args) {
        List<CShape> shapes = generateRandomShapes();

        System.out.println("Canvas has the following random shapes:\n");

        for (CShape shape : shapes) {
            System.out.println("Shape " + shape.getId() + ": " + shape.getDimensions());
        }
    }

    private static List<CShape> generateRandomShapes() {
        List<CShape> shapes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            shapes.add(ShapeFactory.generateRandomShape());
        }

        return shapes;
    }
}

class ShapeFactory {
    public static CShape generateRandomShape() {
        Random random = new Random();
        int shapeType = random.nextInt(4);

        switch (shapeType) {
            case 0:
                int ovalHorizontalRadius = random.nextInt(50) + 1;
                int ovalVerticalRadius = random.nextInt(50) + 1;
                return new COval(ovalHorizontalRadius, ovalVerticalRadius);
            case 1:
                int circleRadius = random.nextInt(50) + 1;
                return new CCircle(circleRadius);
            case 2:
                int rectangleLength = random.nextInt(50) + 1;
                int rectangleWidth = random.nextInt(50) + 1;
                return new CRectangle(rectangleLength, rectangleWidth);
            case 3:
                int squareSideLength = random.nextInt(50) + 1;
                return new CSquare(squareSideLength);
            default:
                return null;
        }
    }
}
