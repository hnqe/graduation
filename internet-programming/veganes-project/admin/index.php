<?php 
    include 'includes/config.php';

    // Buscar os dados da tabela 'sobre'
    $sobre = $pdo->query("SELECT * FROM sobre WHERE idsobre = 1")->fetch(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Veganês</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
    <!-- Cabeçalho -->
    <header class="bg-light py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <h1><img src="https://th.bing.com/th/id/R.ad352646f5b86037b5b36136acb44c03?rik=ZeMeeDwLBAOurQ&pid=ImgRaw&r=0" height="100px" width="100px" alt="Logo Restaurante Veganês"></h1>
            <nav>
                <ul class="nav">
                    <li class="nav-item"><a class="nav-link text-dark" href="index.php">Home</a></li>
                    <li class="nav-item"><a class="nav-link text-dark" href="pages/pgbanner.php">Banner</a></li>
                    <li class="nav-item"><a class="nav-link text-dark" href="pages/pgcardapio.php">Cardápio</a></li>
                    <li class="nav-item"><a class="nav-link text-dark" href="pages/pgprato.php">Pratos</a></li>
                    <li class="nav-item"><a class="nav-link text-dark" href="pages/pgsobre.php">Sobre</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- Banner -->
    <div class="banner">
        <div class="banner-overlay">
            <h1>Bem-vindo ao Veganês Admin</h1>
        </div>
    </div>

    <!-- Conteúdo principal -->
    <main class="container principal mt-5">
        <section class="text-center">
            <h2 class="display-4">Administração - Veganês</h2>
            <p class="lead">Aqui você pode gerenciar os banners, cardápios, pratos e informações do restaurante Veganês.</p>
        </section>

        <div class="row text-center">
            <div class="col-md-4">
                <div class="card card-admin">
                    <div class="card-body">
                        <h3 class="card-title">Banners</h3>
                        <p class="card-text">Gerencie as imagens que aparecem na página principal do restaurante.</p>
                        <a href="pages/pgbanner.php" class="btn btn-primary">Ir para Banners</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-admin">
                    <div class="card-body">
                        <h3 class="card-title">Cardápios</h3>
                        <p class="card-text">Edite os cardápios disponíveis para os clientes.</p>
                        <a href="pages/pgcardapio.php" class="btn btn-primary">Ir para Cardápios</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-admin">
                    <div class="card-body">
                        <h3 class="card-title">Pratos</h3>
                        <p class="card-text">Adicione ou remova pratos do cardápio.</p>
                        <a href="pages/pgprato.php" class="btn btn-primary">Ir para Pratos</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <section class="mt-3 py-5">
        <div class="container">
            <!-- Título da Seção -->
            <div class="text-center mb-3">
                <h2 class="display-4" style="color: #C78C19;">Sobre o Veganês</h2>
                <p class="lead text-white">Nosso compromisso com a culinária vegana vai além do prato. Aqui, cada refeição é uma experiência, 
                e cada ingrediente é escolhido com carinho para proporcionar o melhor sabor sem crueldade animal.</p>
            </div>

            <!-- Conteúdo da Seção -->
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <!-- Card de Descrição e Localização -->
                    <div class="card shadow-lg border-0">
                        <div class="card-body p-4">
                            <h4 class="card-title text-center" style="color: #C78C19;">Descrição</h4>
                            <p class="card-text text-center"><?php echo $sobre['descricao']; ?></p>

                            <hr>

                            <h4 class="card-title text-center" style="color: #C78C19;">Localização</h4>
                            <p class="text-center">
                                <i class="fas fa-map-marker-alt"></i> 
                                <?php echo $sobre['localizacao']; ?>
                            </p>

                            <hr>

                            <h4 class="card-title text-center" style="color: #C78C19;">Contato</h4>
                            <p class="text-center">
                                <i class="fas fa-phone-alt"></i> 
                                <?php echo $sobre['telefone']; ?>
                            </p>
                        </div>
                    </div>
                </div>

                <!-- Mapa de Localização -->
                <div class="col-md-6 d-flex justify-content-center align-items-center">
                    <iframe 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3656.449840047712!2d-46.6323319!3d-23.588194799999997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5a2b2ed7f3a1%3A0xab35da2f5ca62674!2sAlura%20-%20Escola%20Online%20de%20Tecnologia!5e0!3m2!1spt-BR!2sbr!4v1711127837148!5m2!1spt-BR!2sbr" 
                        width="100%" 
                        height="300" 
                        style="border:0;" 
                        allowfullscreen="" 
                        loading="lazy">
                    </iframe>
                </div>
            </div>
        </div>
    </section>

    <?php include 'includes/footer.php'; ?>
</body>
</html>
