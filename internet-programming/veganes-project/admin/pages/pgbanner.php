<?php
include '../includes/config.php';

// Definindo quantos banners por página
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
$limit = 5;  // Defina o número de banners por página
$offset = ($page - 1) * $limit;

$idFiltro = isset($_GET['id']) ? $_GET['id'] : null;

if ($idFiltro) {
    // Contar os banners filtrados por ID
    $totalBannersQuery = $pdo->prepare("SELECT COUNT(*) FROM banner WHERE idbanner = :id");
    $totalBannersQuery->bindValue(':id', $idFiltro, PDO::PARAM_INT);
    $totalBannersQuery->execute();
    $totalBanners = $totalBannersQuery->fetchColumn();

    // Filtra por id específico com paginação
    $lista = $pdo->prepare("SELECT * FROM banner WHERE idbanner = :id LIMIT :limit OFFSET :offset");
    $lista->bindValue(':id', $idFiltro, PDO::PARAM_INT);
} else {
    // Contar todos os banners
    $totalBanners = $pdo->query("SELECT COUNT(*) FROM banner")->fetchColumn();

    // Listar todos os banners com paginação
    $lista = $pdo->prepare("SELECT * FROM banner LIMIT :limit OFFSET :offset");
}

$lista->bindValue(':limit', $limit, PDO::PARAM_INT);
$lista->bindValue(':offset', $offset, PDO::PARAM_INT);
$lista->execute();

$totalPages = ceil($totalBanners / $limit);
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

    <div class="container mt-5">
        <!-- Botão Modal Cadastro -->
        <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Adicionar Banner
        </button>

        <!-- Modal Cadastro -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cadastro de Banner</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="../controllers/opbanner.php?acao=cadastrar" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="formFile" class="form-label">Foto</label>
                                <input class="form-control" type="file" name="file_foto" required>
                                <img class="preview-img" src="#" alt="Pré-visualização" style="display: none; margin-top: 10px; max-width: 100px;" />
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Capturar o status na pgbanner.php e exibir a mensagem -->
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

        <!-- Listagem de Banners -->
        <div class="card-admin">
            <h3 class="text-center">Lista de Banners</h3>

            <form method="GET" action="pgbanner.php">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="id" placeholder="Filtrar por ID" value="<?php echo isset($_GET['id']) ? $_GET['id'] : ''; ?>">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Filtrar</button>
                </div>
            </form>

            <table class="table table-striped table-hover mt-3">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Id#</th>
                        <th scope="col">Banner</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                        // $lista = $pdo->query("SELECT * FROM banner");
                        while($linha = $lista->fetch(PDO::FETCH_ASSOC)){
                    ?>

                    <tr>
                        <th scope="row"><?php echo $linha['idbanner'];?></th>
                        <td><img src="../img/<?php echo $linha['foto'];?>" class="img-fixed-size" alt="Imagem do banner"></td>
                        <td>
                            <!-- Botões de ação -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                            data-bs-target="#ModalEditar<?php echo $linha['idbanner'];?>">Editar</button>

                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                            data-bs-target="#ModalExcluir<?php echo $linha['idbanner'];?>">Excluir</button>
                        </td>
                    </tr>

                    <!-- Modal Excluir -->
                    <div class="modal fade" id="ModalExcluir<?php echo $linha['idbanner'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Deseja excluir o Banner?</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body text-center">
                                    <a href="../controllers/opbanner.php?acao=excluir&id=<?php echo $linha['idbanner'];?>&foto=<?php echo $linha['foto'];?>" 
                                    class="btn btn-danger">Sim</a>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Modal Editar -->
                    <div class="modal fade" id="ModalEditar<?php echo $linha['idbanner'];?>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Edição de Banner</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="../controllers/../controllers/opbanner.php?acao=editar&id=<?php echo $linha['idbanner'];?>&foto=<?php echo $linha['foto'];?>" method="post" enctype="multipart/form-data">
                                        <div class="mb-3">
                                            <label for="formFile" class="form-label">Foto</label>
                                            <input class="form-control" type="file" name="file_foto">
                                            <img class="preview-img" src="#" alt="Pré-visualização" style="display: none; margin-top: 10px; max-width: 100px;" />
                                        </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Salvar</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <?php } ?>
                </tbody>
            </table>

            <nav aria-label="Paginação de Banners">
                <ul class="pagination justify-content-center">
                    <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                        <a class="page-link" href="?page=1<?php echo $idFiltro ? '&id='.$idFiltro : ''; ?>">Primeira</a>
                    </li>
                    <li class="page-item <?php if($page <= 1) { echo 'disabled'; } ?>">
                        <a class="page-link" href="?page=<?php echo $page - 1; ?><?php echo $idFiltro ? '&id='.$idFiltro : ''; ?>">Anterior</a>
                    </li>
                    <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                        <a class="page-link" href="?page=<?php echo $page + 1; ?><?php echo $idFiltro ? '&id='.$idFiltro : ''; ?>">Próxima</a>
                    </li>
                    <li class="page-item <?php if($page >= $totalPages) { echo 'disabled'; } ?>">
                        <a class="page-link" href="?page=<?php echo $totalPages; ?><?php echo $idFiltro ? '&id='.$idFiltro : ''; ?>">Última</a>
                    </li>
                </ul>
            </nav>
            <!-- Listagem final -->
        </div>
    </div>

    <?php include '../includes/footer.php'; ?>
</body>
</html>
