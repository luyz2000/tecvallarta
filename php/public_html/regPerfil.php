<?php

require_once 'include/db_function_perfiles.php';
$db = new db_function_perfiles();

// json response array
$response = array("error" => FALSE);

if (isset( $_POST['nombre']) && isset( $_POST['permiso'])) {
 
   // Request type is Register new user
        $nombre = $_POST['nombre'];
        $permiso = $_POST['permiso'];
            // store user
            $perfil = $db->guardarPerfil($nombre, $permiso);
            if ($perfil) {
                // user stored successfully
                $response["success"] = 1;
                $response["perfil"]["nombre"] = $perfil["nombre"];
                $response["perfil"]["permiso"] = $perfil["permiso"];
                echo json_encode($response);
            } else {
                // user failed to store
                $response["error"] = 1;
                $response["error_msg"] = "Error occured in Registartion";
                echo json_encode($response);
            }
}
?>