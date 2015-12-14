<?php
require_once 'include/db_function_perfiles.php';
$db = new db_function_perfiles();

// json response array
$response = array("error" => FALSE);

if (isset( $_POST['id'])) {
 
   // Request type is Register new user
        $id = $_POST['id'];
            // store user
            $perfil = $db->eliminarPerfil($id);
            if ($perfil) {
                // user stored successfully
                $response["success"] = 1;
                $response["perfil"]["id"] = $perfil["id"];
                echo json_encode($response);
            } else {
                // user failed to store
                $response["error"] = 1;
                $response["error_msg"] = "Error occured in Registartion";
                echo json_encode($response);
            }
}
?>