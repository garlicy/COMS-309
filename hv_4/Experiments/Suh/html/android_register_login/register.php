<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect.php';

    $sql = "INSERT INTO users_table(name, email, password) VALUES('$name','$email','$password')";

    if(mysql_query($conn,$sql)){
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysql_close($conn);
    } else{
        $result["success"] = "0";
        $result["message"] = "errr";

        echo json_encode($result);
        mysql_close($conn);
    }
}
?>