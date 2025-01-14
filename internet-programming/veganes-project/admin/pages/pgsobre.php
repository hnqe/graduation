<?php include '../includes/config.php'; ?>
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

    <?php 
        // Recupera os dados atuais da tabela 'sobre'
        $up = $pdo->query("SELECT * FROM sobre");
        $linha = $up->fetch(PDO::FETCH_ASSOC);
    ?>

    <div class="container mt-5">
        <h3 class="text-center mb-5">Atualizando Sobre da Empresa</h3>
        <div class="row">
            <!-- Formulário de Edição -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3>Formulário - Sobre a Empresa</h3>
                    </div>
                    <div class="card-body">
                        <form action="../controllers/opsobre.php" method="post">
                            <!-- Descrição -->
                            <div class="mb-3">
                                <label class="form-label">Descrição</label>
                                <textarea class="form-control" rows="4" name="txt_descricao" 
                                id="descricao"><?php echo $linha['descricao'];?></textarea>
                            </div>
                            
                            <!-- Localização -->
                            <div class="mb-3">
                                <label class="form-label">Localização</label>
                                <textarea class="form-control" rows="3" name="txt_localizacao" 
                                id="localizacao"><?php echo $linha['localizacao'];?></textarea>
                            </div>
                            
                            <!-- Telefone -->
                            <div class="mb-3">
                                <label class="form-label">Telefone</label>
                                <input type="text" class="form-control" name="txt_telefone" id="telefone" 
                                value="<?php echo $linha['telefone'];?>">
                            </div>
                            
                            <div class="mb-3">
                                <button type="submit" class="btn btn-primary px-5">Salvar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Pré-visualização ao vivo -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3>Pré-visualização</h3>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Descrição</h5>
                        <p id="preview-descricao"><?php echo $linha['descricao']; ?></p>

                        <h5 class="card-title">Localização</h5>
                        <p id="preview-localizacao"><?php echo $linha['localizacao']; ?></p>

                        <h5 class="card-title">Telefone</h5>
                        <p id="preview-telefone"><?php echo $linha['telefone']; ?></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <?php include '../includes/footer.php'; ?>

    <script>
        // Funções para atualizar a pré-visualização em tempo real
        document.getElementById('descricao').addEventListener('input', function() {
            document.getElementById('preview-descricao').innerText = this.value;
        });

        document.getElementById('localizacao').addEventListener('input', function() {
            document.getElementById('preview-localizacao').innerText = this.value;
        });

        document.getElementById('telefone').addEventListener('input', function() {
            document.getElementById('preview-telefone').innerText = this.value;
        });
    </script>
</body>
</html>
