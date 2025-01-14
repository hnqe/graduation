<?php include '../includes/config.php';?>

<?php
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
$limit = 5;  // Quantidade de pratos por página
$offset = ($page - 1) * $limit;

$cardapioFiltro = isset($_GET['cardapio']) ? $_GET['cardapio'] : null;
$nomeFiltro = isset($_GET['nome']) ? $_GET['nome'] : null;

$query = "SELECT p.idprato, p.prato, p.foto, c.cardapio, c.idcardapio 
          FROM pratos p 
          INNER JOIN cardapios c ON p.idcardapio = c.idcardapio
          WHERE 1=1";  // Condição para construir o filtro dinamicamente

$queryCount = "SELECT COUNT(*) FROM pratos p 
               INNER JOIN cardapios c ON p.idcardapio = c.idcardapio 
               WHERE 1=1";  // Para contar os pratos filtrados

if ($cardapioFiltro) {
    $query .= " AND p.idcardapio = :cardapio";
    $queryCount .= " AND p.idcardapio = :cardapio";
}

if ($nomeFiltro) {
    $query .= " AND p.prato LIKE :nome";
    $queryCount .= " AND p.prato LIKE :nome";
}

// Obtenha o número total de pratos com o filtro
$totalPratosQuery = $pdo->prepare($queryCount);

if ($cardapioFiltro) {
    $totalPratosQuery->bindValue(':cardapio', $cardapioFiltro, PDO::PARAM_INT);
}
if ($nomeFiltro) {
    $totalPratosQuery->bindValue(':nome', '%' . $nomeFiltro . '%', PDO::PARAM_STR);
}
$totalPratosQuery->execute();
$totalPratos = $totalPratosQuery->fetchColumn();
$totalPages = ceil($totalPratos / $limit);

// Consulta paginada
$query .= " LIMIT :limit OFFSET :offset";
$lista = $pdo->prepare($query);

if ($cardapioFiltro) {
    $lista->bindValue(':cardapio', $cardapioFiltro, PDO::PARAM_INT);
}
if ($nomeFiltro) {
    $lista->bindValue(':nome', '%' . $nomeFiltro . '%', PDO::PARAM_STR);
}

$lista->bindValue(':limit', $limit, PDO::PARAM_INT);
$lista->bindValue(':offset', $offset, PDO::PARAM_INT);
$lista->execute();
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN - Veganês</title>
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
</head>
<body>
    <?php include '../includes/menu.php';?>

    <div class="container mt-3">
        <!-- Botao Modal Cadastro Inicio -->
        <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Adicionar Prato
        </button>

        <!-- Modal Cadastro -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Prato</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="../controllers/opprato.php?acao=cadastrar" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label class="form-label">Prato</label>
                                <input type="text" class="form-control" placeholder="Digite o nome do Prato" 
                                name="txt_prato" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Selecione o Cardápio</label>
                                <select class="form-select" name="txt_cardapio">
                                    <?php
                                        $sql = $pdo->query("SELECT * FROM cardapios ORDER BY cardapio");

                                        while($linha = $sql->fetch(PDO::FETCH_ASSOC)){

                                    ?>
                                    <option value="<?php echo $linha['idcardapio'];?>"><?php echo $linha['cardapio'];?></option>
                                    <?php } ?>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="formFile" class="form-label">Foto</label>
                                <input class="form-control" type="file" name="file_foto" required>
                                <img class="preview-img" src="#" alt="Pré-visualização" style="display: none; margin-top: 10px; max-width: 100px;" />
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Capturar o status na pgprato.php e exibir a mensagem -->
        <?php if (isset($_GET['status']) && $_GET['status'] == 'success') : ?>
            <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
                <?php echo $_GET['message']; ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <?php endif; ?>

        <?php if (isset($_GET['status']) && $_GET['status'] == 'error') : ?>
            <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                <?php echo $_GET['message']; ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <?php endif; ?>

        <!-- Listagem início -->
        <div class="card-admin">
            <h3 class="text-center">Lista de Pratos</h3>

            <form method="GET" action="pgprato.php">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="nome" placeholder="Buscar por nome" value="<?php echo isset($_GET['nome']) ? $_GET['nome'] : ''; ?>">
                    <select class="form-select" name="cardapio">
                        <option value="">Todos os Cardápios</option>
                        <?php
                            $cardapios = $pdo->query("SELECT * FROM cardapios ORDER BY cardapio");
                            while ($cardapio = $cardapios->fetch(PDO::FETCH_ASSOC)) {
                                echo '<option value="' . $cardapio['idcardapio'] . '"';
                                if (isset($_GET['cardapio']) && $_GET['cardapio'] == $cardapio['idcardapio']) {
                                    echo ' selected';
                                }
                                echo '>' . $cardapio['cardapio'] . '</option>';
                            }
                        ?>
                    </select>
                    <button class="btn btn-outline-secondary" type="submit">Filtrar</button>
                </div>
            </form>

            <table class="table table-striped table-hover mt-3">
                <thead class="table-dark">
                    <tr>
                    <th scope="col">Id#</th>
                    <th scope="col">Prato</th>
                    <th scope="col">Cardápio</th>
                    <th scope="col">Foto</th>
                    <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        // $lista = $pdo->query("SELECT p.idprato, p.prato, p.foto, c.cardapio, c.idcardapio 
                        //                         FROM pratos p INNER JOIN cardapios c 
                        //                         ON p.idcardapio = c.idcardapio");

                        while($linha = $lista->fetch(PDO::FETCH_ASSOC)){
                    ?>

                    <tr>
                        <th scope="row"><?php echo $linha['idprato'];?></th>
                        <td><?php echo $linha['prato'];?></td>
                        <td><?php echo $linha['cardapio'];?></td>
                        <td><img src="../img/<?php echo $linha['foto']; ?>" class="img-fixed-size" alt="Imagem do prato"></td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                            data-bs-target="#ModalEditar<?php echo $linha['idprato'];?>">Editar</button>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                            data-bs-target="#ModalExcluir<?php echo $linha['idprato'];?>">Excluir</button>
                        </td>
                    </tr>
                    
                    <!-- Modal Excluir início -->
                    <div class="modal fade" id="ModalExcluir<?php echo $linha['idprato'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Deseja excluir o Prato?</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>

                            <div class="modal-body text-center">
                                <a href="../controllers/opprato.php?acao=excluir&id=<?php echo $linha['idprato'];?>&foto=<?php echo $linha['foto'];?>" 
                                class="btn btn-danger">Sim</a>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                            </div>
                        </div>
                    </div>
                    </div>
                    <!-- Modal Excluir final-->

                    <!-- Modal Editar início -->
                    <div class="modal fade" id="ModalEditar<?php echo $linha['idprato'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Edição do Prato</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="../controllers/opprato.php?acao=editar&id=<?php echo $linha['idprato'];?>&foto=<?php echo $linha['foto'];?>" method="post" enctype="multipart/form-data">
                                        <div class="mb-3">
                                            <label class="form-label">Prato</label>
                                            <input type="text" class="form-control" value="<?php echo $linha['prato'];?>"
                                            name="txt_prato" required>
                                        </div>

                                        <div class="mb-3">
                                            <label for="formFile" class="form-label">Foto</label>
                                            <input class="form-control" type="file" name="file_foto">
                                            <img class="preview-img" src="#" alt="Pré-visualização" style="display: none; margin-top: 10px; max-width: 100px;" />
                                        </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Salvar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal Editar final -->
                    <?php } ?>
                </tbody>
            </table>
        </div>

        <nav aria-label="Paginação de Pratos">
            <ul class="pagination justify-content-center">
                <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=1<?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $cardapioFiltro ? '&cardapio='.$cardapioFiltro : ''; ?>">Primeira</a>
                </li>
                <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $page - 1; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $cardapioFiltro ? '&cardapio='.$cardapioFiltro : ''; ?>">Anterior</a>
                </li>
                <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $page + 1; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $cardapioFiltro ? '&cardapio='.$cardapioFiltro : ''; ?>">Próxima</a>
                </li>
                <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $totalPages; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $cardapioFiltro ? '&cardapio='.$cardapioFiltro : ''; ?>">Última</a>
                </li>
            </ul>
        </nav>
        <!-- Listagem final -->
    </div>

    <?php include '../includes/footer.php'; ?>
</body>
</html>