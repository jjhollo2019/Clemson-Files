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

    document.getElementById("app").innerHTML = `
<h1>Hello Parcel!</h1>
<div id="main">
  <button id="b1">Click Me</button>
  <div id="counter">0</div>
</div>
`;

let mainNode = document.getElementById("main");
let b1Node = document.getElementById("b1");
let counter = 0;
b1Node.onclick = function() {
  counter++;
  document.getElementById("counter").innerHTML = counter;
};

//make a 2nd button and counter that uses setTimeout

//try storing counter inside a closure