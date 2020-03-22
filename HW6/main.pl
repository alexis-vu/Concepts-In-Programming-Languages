my_reverse([], []).
my_reverse([H|T], R) :-
	my_reverse(T, RevT), append(RevT, [H], R).

my_length([], 0).
my_length([H|T], R) :-
	my_length(T, Tlength), R is Tlength + 1.

my_subset(_, [], []).
my_subset(X, [H|T], [H|R]) :-
	Term =.. [X, H], Term, my_subset(X, T, R).
my_subset(X, [_|T], R) :-
	my_subset(X, T, R).

my_member(X, [X|T]).
	my_member(X, [H|T]) :-
		my_member(X, T).

my_intersect([], _, []).
my_intersect([H|T], L2, [H|R]) :-
    my_member(H, L2), my_intersect(T, L2, R).
my_intersect([_|T], L2, R) :-
    my_intersect(T, L2, R).

compute_change(0, 0, 0, 0, 0).
compute_change(C, Q, D, N, P) :-
    C >= 25, NewC is C - 25, compute_change(NewC, NewQ, D, N, P), Q is NewQ + 1.
compute_change(C, Q, D, N, P) :-
    C >= 10, NewC is C - 10, compute_change(NewC, Q, NewD, N, P), D is NewD + 1.
compute_change(C, Q, D, N, P) :-
    C >= 5, NewC is C - 5, compute_change(NewC, Q, D, NewN, P), N is NewN + 1.
compute_change(C, Q, D, N, P) :-
    C >= 1, NewC is C - 1, compute_change(NewC, Q, D, N, NewP), P is NewP + 1.

compose([], [], []).
compose([], L2, L2).
compose(L1, [], L1).
compose([H1|T1], [H2|T2], [H1,H2|NewT]) :-
    compose(T1, T2, NewT).

palindrome(B, R) :-
	my_reverse(B, RevB), append(B, RevB, R).
