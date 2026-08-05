const links = document.querySelectorAll('nav a'); // Seleciona todos os links de navegação
const iframe = document.getElementById('janela_centro'); // Certifique-se de que o ID esteja correto

iframe.onload = function () { // Executa quando o conteúdo do iframe é carregado
    const currentUrl = iframe.contentWindow.location.href; // Obtém a URL do iframe

    links.forEach(link => {
        // Remove a classe 'active' de todos os links
        link.classList.remove('ativado');

        // Adiciona a classe 'active' ao link correspondente
        if (link.href === currentUrl) {
            link.classList.add('ativado');
        }
    });
};