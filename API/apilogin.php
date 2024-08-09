<?php
// Include the connection file
include 'conn.php';

// Initialize the response array
$data['result'] = array('response' => array(
    'code' => 401,
    'status' => 'unauthorized',
    'message' => 'Authentication Error'
));

if (!empty($_POST['email']) && !empty($_POST['password'])) {
    $data['email'] = $_POST['email'];
    $data['password'] = $_POST['password'];

    // Use prepared statements to prevent SQL injection
    $stmt = $data['conn']->prepare("SELECT * FROM users WHERE email = ? AND password = ?");
    $stmt->bind_param("ss", $data['email'], $data['password']);
    $stmt->execute();
    $result = $stmt->get_result();
    $data['user'] = $result->fetch_assoc();

    if ($data['user']) {
        $data['result']['response'] = array(
            'code' => 200,
            'status' => 'success',
            'message' => 'Login Success',
            'data' => $data['user']
        );
    } else {
        $data['result']['response'] = array(
            'code' => 400,
            'status' => 'failed',
            'message' => 'Invalid Credentials'
        );
    }

    $stmt->close();
}

// Output the JSON encoded result
echo json_encode($data['result']);
?>
