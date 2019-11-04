<?php
    $username = $_SESSION['username'];
    $sql = "SELECT NAME, EMAIL FROM DRIVER WHERE USERNAME = '$username'";
    
    if ($result = mysqli_query($db, $sql)) {
        $userInfo = mysqli_fetch_array($result, MYSQL_ASSOC);
        $name = $result['NAME'];
        $email = $result['EMAIL'];    
    } 

    $sql2 = "SELECT POINT_TOTAL WHERE DRIVER = '$username'";

    if($result2 = mysqli_query($db, $sql2)) {
        $pointsArray = mysqli_fetch_array($result2, MYSQL_ASSOC);
    }
?>