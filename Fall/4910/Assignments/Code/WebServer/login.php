<?php
	include("config.php");
	session_start();

	if ($_SERVER["REQUEST_METHOD"] == "POST")
	{
		$username = $_POST['username'];
		$password = $_POST['password'];
		$table = getTable($db, $username);
		if($table === 'DRIVER') {
			$_SESSION['sponsors'] = getSponsors($username);
			$_SESSION['items'] = getItems($username);
		}

		if($table === 'ADMINISTRATIVE') {
			$_SESSION['users'] = getUsers();
		}

		$sql = "SELECT USERNAME FROM " . $table . " WHERE USERNAME = '$username' AND PASSWORD = '$password'";

		if ($result = mysqli_query($db, $sql))
		{
			$row = mysqli_fetch_array($result, MYSQL_ASSOC);
			$count = mysqli_num_rows($result);

			if ($count > 0)
			{
				$_SESSION['login_user'] = $username;
				$_SESSION['table'] = $table;

				header("location: welcome.php");
			}
			else 
			{
				$error = "Your Login Name or Password is invalid";
			}
		}
	}

	function getTable($db, $username) {
		$tables = ['SPONSOR', 'DRIVER', 'ADMINISTRATIVE'];
		foreach($tables as $table){			
			$sql = "SELECT USERNAME FROM " . $table . " WHERE USERNAME = '$username'";

			if ($result = mysqli_query($db, $sql))
			{
				$row = mysqli_fetch_array($result, MYSQL_ASSOC);
				$count = mysqli_num_rows($result);
				if ($count > 0)
				{
					return $table;
				}
			}
		}
		return false;
	}

	function getSponsors($driver) {
		$sql = "SELECT SPONSOR FROM SPONSORED_BY WHERE DRIVER = '$driver'";

		if($result = mysqli_query($db, $sql)) {
			$sponsors = mysqli_fetch_array($result, MYSQL_ASSOC);
			return $sponsors;
		} else {
			return false;
		}
	}

	function getItems($driver) {
		$sponsor = $_SESSION['sponsors'];
		$sql = "SELECT PRODUCT_ID FROM ADD_PRODUCTS WHERE SPONSOR = '$sponsor[0]' AND MARKET = (SELECT ID FROM MARKET WHERE SPONSOR = '$sponsor[0]')";

		if($result = mysqli_query($db, $sql)) {
			$items = mysqli_fetch_array($result, MYSQL_ASSOC);
			return $items;
		} else {
			return false;
		}	
	}

	function getUsers() {
		$sql = "SELECT USERNAME FROM DRIVER";

		if($result = mysqli_query($db, $sql)) {
			$users['drivers'] = mysqli_fetch_array($result, MYSQL_ASSOC);
			$sql2 = "SELECT USERNAME FROM SPONSOR";
			if($result2 = mysqli_query($db, $sql2)) {
				$users['sponsors'] = mysqli_fetch_array($result2, MYSQL_ASSOC);
				return $users;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
?>

<html lang="en">
<head>
  	<meta charset="utf-8">

  	<title>Login</title>
 
  	<link rel="stylesheet" href="style.css">
  	<link rel='icon' href='log.png' type='image/x-icon'/>
  	<link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro&display=swap" rel="stylesheet">


</head>

<body id="login">

  	<!--Page Content-->
	<div class="container">

  	<div class = "imgcontainer">
    	<img src= "companylogo.png" alt="logo" class="logo" >
  	</div>

  	<div class="wrapper">
  

    	<div class="form">

      		<form action="" method="POST" id="form">
      			<div class="login-box">
          			<h1>Login</h1>
          			<div class="textbox">
            			<input type="text" placeholder="Username" id="username" name="username" required>
          			</div>

          			<div class="textbox">
            			<input type="password" placeholder="Password" id="password" name = "password" required>
          			</div>
          
          			<div class="forgot-password">
            			<h2>
              			<a href="reset.php">Forgot Password</a>
              			<br/>
              			<a href="create.php">Create Account</a>
            			</h2>
          			</div>
    
          			<button class="btn" type="submit">Sign In</button>

      			</div>
      		</form>

    	</div>

  	</div>

	</div>

	<!--<script type="text/javascript" src="login.js"></script>-->

</body>
</html>