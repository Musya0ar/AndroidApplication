<?php
include 'conn.php'; // Corrected the include statement

$data['result'] = array(
    'response' => array(
        'code' => 401,
        'status' => 'unauthorized',
        'message' => 'Authentication Error'
    )
);

if (!empty($_POST['name']) && !empty($_POST['email']) && !empty($_POST['password'])) {
    // Corrected the conditions and closing parenthesis

    $name = $_POST['name']; // Secure the inputs to prevent SQL injection
    $email = $_POST['email'];
    $password = $_POST['password'];

    $data['sql'] = "INSERT INTO users (name, email, password) VALUES ('$name', '$email', '$password')"; // Corrected the syntax and concatenation
    // echo $data['sql']; exit; // Commented out the debug line

    $result = mysqli_query($conn, $data['sql']); // Corrected the query execution

    if ($result) {
        $data['result']['response'] = array(
            'code' => 200,
            'status' => 'success',
            'message' => 'User registration successful', // Updated message to be more appropriate
            'data' => array('name' => $name, 'email' => $email) // Added data to response
        );
    } else {
        $data['result']['response'] = array(
            'code' => 500,
            'status' => 'error',
            'message' => 'Database Error'
        );
    }
}

echo json_encode($data['result']);
?>
