<?php
	include("config.php");
	if ($_SERVER["REQUEST_METHOD"] == "POST")
	{
		$identification = $_POST['id'];
		$username = $_POST['username'];
		$table = $_POST['table'];

		$sql = "UPDATE " . $table . "SET PASSWORD = '$password' WHERE USERNAME = '$username' AND ID = '$identification'";

		if(mysqli_query($db, $sql)) {
			//TODO add the success redirect, the query will return true if it worked
		} else {
			//failed to insert
		}
	}
?>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">

  <title>Login</title>
 
  <link rel="stylesheet" href="reset.css">
  <link rel='icon' href='log.png' type='image/x-icon'/>
  <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro&display=swap" rel="stylesheet">

</head>

<body id="reset">
  
  <!--Page Content-->
  <div class="container">

    <div class = "imgcontainer">
      <img src= "companylogo.png" alt="logo" class="logo" >
    </div>
  
    <div class="wrapper">
    
  
      <div class="form">
  
        <form>
        <div class="info-box">
            <h1>Forgot Password</h1>
            <div class="textbox">
              <div>User Type:</div>
              <select id="userType">
                  <option value="driver">Driver</option>
                  <option value="sponsor">Sponsor</option>
                  <option value="admin">Admin</option>
              </select>
              <br/>
              
              <div>Username:</div>
              <input class="test" type="text" id="username" required>
              <div>ID #:</div>
              <input class="test2" type="text" id="id" required>
              <div>New Password:</div>
              <input class="test1" type="password" id="password" required>
              
            </div>

            <input class="btn" type="submit" value="Submit" onclick="AcctCreate();">
  
        </div>
        </form>
  
      </div>
  
    </div>
  
  </div>

<script type="text/javascript">
      function AcctCreate(){
      
      window.location = "index.html";
      }
</script>
  
    <!--Footer-->
    <div class="footer">
  
    </div>
    <!--End of Footer-->
  
  
  </body>
  </html>

