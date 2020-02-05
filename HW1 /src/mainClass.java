public class mainClass {

	// Base Class: Shape 
	static class Shape {
		String name;

		public Shape(String name) {
			this.name = name;
		}

		double area() {
			return 0.0;
		}

		void draw() {
			System.out.println("Shape.draw() You should never see this.");
		}
	}

	// Derived Class: Circle
	static class Circle extends Shape {
		int radius;

		public Circle(String name, int radius) {
			super(name);
			this.radius = radius;
		}

		public double area() {
			return (Math.PI * Math.pow(this.radius, 2));
		}

		public void draw() {
			System.out.println(this + "\n");
			System.out.println("  ****\n" +
					" ******\n" +
					"********\n" +
					" ******\n" +
					"  ****");
		}
		
		public String toString() {
			return (this.name + "(" + this.radius + ") : " + this.area());
		}
	}

	// Derived Class: Square
	static class Square extends Shape {
		int length;

		public Square(String name, int length) {
			super(name);
			this.length = length;
		}

		public double area() {
			return (Math.pow(this.length, 2));
		}

		public void draw() {
			System.out.println(this + "\n");
			for (int i = 0; i < 4; ++i) {
				System.out.println("******");
			}
		}
		
		public String toString() {
			return (this.name + "(" + this.length + ") : " + this.area());
		}
	}

	// Derived Class: Triangle
	static class Triangle extends Shape {
		int base;
		int height;

		public Triangle(String name, int base, int height) {
			super(name);
			this.base = base;
			this.height = height;
		}

		public double area() {
			return ((this.base * this.height) * 0.5);
		}

		public void draw() {
			System.out.println(this + "\n");
			System.out.println("    *\n" +
					"   ***\n" +
					"  *****\n" +
					" *******\n" +
					"*********");
		}
		
		public String toString() {
			return (this.name + "(" + this.base + ", " + this.height + ") : " + this.area());
		}
	}

	// Derived Class: Rectangle
	static class Rectangle extends Square {
		int width;

		public Rectangle(String name, int length, int width) {
			super(name, length);
			this.width = width;
		}

		public double area() {
			return (this.length * this.width);
		}

		public void draw() {
			System.out.println(this + "\n");
			for (int i = 0; i < 4; ++i) {
				System.out.println("********");
			}
		}
		
		public String toString() {
			return (this.name + "(" + this.length + ", " + this.width + ") : " + this.area());
		}
	}

	// Linked List Implementation
	static class ListNode {
		Object info;
		ListNode next;

		ListNode(Object info, ListNode next) {
			this.info = info;
			this.next = next;
		}
	}

	static class LinkedList {
		ListNode head;

		LinkedList() {
			this.head = null;
		}

		int length() {
			int length = 0;
			ListNode temp = this.head;

			while (temp != null) {
				++length;
				temp = temp.next;
			}

			return length;
		}

		void add(Object object) {
			if (this.length() == 0) {
				this.head = new ListNode(object, null);
			}
			else {
				ListNode temp = this.head;

				while (temp.next != null) {
					temp = temp.next;
				}

				temp.next = new ListNode(object, null);
			}
		}
	}

	static class Picture {
		LinkedList list;
		
		Picture() {
			list = new LinkedList();
		}
		
		void add(Shape shape) {
			this.list.add(shape);
		}
		
		void drawAll() {
			int size = this.list.length();
			ListNode current = this.list.head;
			
			for (int i = 0; i < size; ++i) {
				((Shape)current.info).draw();
				System.out.println();
				current = current.next;
			}
		}
		
		double totalArea() {
			double area = 0.0;
			int size = this.list.length();
			ListNode current = this.list.head;
			
			
			for (int i = 0; i < size; ++i) {
				area += ((Shape)current.info).area();
				current = current.next;
			}
			
			return area;
		}
	}

	public static void main(String[] args) {
		Picture p = new Picture();
		
		p.add(new Triangle("FirstTriangle", Integer.parseInt(args[0]), Integer.parseInt(args[1])));
		p.add(new Triangle("SecondTriangle", Integer.parseInt(args[0]) - 1, Integer.parseInt(args[1]) - 1));
		
		p.add(new Circle("FirstCircle", Integer.parseInt(args[0])));
		p.add(new Circle("SecondCircle", Integer.parseInt(args[0]) - 1));
		
		p.add(new Square("FirstSquare", Integer.parseInt(args[0])));
		p.add(new Square("SecondSquare", Integer.parseInt(args[0]) - 1));
		
		p.add(new Rectangle("FirstRectangle", Integer.parseInt(args[0]), Integer.parseInt(args[1])));
		p.add(new Rectangle("SecondRectangle", Integer.parseInt(args[0]) - 1, Integer.parseInt(args[1]) - 1));
		
		p.drawAll();
		System.out.println("Total: " + p.totalArea());
	}

}
