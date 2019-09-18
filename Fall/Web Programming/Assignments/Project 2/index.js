const winners = [
  [0,1,2],
  [3,4,5],
  [6,7,8],
  [0,4,8],
  [2,4,6],
  [0,3,6],
  [1,4,7],
  [2,5,8]
];

const board = () => Array.from(document.getElementsByClassName('btn'));
const getFirst = () => Array.from(document.getElementsByClassName('goFirst'));
const getRefresh = () => Array.from(document.getElementsByClassName('refresh'));
const getId = (btn1) => Number.parseInt(btn1.id.replace('B', ''));
const empty = () => board().filter(btn1 => btn1.innerText === '');
const match = (arr) => arr.every(btn1 => btn1.innerText === arr[0].innerText && btn1.innerText !== '');

const turn = (index, token) => board()[index].innerText = token;
const computerPick = () => getId(empty()[Math.floor(Math.random() * empty().length)]);

const gameOver = (winCombo) => { 
  winCombo.forEach(btn1 => btn1.classList.add('win'));
  disableListeners();
};

const draw = () => {
  board().forEach(btn1 => btn1.classList.add('draw'));
  disableListeners();
};

const checkWin = () => {
  let win = false;
  winners.forEach(w => {
    const grid = board();
    const combo = [grid[w[0]], grid[w[1]], grid[w[2]]];
    if(match(combo)) {
      win = true;
      gameOver(combo);
    }
  });
  if(empty().length === 0){
    draw();
  }
  return win;
};

const computerTurn = () => {
  disableFirst();
  disableListeners();
  setTimeout(() => {
    turn(computerPick(), 'O');
    if(!checkWin())
      enableListeners();
  }, 1000);
};

const clickFunc = (event) => {
  turn(getId(event.target), 'X');
  if(!checkWin())
    computerTurn();
};

const goFirst = () => {
  computerTurn();
  disableFirst();
};

const refreshPage = () => {
  if(checkWin())
    window.location.reload();
}

const enableListeners = () => board().forEach(btn1 => btn1.addEventListener('click', clickFunc));
const disableListeners = () => board().forEach(btn1 => btn1.removeEventListener('click', clickFunc));
const enableFirst = () => getFirst()[0].addEventListener('click', goFirst);
const disableFirst = () => getFirst()[0].removeEventListener('click', goFirst);
const enableRefresh = () => getRefresh()[0].addEventListener('click', refreshPage);


enableListeners();
enableFirst();
enableRefresh();
