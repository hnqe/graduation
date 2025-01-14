<?php
include '../includes/config.php';

$descricao = $_POST['txt_descricao'];
$localizacao = $_POST['txt_localizacao'];
$telefone = $_POST['txt_telefone'];
$id = 1;

$up = $pdo->prepare("UPDATE sobre SET descricao = ?, localizacao = ?, telefone = ? WHERE idsobre = ?");

$up->bindValue(1, $descricao);
$up->bindValue(2, $localizacao);
$up->bindValue(3, $telefone);
$up->bindValue(4, $id);
$up->execute();

header('Location:../pages/pgsobre.php');
?>