<?php
include '../includes/config.php';

if(!empty($_POST['txt_cardapio'])){
    $cardapio = $_POST['txt_cardapio'];
    $foto = $_FILES['file_foto']['name'];
    $foto = str_replace(" ", "", $foto);
    $foto_temp = $_FILES['file_foto']['tmp_name'];
    $destino = "../img/".$foto;
}

if(isset($_GET['acao']) && $_GET['acao'] == "cadastrar"){
    if(move_uploaded_file($foto_temp, $destino)){
        $insert = $pdo->prepare("INSERT INTO cardapios (cardapio, foto) VALUES (?, ?)");
        $insert->bindValue(1, $cardapio);
        $insert->bindValue(2, $foto);
        $insert->execute();

        header("Location:../pages/pgcardapio.php?status=success&message=Cardápio cadastrado com sucesso");
    }
}

if(isset($_GET['acao']) && $_GET['acao'] == "excluir"){
    $id = $_GET['id'];
    $foto = $_GET['foto'];

    // Verificar se existem pratos associados a este cardápio
    $checkPratos = $pdo->prepare("SELECT COUNT(*) FROM pratos WHERE idcardapio = ?");
    $checkPratos->bindValue(1, $id);
    $checkPratos->execute();
    $pratosAssociados = $checkPratos->fetchColumn();

    if($pratosAssociados > 0) {
        // Existem pratos associados, redirecionar com mensagem de erro
        header("Location:../pages/pgcardapio.php?status=error&message=Não é possível excluir o cardápio pois há pratos associados.");
    } else {
        // Não existem pratos associados, pode excluir o cardápio
        $del = $pdo->prepare("DELETE FROM cardapios WHERE idcardapio = ?");
        $del->bindValue(1, $id);
        $del->execute();
        unlink('../img/'.$foto);
        header('Location:../pages/pgcardapio.php?status=success&message=Cardápio excluído com sucesso');
    }
}

if(isset($_GET['acao']) && $_GET['acao'] == "editar"){
    $id = $_GET['id'];
    $fotodb = $_GET['foto'];

    if($_FILES['file_foto']['size'] == 0){
        $edit = $pdo->prepare("UPDATE cardapios SET cardapio = ? WHERE idcardapio = ?");
        $edit->bindValue(1, $cardapio);
        $edit->bindValue(2, $id);
        $edit->execute();
        header("Location:../pages/pgcardapio.php?status=success&message=Cardápio editado com sucesso");
    }else{
        unlink('../img/'.$fotodb);
        if(move_uploaded_file($foto_temp, $destino)){
            $edit = $pdo->prepare("UPDATE cardapios SET cardapio = ?, foto = ? WHERE idcardapio = ?");
            $edit->bindValue(1, $cardapio);
            $edit->bindValue(2, $foto);
            $edit->bindValue(3, $id);
            $edit->execute();
    
            header("Location:../pages/pgcardapio.php?status=success&message=Cardápio editado com sucesso");
        }
    }
}
?>