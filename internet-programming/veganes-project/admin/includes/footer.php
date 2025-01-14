<!-- footer.php -->
<footer class="text-center py-4 mt-5">
    <img src="https://th.bing.com/th/id/R.ad352646f5b86037b5b36136acb44c03?rik=ZeMeeDwLBAOurQ&pid=ImgRaw&r=0" height="100px" width="100px" alt="Logo Restaurante Veganês">
    <p class="mt-3">&copy; 2024 Veganês - Todos os direitos reservados.</p>
</footer>

<script src="../../assets/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/js/jquery-3.7.1.min.js"></script>

<script>
    document.addEventListener('change', function(event) {
        if (event.target && event.target.matches('input[type="file"]')) {
            const input = event.target;
            const [file] = input.files;
            if (file) {
                // Verifique se o campo de visualização está dentro do mesmo modal
                const preview = input.closest('.modal-body').querySelector('.preview-img');
                if (preview) {
                    preview.src = URL.createObjectURL(file);
                    preview.style.display = 'block';
                }
            }
        }
    });
</script>