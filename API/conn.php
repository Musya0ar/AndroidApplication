<?php
    $data = array('host'=>'localhost',
    'user'=>'root',
    'pass'=>'',
    'db'=>'test_ppb');
$data['conn'] = mysqli_connect($data['host'],$data['user'],$data['pass'],$data['db']);


if($data['conn']){  
echo 'Connection Success';
}else{
echo 'Connection Failed';}
?>