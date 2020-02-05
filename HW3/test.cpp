#include "vector.h"
int main() // I’ll start it for you
{
  Vector<int> intVec{1,3,5,7,9};
  Vector<double> doubleVec{1.5,2.5,3.5,4.5};
  Vector<int> iv(intVec);
  Vector<double> dv(doubleVec);
  cout << "intVec" << intVec << endl;
  // "intVec(1, 3, 5, 7, 9)"
  cout << "iv" << iv << endl;
  // "iv(1, 3, 5, 7, 9)"
  cout << "doubleVec" << doubleVec << endl;
  // "doubleVec(1.5, 2.5, 3.5, 4.5)"
  cout << "dv" << dv << endl;
  // "dv(1.5, 2.5, 3.5, 4.5)"

  // add at least one test case for each method defined in Vector
    cout << "TESTING SIZE FUNCTION" << endl;
    cout << intVec.size() << endl;
    // returns 5
    
    cout << "TESTING SUBSCRIPT OPERATOR" << endl;
    cout << intVec[2] << endl;
    // returns 5
    
    cout << "TESTING * OPERATOR" << endl;
    Vector<int> v{1, 2, 3};
    int prod = v * intVec;
    cout << prod << endl;
    
    Vector<int> v2{1, 1, 1, 1, 1, 1};
    prod = v * v2;
    cout << prod << endl;
    
    cout << "TESTING + OPERATOR" << endl;
    int add = v + v2;
    cout << add << endl;
    
  return 0;
}
