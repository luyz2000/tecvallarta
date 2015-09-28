<?php

require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['email']) && isset($_POST['password'])) {

        // Request type is check Login
        $email = $_POST['email'];
        $password = $_POST['password'];

        // check for user
        $user = $db->getUserByEmailAndPassword($email, $password);
        if ($user != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["uid"] = $user["uid"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];

            echo json_encode($response);
            
            
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect email or password!";
            echo json_encode($response);
        }

    }
?>

