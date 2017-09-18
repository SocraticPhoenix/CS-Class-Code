public class Rectangle implements Comparable<Rectangle> {
    private int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(int width) {
        this(width, width);
    }

    public Rectangle() {
        this(10);
    }

    public int getWidth() {
        return this.width;
    }

    public Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public int area() {
        return this.width * this.height;
    }

    public int perimeter() {
        return 2 * (this.width + this.height);
    }

    @Override
    public int compareTo(Rectangle o) {
        return Integer.compare(this.area(), o.area());
    }

    @Override
    public String toString() {
        return "{width: " + this.width + ", height: " + this.height + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Rectangle) {
            Rectangle or = (Rectangle) other;
            return this.width == or.width && this.height == or.height;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.height * 31 + this.width;
    }

}
