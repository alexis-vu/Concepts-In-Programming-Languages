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

connected(X, Y) :-
  door_between(X, Y).
connected(X, Y) :-
  door_between(Y, X).

connect(X, Y, R, [Y|R]) :-
   connected(X, Y).
connect(X, Y, V, R) :-
   connected(X, Z),
   Z \== Y,
   \+member(Z, V),
   connect(Z, Y, [Z|V], R).

   male(klefstad).
   male(bill).
   male(mark).
   male(fred).
   male(isaac).

   female(beth).
   female(emily).
   female(heidi).
   female(susan).
   female(jane).

   speaks(klefstad, english).
   speaks(bill, english).
   speaks(emily, english).
   speaks(heidi, english).
   speaks(isaac, english).

   speaks(beth, french).
   speaks(mark, french).
   speaks(susan, french).
   speaks(isaac, french).

   speaks(klefstad, spanish).
   speaks(bill, spanish).
   speaks(susan, spanish).
   speaks(fred, spanish).
   speaks(jane, spanish).

   can_sit(X, Y) :-
   	X \== Y,
   	male(X),
       male(Y),
       speaks(X, Lang),
       speaks(Y, Lang).

   can_sit(X, Y) :-
   	X \== Y,
   	female(X),
       male(Y),
       speaks(X, Lang),
       speaks(Y, Lang).

   can_sit(X, Y) :-
   	X \== Y,
   	male(X),
       female(Y),
       speaks(X, Lang),
       speaks(Y, Lang).

   party_seating(L) :-
   	male(X),
   	seat(X, [Last|T]),
   	can_sit(Last,X),
       L = [Last,T|X].

   seat(Prev,L):-
   	can_sit(Prev,Curr),
       \+member(Curr, L),
       seat(Curr,[Curr|L]),
       L = [Curr|L].
