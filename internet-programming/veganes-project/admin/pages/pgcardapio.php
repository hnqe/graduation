<?php include '../includes/config.php';?>

<?php
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
$limit = 5;  // Limite de cardápios por página
$offset = ($page - 1) * $limit;

// Filtro por nome e por cardápios com/sem pratos
$nomeFiltro = isset($_GET['nome']) ? $_GET['nome'] : null;
$tipoFiltro = isset($_GET['tipo']) ? $_GET['tipo'] : null;

$query = "SELECT * FROM cardapios WHERE 1=1";
$queryCount = "SELECT COUNT(*) FROM cardapios WHERE 1=1";

if ($nomeFiltro) {
    $query .= " AND cardapio LIKE :nome";
    $queryCount .= " AND cardapio LIKE :nome";
}

if ($tipoFiltro === 'com_pratos') {
    // Filtro para mostrar cardápios que têm pratos associados
    $query .= " AND EXISTS (SELECT 1 FROM pratos WHERE pratos.idcardapio = cardapios.idcardapio)";
    $queryCount .= " AND EXISTS (SELECT 1 FROM pratos WHERE pratos.idcardapio = cardapios.idcardapio)";
} elseif ($tipoFiltro === 'sem_pratos') {
    // Filtro para mostrar cardápios que não têm pratos associados
    $query .= " AND NOT EXISTS (SELECT 1 FROM pratos WHERE pratos.idcardapio = cardapios.idcardapio)";
    $queryCount .= " AND NOT EXISTS (SELECT 1 FROM pratos WHERE pratos.idcardapio = cardapios.idcardapio)";
}

// Obter o total de cardápios com os filtros
$totalCardapiosQuery = $pdo->prepare($queryCount);
if ($nomeFiltro) {
    $totalCardapiosQuery->bindValue(':nome', '%' . $nomeFiltro . '%', PDO::PARAM_STR);
}
$totalCardapiosQuery->execute();
$totalCardapios = $totalCardapiosQuery->fetchColumn();
$totalPages = ceil($totalCardapios / $limit);

// Consulta com paginação
$query .= " LIMIT :limit OFFSET :offset";
$lista = $pdo->prepare($query);
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
        Adicionar Cardápio
        </button>

        <!-- Modal Cadastro -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Cardápio</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="../controllers/opcardapio.php?acao=cadastrar" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label class="form-label">Cardápio</label>
                                <input type="text" class="form-control" placeholder="Digite o nome do cardápio" 
                                name="txt_cardapio" required>
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

        <!-- Capturar o status na pgcardapio.php e exibir a mensagem -->
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
            <h3 class="text-center">Lista de Cardápios</h3>

            <form method="GET" action="pgcardapio.php">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="nome" placeholder="Buscar por nome" value="<?php echo isset($_GET['nome']) ? $_GET['nome'] : ''; ?>">
                    <select class="form-select" name="tipo">
                        <option value="">Todos</option>
                        <option value="com_pratos" <?php if(isset($_GET['tipo']) && $_GET['tipo'] == 'com_pratos') echo 'selected'; ?>>Com Pratos</option>
                        <option value="sem_pratos" <?php if(isset($_GET['tipo']) && $_GET['tipo'] == 'sem_pratos') echo 'selected'; ?>>Sem Pratos</option>
                    </select>
                    <button class="btn btn-outline-secondary" type="submit">Filtrar</button>
                </div>
            </form>

            <table class="table table-striped table-hover mt-3">
                <thead class="table-dark">
                    <tr>
                    <th scope="col">Id#</th>
                    <th scope="col">Cardápio</th>
                    <th scope="col">Foto</th>
                    <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        // $lista = $pdo->query("SELECT * FROM cardapios");
                        while($linha = $lista->fetch(PDO::FETCH_ASSOC)){
                    ?>

                    <tr>
                        <th scope="row"><?php echo $linha['idcardapio'];?></th>
                        <td><?php echo $linha['cardapio'];?></td>
                        <td><img src="../img/<?php echo $linha['foto'];?>" class="img-fixed-size" alt="Imagem do cardápio"></td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                            data-bs-target="#ModalEditar<?php echo $linha['idcardapio'];?>">Editar</button>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                            data-bs-target="#ModalExcluir<?php echo $linha['idcardapio'];?>">Excluir</button>
                        </td>
                    </tr>
                    
                    <!-- Modal Excluir início -->
                    <div class="modal fade" id="ModalExcluir<?php echo $linha['idcardapio'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Deseja excluir o cardápio?</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>

                            <div class="modal-body text-center">
                                <a href="../controllers/opcardapio.php?acao=excluir&id=<?php echo $linha['idcardapio'];?>&foto=<?php echo $linha['foto'];?>" 
                                class="btn btn-danger">Sim</a>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                            </div>
                        </div>
                    </div>
                    </div>
                    <!-- Modal Excluir final-->

                    <!-- Modal Editar início -->
                    <div class="modal fade" id="ModalEditar<?php echo $linha['idcardapio'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Edição de Cardápio</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="../controllers/opcardapio.php?acao=editar&id=<?php echo $linha['idcardapio'];?>&foto=<?php echo $linha['foto'];?>" method="post" enctype="multipart/form-data">
                                        <div class="mb-3">
                                            <label class="form-label">Cardápio</label>
                                            <input type="text" class="form-control" value="<?php echo $linha['cardapio'];?>"
                                            name="txt_cardapio" required>
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

        <nav aria-label="Paginação de Cardápios">
            <ul class="pagination justify-content-center">
                <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=1<?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $tipoFiltro ? '&tipo='.$tipoFiltro : ''; ?>">Primeira</a>
                </li>
                <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $page - 1; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $tipoFiltro ? '&tipo='.$tipoFiltro : ''; ?>">Anterior</a>
                </li>
                <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $page + 1; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $tipoFiltro ? '&tipo='.$tipoFiltro : ''; ?>">Próxima</a>
                </li>
                <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                    <a class="page-link" href="?page=<?php echo $totalPages; ?><?php echo $nomeFiltro ? '&nome='.$nomeFiltro : ''; ?><?php echo $tipoFiltro ? '&tipo='.$tipoFiltro : ''; ?>">Última</a>
                </li>
            </ul>
        </nav>
        <!-- Listagem final -->
    </div>

    <?php include '../includes/footer.php'; ?>
</body>
</html>