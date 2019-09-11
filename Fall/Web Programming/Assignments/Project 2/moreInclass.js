let add2 = function(a, b){
    return a+b;
}

let addTo5 = function(a){
    return add2(a,5);
}

addTo5(3);

let to5 = function(f,a){
    return function(a){
        return f(a,5);
    }
}

let addTo5 = to5(add2)

addTo5(3)

let f = function(a){
    a += '1';
    return a;
}

let str = "123";
f(str)

let obj = {a: "123"};

f(obj)

let obj2 = {};
obj['abc'] = 123;
obj2.abc = 456;