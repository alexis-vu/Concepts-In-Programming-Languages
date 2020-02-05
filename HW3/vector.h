#ifndef VECTOR_H
#define VECTOR_H

#include <iostream>
#include <stdexcept>
using namespace std;

template <typename T>
class Vector {
private:
	int sz;
	T* buf;

public:

	// DEFAULT CONSTRUCTOR
	Vector(int n) {
		sz = n;
		buf = new T[sz];
	}

	// INITIALIZER LIST
	Vector(initializer_list<T> L) {
		sz = L.size();
		buf = new T[sz];

		T* temp = buf;
		for (const auto i : L) {
			*temp = i;
			++temp;
		}
	}

	// DESTRUCTOR
	~Vector() {
		sz = 0;
		delete [] buf;
		buf = nullptr;
	}

	// COPY CONSTRUCTOR
	Vector(const Vector & v) {
		sz = v.sz;
		buf = new T[sz];

		for (int i = 0; i < sz; ++i) {
			buf[i] = v.buf[i];
		}
	}

	// SIZE ACCESSOR
	int size() const {
		return sz;
	}

	// SUBSCRIPT OPERATOR
	T& operator[](const int i) {
		if (i < 0 || i >= sz) {
			throw out_of_range("Vector<T>::operator[] : index is out of range");
		}

		return buf[i];
	}

	// PRODUCT OPERATOR
	T operator*(const Vector& v) const {
		T product = 0;

		for (int i = 0; i < v.sz && i < sz; ++i) {
			product += (buf[i] * v.buf[i]);
		}

		return product;
	}

	// ADD OPERATOR
	Vector operator+(const Vector& v) const
	// V3 = V1 + V2; [1, 2, 3] + [4, 5, 6, 7] = [5, 7, 9, 7]
	{
			if (sz <= v.sz) {
			Vector<T> v3(v);

			for (int i = 0; i < sz; ++i) {
				v3.buf[i] += buf[i];
			}

			return v3;
		}
		else {
			Vector<T> v3(*this);

			for (int i = 0; i < v.sz; ++i) {
				v3.buf[i] += v.buf[i];
			}

			return v3;
		}
	}

	// ASSIGNMENT OPERATOR
	const Vector& operator=(const Vector& v)
	{
		if (&v != this) {
			if (sz != v.sz) {
				delete [] buf;
				buf = new T[v.sz];
				sz = v.sz;
			}

			for (int i = 0; i < sz; ++i) {
				buf[i] = v.buf[i];
			}
		}

		return *this;
	}

	// EQUALS OPERATOR
	bool operator==(const Vector& v) const {
		bool equal = true;

		if (sz == v.sz) {
			for (int i = 0; i < sz; ++i) {
				if (buf[i] != v.buf[i]) {
					equal = false;
				}
			}
		}
		else {
			equal = false;
		}

		return equal;
	}

	// NOT EQUALS OPERATOR
	bool operator!=(const Vector & v) const {
		return !(*this == v);
	}

	// FRIEND PRODUCT OPERATOR
	friend Vector operator*(const int n, const Vector & v) {
		Vector<T> newV(v);

		for (int i = 0; i < newV.sz; ++i) {
			newV[i] *= n;
		}

		return newV;
	}

	// FRIEND ADD OPERATOR
	friend Vector operator+(const int n, const Vector & v) {
		Vector<T> newV(v);

		for (int i = 0; i < newV.sz; ++i) {
			newV[i] += n;
		}

		return newV;
	}

	friend ostream& operator<<(ostream & o, const Vector & v) {
		// Implementation Here;
		o << "(";

		for (int i = 0; i < v.sz - 1; ++i) {
			o << v.buf[i] << ", ";
		}

		o << v.buf[v.sz - 1] << ")" << endl;

		return o;
	}
};

#endif
