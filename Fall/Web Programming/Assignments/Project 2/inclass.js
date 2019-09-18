let f = function(){
    return function(){
        return 2;
    };
}

let f2 = f()();
f2 == 2

//primatives (no pointers, all passed by value)
    float
    String
        id = '10012303095120120235'
    Boolean
        true, false

//non-primatives (pointers, pass by reference)
    //function ()
    //arrays []
    //objects {}

    