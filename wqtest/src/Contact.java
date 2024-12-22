public class Contact {
    private int id;
    private String class1;
    private String name;
    private String number;
    private double area;
    private String now;

    public Contact() {
    }

    public Contact(int id, String class1, String name, String number, double area, String now) {
        this.id = id;
        this.class1 = class1;
        this.name = name;
        this.number = number;
        this.area = area;
        this.now = now;
    }

    public Contact(String class1, String name, String number, double area, String now) {
        this.class1 = class1;
        this.name = name;
        this.number = number;
        this.area = area;
        this.now = now;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
}
