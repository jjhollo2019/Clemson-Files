<?php
$username = $_SESSION['username'];
    $sql = "SELECT NAME, EMAIL FROM ADMINISTRATIVE WHERE USERNAME = '$username'";
    
    if ($result = mysqli_query($db, $sql)) {
        $userInfo = mysqli_fetch_array($result, MYSQL_ASSOC);
        $name = $result['NAME'];
        $email = $result['EMAIL'];    
    }

?>