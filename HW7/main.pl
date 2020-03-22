%%%%% PATH

connected(X, Y) :-
   door_between(X, Y).
connected(X, Y) :-
   door_between(Y, X).

path_from(X, X, []).
path_from(X, Y, R) :-
   door_between(X, Y),
   R = [X, Y].
path_from(Y, X, R) :-
   door_between(Y, X),
   R = [Y, X].
path_from(X, Y, R) :-
   connect(Y, X, [Y], R1),
   R = R1.

connect(X, Y, R, [Y|R]) :-
   connected(X, Y).
connect(X, Y, V, R) :-
   connected(X, Z),
   Z \== Y,
   \+member(Z, V),
   connect(Z, Y, [Z|V], R).

%%%%% SEATING TABLE

can_sit(X, Y) :- 
	X \== Y,
	male(X),
	male(Y),
	speaks(X,Lang),
	speaks(Y,Lang).
	
can_sit(X, Y) :- 
	male(X),
	female(Y),
	speaks(X,Lang),
	speaks(Y,Lang).
	
can_sit(X, Y) :-
	male(Y),
	female(X),
	speaks(X,Lang),
	speaks(Y,Lang).

seat(First, Last, _, 10, []) :-
    can_sit(First, Last).

seat(First, Curr, Table, Count, [Next|L]) :-
     can_sit(Curr, Next),
     \+member(Next, Table),
     NewCount is Count + 1,
     seat(First, Next, [Next|Table], NewCount, L).

party_seating([First|L]) :-
	female(First),
	seat(First, First, [First], 1, L).
	
%%%%% DERIVATIVE 

% base cases
simplify(N, N) :- 
    number(N).
simplify(X, X):- 
    atomic(X).

% identity 
simplify(X * 0, 0).
simplify(0 * Y, 0).
simplify(X * 1, X).
simplify(1 * Y, Y).
simplify(X / 1, X).
simplify(1 / X, R) :-
    atom(X),
    R is 1 / X.
simplify(1 / X, R) :-
    number(X),
    R is 1 / X.

% constant - addition
simplify(X + Y, R) :-
    number(X),
    number(Y),
    R is X + Y.
simplify(X + Y, R) :-
    number(X),
    atom(Y),
    R is X + Y.
simplify(X + Y, R) :-
    atom(X),
    number(Y),
    R is Y + X.

% constant - minus
simplify(X - Y, R) :-
    number(X),
    number(Y),
    R is X - Y.
simplify(X - Y, R) :-
    number(X),
    atom(Y),
    R is X - Y.
simplify(X - Y, R) :-
    atom(X),
    number(Y),
    R is Y - X.

% constant - multiply
simplify(X * Y, R) :-
    number(X),
    number(Y),
    R is X * Y.
simplify(X * Y, R) :-
    number(X),
    atom(Y),
    R is X * Y.
simplify(X * Y, R) :-
    atom(X),
    number(Y),
    R is Y * X.

% constant - divide
simplify(X / Y, R) :-
    Y \== 0,
    number(X),
    number(Y),
    R is X / Y.
simplify(X / Y, R) :-
    number(X),
    atom(Y),
    R is X / Y.
simplify(X / Y, R) :-
    Y \== 0,
    atom(X),
    number(Y),
    R is Y / X.
simplify(X / Y, R) :-
    atom(X),
    atom(Y),
    R is X / Y.

% constant - exponent
simplify(X ^ 1, X) :-
    atom(X),
    !.
simplify(X ^ 0, 1) :-
 	atom(X),
    !.
simplify(X ^ N, X ^ N):- 
    number(N).

% polynomial
simplify(X + Y, SX + SY) :-
    simplify(X, SX),
    simplify(Y, SY).
simplify(X - Y, SX - SY) :-
    simplify(X, SX),
    simplify(Y, SY).
simplify(X * Y, SX * SY) :-
    simplify(X, SX),
    simplify(Y, SY).
simplify(X / Y, SX / SY) :-
    simplify(X, SX),
    simplify(Y, SY).

diff(N, 0) :-
    number(N). 
diff(x, 1) :-
    !.
diff(-x, -1) :-
    !.
diff(Func ^ Exp, Res) :-
    Exp >= 1,
    Exp2 is Exp - 1,
    diff(Func, DFunc),
    simplify(Exp * Func ^ Exp2 * DFunc, Res).
diff(-(Func), DFunc) :-
    diff(-1 * Func, DFunc).
diff(Func1 * Func2, Func1 * DFunc2 + Func2 * DFunc1) :- 
    diff(Func1, DFunc1),
    diff(Func2, DFunc2).
diff(Func1 + Func2, DFunc1 + DFunc2) :- 
    diff(Func1, DFunc1),
    diff(Func2, DFunc2).
diff(Func1 - Func2, DFunc1 - DFunc2) :- 
    diff(Func1, DFunc1),
    diff(Func2, DFunc2).

deriv(Func, Y) :-
    diff(Func, Res),
    simplify(Res, Y).
    