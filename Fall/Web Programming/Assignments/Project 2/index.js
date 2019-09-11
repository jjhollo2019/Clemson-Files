//I'm defining these variables here so they're accessible
//in the init and other child scopes

//rootNode needs to be accessible from everything
//but no guarantee that the DOM is ready
//to do an ID lookup right now, so assign it in init
let rootNode;

//our array of button dom elements
let buttonNodes = [
  [], //row 0
  [], //row 1
  [] //row 2
];

let onclick = function(token){
  let x = this.row;
  let y = this.col;

  this.innerHTML = token;
  this.owned = token;
  this.disabled = true;
}

for(let i = 0; i < 3; i++){
  let rowDiv = document.createElement("div");
  for(let j = 0; j < 3; j++){
    let btn = (buttonNodes[i][j] = document.createElement("button"));
    btn.innerHTML = "_";
    btn.owned = false;
    btn.row = i;
    btn.col = j;
    btn.onclick = onclick;
    rowDiv.appendChild(btn);
  }
}

let checkWin = function(token){
  //console.log(this);
  let x = this.row;
  let y = this.column;

  let counter = 0;
  for(let i = 0; i < 3; i++){
    if(this[x][i] == token){
      counter++;
    }
    else{
      counter = 0;
      break;
    }
  }
  if(counter == 3){
    return true;
  }
  counter = 0;
  for(i = 0; i < 3; i++){
    if(this[i][y] == token){
      counter++;
    }
    else{
      counter = 0;
      break;
    }
  }
  if(counter == 3){
    return true;
  }
  counter = 0;
  for(i = 0; i < 3; i++){
    if(this[i][i] == token){
      counter++;
    }
    else{
      counter = 0;
      break;
    }
  }
  if(counter == 3){
    return true;
  }
  counter = 0;
  for(i = 2; i >= 0; i--){
    if(this[i][i] == token){
      counter++;
    }
    else{
      return false;
    }
  }
  return true;
}

//this gets called by the 'load' event listener
let init = function() {
  rootNode = document.getElementById("app");

  //create and add the 9 game board buttons
  //to the array and to DOM
  //assign an onclick callback

  //create and add the "AI Go First" button

  //create a reload button here if you want?
};

//called once page is laded,
//DOM is ready and has all it's nodes loaded
window.addEventListener("load", init);
