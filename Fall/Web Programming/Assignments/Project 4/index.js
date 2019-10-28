//load the filesystem core nodejs package so we can read and write to the FS
const fs = require('fs');
//load crypto
const crypto = require('crypto');
//load in the express and express-session packages
const express = require('express')
const session = require('express-session')

//get the bodyparser middleware package
const bodyparser = require('body-parser')

//call express to get an app object, to be used like in php/slim
const app = express()

//setup the port we want to use
const port = 3000
//setup the filepath to read and write DB to
const dbFilePath = 'db/database.json'

//lets load our database into memory
let db;
try{
	let rawdata = fs.readFileSync(dbFilePath);  
	db = JSON.parse(rawdata);
}catch (e){
	//cant read file, make a blank db to use
	db = {};
}
console.log("DB",JSON.stringify(db));

//define some helper functions
let writeDB = () => {
	let str = JSON.stringify(db);
	//using sync so we can be sure its written to disk when the function ends
	fs.writeFileSync(dbFilePath, str);
};
let getUser = (username) =>{
	if(db[username]){
		return db[username];
	}
	return false;
};
let saveUser = (userObj) =>{
	let username = userObj.username;
	if(db[username]){
		return false;
	}
	db[username] = userObj;
	writeDB();
	return true;
};
let authUser = (username, password, salt) => {
	if(db[username].hash === createHash(password, salt)){
		return true;
	}
	return false;
};

//setup session
//its always on, no need to session_start
app.use(session({
	secret: 'keyboard cat',
	resave: false,
	saveUninitialized: true
}));

// to support URL-encoded bodies like what our forms will Post us
app.use(bodyparser.urlencoded({extended: true})); 

//register a listener for GET requests to / and return 'Hello World' as our response
app.get('/', (req, res) => {
	return res.send('Hello World!')
});


app.get('/users/:username', (req,res)=>{
	if(getUser(req.params.username) !== false) {
		return res.send(200);
	} else {
		return res.send(404);
	}
	//given a username, return a status 200 if it exists
	//and return a 404 if it does not exist.
});

const createHash = (password, salt) => crypto.createHash('sha256').update(password+salt).digest('hex');

const minLength = (length, value) => value.length > length ? true : "Error: too short"; 

const maxLength = (length, value) => value.length <= length ? true : "Error: too long";

const regex = (regex, value) => regex.test(value) ? true : "Error: can only be letters and numbers";

const validate = (value) => (minLength(3, value) && maxLength(49, value) && regex(/^[0-9a-zA-Z]+$/, value));

app.post('/users', (req,res)=>{
	//given a username, name and password as post variables
	//validate that username, name and password are longer than 3
	//and shorter than 50
	//further validate the username and name that they only contain 
	//letters, numbers and spaces
	//hash the password using todays date and time as a salt.
	//store the salt and the resulting hash, but not the password
	//return a 302 -> login.html on success
	//return a 302 -> registration.html#errorMessage on error
	//you must urlencode the errorMessage
	if(validate(req.body.username) && validate(req.body.password)) { 
		const salt = ""+new Date();
		const username = req.body.username;
		const password = req.body.password;
		const name = req.body.name;
		const hash = createHash(password, salt);
		const user = new Object();
		user.username = username;
		user.hash = hash;
		user.salt = salt;
		user.name = name;
		if(getUser(username) === false) {
			saveUser(user);
			res.redirect(302, '/login.html');
		} else {
			error = 'Error on users';
			res.redirect(302, '/registration.html#'+encodeURIComponent(error));
		}
		
	}
});

app.post('/auth',(req,res)=>{
	//given a username and password
	//validate that the username exists
	//hash the submitted password using 
	//the date stored on the users object
	//and compare the resulting hash to the stored hash
	//if it passes, save the user's name into the session
	//and redirect 302 to index.html 
	//(which should be able to read the name from session)
	//if it doesn't work return a 302 -> login.html#errorMessage
	//you must urlencode the errorMessage
	if(authUser(req.body.username, req.body.password, getUser(req.body.username).salt)) {
		var sess = req.session;
		sess.name = getUser(req.body.username).name;
		res.redirect(302, '/index.html');
	} else {
		error = 'Error verifying user';
		res.redirect(302, '/login.html#'+encodeURIComponent(error));
	}
});

app.get('/index.html',(req,res)=>{
	let hello = '';
	if(req.session && req.session.name){
		hello = "Hi "+req.session.name+"!"
	}
	let page = `
	<!DOCTYPE html>
	<title>Project 4</title>
	<h1>Project 4</h1>
	<section>
		<p>
		${hello}
		Go <a href="login.html">here to login</a> or <a href="registration.html">here to register</a>.
		</p>
	</section>`
	res.send(page)
});

//we're telling express to try to match file in our static directory.
//so if a request comes in for /registration.html
//it will first look in try to match one of our above defined routes
//and if it doesnt match a route it will look for proj4/static/registration.html
//if this app.use line were at the top, then our files in static would take precidence
app.use(express.static('static'));

//done with startup and registering callbacks
//all we have left to do is listen on the port 
//and if we were able to grab the port
//run our callback that console logs the achievement
app.listen(port, () => console.log(`Project 4 listening on port ${port}!`));
