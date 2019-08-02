/* Jeremy Holloway
 * CPSC-3520-001
 * SDE 2
 * 7/29/2019
 */

printList([]).

printList([HH|HT]) :- 
    write(HH), 
    nl, 
    printList(HT).

printList(+H) :- 
    printList([H|H]).

theClass([A], A). 

theClass([_|Tvect], C) :- 
    theClass(Tvect, C).

theClass(+Avect, -C) :- 
    theClass([_|Avect], C).

distanceR2([_],[_], 0).

distanceR2([H1|T1], [H2|T2], DistSq) :- 
    distanceR2(T1, T2, Rest), 
    DistSq is ((H1 - H2) * (H1 - H2)) + Rest. 

distanceR2(+V1, +V2, -DistSq) :- 
    distanceR2([V1|V1],[V2|V2], DistSq).

distanceAllVectors2([_|_],[], []).

distanceAllVectors2(V, [H|T], [D1|D2]) :- 
    distanceR2(V, H, Dis), 
    distanceAllVectors2(V, T, D2), 
    D1 is Dis.

distanceAllVectors2(+V, +Vset, -Dlist) :- 
    distanceAllVectors2(V, [Vset|Vset], Dlist).

nnr1(Test, [HH|HT], Class) :- 
    distanceAllVectors2(Test, [HH|HT], Min), 
    distanceR2(Test, HH, Dist), 
    (min_member(Dist, Min)->theClass(HH, Class); nnr1(Test, HT, Class)).

nnr1(+Test, +H, -Class) :- nnr1(Test, H, Class).