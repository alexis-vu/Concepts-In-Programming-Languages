#include <stdio.h>
#include <stdlib.h>

typedef double (*VirtualMethodPointer)(void*);
typedef VirtualMethodPointer* VTableType;

// SHAPE CLASS
typedef struct {
  VTableType VPointer;
  char* name;
} Shape;

double Shape_area(Shape* _this) {
  return 0.0;
}

void Shape_draw(Shape* _this) {
  printf("Shape.draw() You should never see this.\n");
}

VirtualMethodPointer Shape_VTable[] = {
  (VirtualMethodPointer) Shape_area,
  (VirtualMethodPointer) Shape_draw
};

Shape* Shape_Shape(Shape* _this, char* name) {
  _this->VPointer = Shape_VTable;
  _this->name = name;
  return _this;
}
// SHAPE CLASS END

// CIRCLE CLASS
#define PI 3.14159

typedef struct {
  VTableType VPointer;
  char* name;
  int radius;
} Circle;

double Circle_area(Circle* _this) {
  return _this->radius * _this->radius * PI;
}

void Circle_draw(Circle* _this) {
  //printf("%s(%i) : %d\n", _this->name, _this->radius, _this->VPointer[0]);
  printf("  ****\n ******\n********\n ******\n  ****\n");
}

VirtualMethodPointer Circle_VTable[] = {
  (VirtualMethodPointer) Circle_area,
  (VirtualMethodPointer) Circle_draw
};

Circle* Circle_Circle(Circle* _this, char* name, int radius) {
  Shape_Shape((Shape*) _this, name);
  _this->VPointer = Circle_VTable;
  _this->radius = radius;
  return _this;
}
// CIRCLE CLASS END

// RECTANGLE CLASS
typedef struct {
  VTableType VPointer;
  char* name;
  int length;
} Square;

double Square_area(Square* _this) {
  return _this->length * _this->length;
}

void Square_draw(Square* _this) {
  int i;
  for (i = 0; i < 4; ++i) {
    printf("******\n");
  }
}

VirtualMethodPointer Square_VTable[] = {
  (VirtualMethodPointer) Square_area,
  (VirtualMethodPointer) Square_draw
};

Square* Square_Square(Square* _this, char* name, int length) {
  Shape_Shape((Shape*) _this, name);
  _this->VPointer = Square_VTable;
  _this->length = length;
  return _this;
}
// SQUARE CLASS END

// TRIANGLE CLASS
typedef struct {
  VTableType VPointer;
  char* name;
  int base;
  int height;
} Triangle;

double Triangle_area(Triangle* _this) {
  return 0.5 * _this->base * _this->height;
}

void Triangle_draw(Triangle* _this) {
  printf("    *\n   ***\n  *****\n *******\n*********\n");
}

VirtualMethodPointer Triangle_VTable[] = {
  (VirtualMethodPointer) Triangle_area,
  (VirtualMethodPointer) Triangle_draw
};

Triangle* Triangle_Triangle(Triangle* _this, char* name, int base, int height) {
  Shape_Shape((Shape*) _this, name);
  _this->VPointer = Triangle_VTable;
  _this->base = base;
  _this->height = height;
  return _this;
}
// TRIANGLE CLASS END

typedef struct {
  VTableType VPointer;
  char* name;
  int length;
  int width;
} Rectangle;

double Rectangle_area(Rectangle* _this) {
  return _this->length * _this->width;
}

void Rectangle_draw(Rectangle* _this) {
  int i;
  for (i = 0; i < 4; ++i) {
    printf("************\n");
  }
}

VirtualMethodPointer Rectangle_VTable[] = {
  (VirtualMethodPointer) Rectangle_area,
  (VirtualMethodPointer) Rectangle_draw
};

Rectangle* Rectangle_Rectangle(Rectangle* _this, char* name, int length, int width) {
  Square_Square((Square*) _this, name, length);
  _this->VPointer = Rectangle_VTable;
  _this->width = width;
  return _this;
}
// RECTANGLE CLASS END

int main(int argc, char** argv)
{
  int arg1 = atoi(argv[1]);
  int arg2 = atoi(argv[2]);

  Shape* s[] = {
    (Shape*) Triangle_Triangle((Triangle*) malloc(sizeof(Triangle)), "FirstTriangle", arg1, arg2),
    (Shape*) Triangle_Triangle((Triangle*) malloc(sizeof(Triangle)), "SecondTriangle", arg1 - 1, arg2 - 1),
    (Shape*) Circle_Circle((Circle*) malloc(sizeof(Circle)), "FirstCircle", arg1),
    (Shape*) Circle_Circle((Circle*) malloc(sizeof(Circle)), "SecondCircle", arg1 - 1),
    (Shape*) Square_Square((Square*) malloc(sizeof(Square)), "FirstSquare", arg1),
    (Shape*) Square_Square((Square*) malloc(sizeof(Square)), "SecondSquare", arg1 - 1),
    (Shape*) Rectangle_Rectangle((Rectangle*) malloc(sizeof(Rectangle)), "FirstRectangle", arg1, arg2),
    (Shape*) Rectangle_Rectangle((Rectangle*) malloc(sizeof(Rectangle)), "SecondRectangle", arg1 - 1, arg2 - 1)
  };

  int i;
  double totalArea = 0.0;
  int size = sizeof(s) / sizeof(s[0]);
  for (i = 0; i < size; ++i) {
    switch(i) {
      case 0:
      case 1:
        printf("%s(%i, %i) : %f\n\n", s[i]->name, ((Triangle*)s[i])->base, ((Triangle*)s[i])->height, (s[i]->VPointer[0])(s[i]));
        (s[i]->VPointer[1])(s[i]);
        printf("\n");
        break;
      case 2:
      case 3:
        printf("%s(%i) : %f\n\n", s[i]->name, ((Circle*)s[i])->radius, (s[i]->VPointer[0])(s[i]));
        (s[i]->VPointer[1])(s[i]);
        printf("\n");
        break;
      case 4:
      case 5:
        printf("%s(%i) : %f\n\n", s[i]->name, ((Square*)s[i])->length, (s[i]->VPointer[0])(s[i]));
        (s[i]->VPointer[1])(s[i]);
        printf("\n");
        break;
      case 6:
      case 7:
        printf("%s(%i, %i) : %f\n\n", s[i]->name, ((Rectangle*)s[i])->length, ((Rectangle*)s[i])->width, (s[i]->VPointer[0])(s[i]));
        (s[i]->VPointer[1])(s[i]);
        printf("\n");
        break;
    }

    totalArea += (s[i]->VPointer[0])(s[i]);
  }

  printf("\nTotal: %f\n", totalArea);

  return 0;
}
