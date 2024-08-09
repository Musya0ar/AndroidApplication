<?php
// Include the connection file
include 'conn.php';

// Initialize the response array
$data['result'] = array('response' => array(
    'code' => 401,
    'status' => 'unauthorized',
    'message' => 'Authentication Error'
));

// SQL query to select data from items table
$data['sql'] = "SELECT * FROM items";
$data['query'] = mysqli_query($data['conn'], $data['sql']);

// Initialize an array to hold user data
$data['user'] = array();

// Fetch the data
while ($data['row'] = mysqli_fetch_assoc($data['query'])) {
    $data['user'][] = $data['row'];
}

// Check if user data exists and update the response accordingly
if (!empty($data['user'])) {
    $data['result']['response'] = array(
        'code' => 200,
        'status' => 'success',
        'message' => 'Data Exist',
        'data' => $data['user']
    );
} else {
    $data['result']['response'] = array(
        'code' => 400,
        'status' => 'failed',
        'message' => 'Empty Data'
    );
}
$data['result'] = array('response' => array(
    'code' => 401,
    'status' => 'unauthorized',
    'message' => 'Authentication Error'
));

// SQL query to select data from items table
$data['sql'] = "SELECT * FROM items";
$data['query'] = mysqli_query($data['conn'], $data['sql']);

// Initialize an array to hold user data
$data['user'] = array();

// Fetch the data
while ($data['row'] = mysqli_fetch_assoc($data['query'])) {
    $data['user'][] = $data['row'];
}

// Check if user data exists and update the response accordingly
if (!empty($data['user'])) {
    $data['result']['response'] = array(
        'code' => 200,
        'status' => 'success',
        'message' => 'Data Exist',
        'data' => $data['user']
    );
} else {
    $data['result']['response'] = array(
        'code' => 400,
        'status' => 'failed',
        'message' => 'Empty Data'
    );
}


// Output the JSON encoded result
echo json_encode($data['result']);
?>
