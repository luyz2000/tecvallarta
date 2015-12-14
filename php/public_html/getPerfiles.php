<?php
 
require_once 'include/db_function_perfiles.php';
$db = new db_function_perfiles();
 
 
    $response = array();
    $response["Perfiles"] = array();
 
    // Mysql select query
    $result = mysql_query("SELECT perId, perNombre, perPermiso FROM TBLPERFIL WHERE perExiste=true");
 
    while($row = mysql_fetch_array($result)){
        // temporary array to create single category
        $tmp = array();
        $tmp["id"] = $row["perId"];
		$tmp["nombre"] = $row["perNombre"];
		$tmp["permiso"] = $row["perPermiso"];
 
        // push category to final json array
        array_push($response["Perfiles"], $tmp);
    }
  
    // keeping response header to json
    header('Content-Type: application/json');
 
    // echoing json result
    echo json_encode($response);
 
 ?>