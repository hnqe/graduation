<?php
include '../includes/config.php';

if(!empty($_POST['txt_prato'])){
    $prato = $_POST['txt_prato'];
    $cardapio = $_POST['txt_cardapio'];
    $foto = $_FILES['file_foto']['name'];
    $foto = str_replace(" ", "", $foto);
    $foto_temp = $_FILES['file_foto']['tmp_name'];
    $destino = "../img/".$foto;
}

if(isset($_GET['acao']) && $_GET['acao'] == "cadastrar"){
    if(move_uploaded_file($foto_temp, $destino)){
        $insert = $pdo->prepare("INSERT INTO pratos (idcardapio, prato, foto) VALUES (?, ?, ?)");
        $insert->bindValue(1, $cardapio);
        $insert->bindValue(2, $prato);
        $insert->bindValue(3, $foto);
        $insert->execute();

        header("Location:../pages/pgprato.php?status=success&message=Prato cadastrado com sucesso");
    }
}

if(isset($_GET['acao']) && $_GET['acao'] == "excluir"){
    $id = $_GET['id'];
    $foto = $_GET['foto'];

    $del = $pdo->prepare("DELETE FROM pratos WHERE idprato = ?");
    $del->bindValue(1, $id);
    $del->execute();
    unlink('../img/'.$foto);
    header("Location:../pages/pgprato.php?status=success&message=Prato excluído com sucesso");
}

if(isset($_GET['acao']) && $_GET['acao'] == "editar"){
    $id = $_GET['id'];
    $fotodb = $_GET['foto'];

    if($_FILES['file_foto']['size'] == 0){
        $edit = $pdo->prepare("UPDATE pratos SET prato = ? WHERE idprato = ?");
        $edit->bindValue(1, $prato);
        $edit->bindValue(2, $id);
        $edit->execute();
        header("Location:../pages/pgprato.php?status=success&message=Prato editado com sucesso");
    }else{
        unlink('../img/'.$fotodb);
        if(move_uploaded_file($foto_temp, $destino)){
            $edit = $pdo->prepare("UPDATE pratos SET prato = ?, foto = ? WHERE idprato = ?");
            $edit->bindValue(1, $prato);
            $edit->bindValue(2, $foto);
            $edit->bindValue(3, $id);
            $edit->execute();
    
            header("Location:../pages/pgprato.php?status=success&message=Prato editado com sucesso");
        }
    }
}
?>