let str = "this is globally accessible";

console.log("from global scope", str);

let f1 = function(){
    console.log("from f1 scope", str);
};

f1();

let f2 = function(){
    let str = "this is overriding the global str";
    console.log("from f2 scope", str);
};

f2();
console.log("from f1 scope", str);

let str2 = "str2";
let f3 = function(str){
    console.log("from f3 scope", str);
};

f3(str2);

let f4 = function(){
    let str = arguments[0];
    console.log("from f4 scope", str);
};

f4(str2);

let f5 = function(){
    for(let i = 0; i < arguments.length; i++){
        console.log("from f5 argument[" + i + "]", arguments[i]);
    }
};

f5(str2, "123");

let addNumbers = function(a, b){
    console.log("adding " + a + " to " + b);
    return a + b;
};

let addNumbers = (a, b) => {
    //no this 
    //no arguments
    console.log("adding " + a + " to " + b);
    return a + b;
};

//no brackets
let addNumbers = (a,b) => a+b;

//hoisting, using let avoids this problem
let obj1 = {a:"123"};
let f10 = ()=>{
    console.log("first", obj1);
    var obj1 = "123";
    console.log("second", obj1);
};

let doStuff = () => {
    return new Promise((resolve, reject)=>{
        setTimeout(resolve,1000);
    })
};

doStuff().then(()=> console.log("done doing stuff"));