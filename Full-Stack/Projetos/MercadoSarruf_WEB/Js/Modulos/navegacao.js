// Gerenciamento de navegação e iframe

export class GerenciadorNavegacao {
    constructor(botoesNav, iframe) {
        this.botoesNav = botoesNav;
        this.iframe = iframe;
        this.currentUrl = null;
    }
    
    init() {
        this.botoesNav.forEach(btn => {
            btn.addEventListener('click', (e) => this.mudarConteudo(e, btn));
        });
        
        // Ativar primeiro botão por padrão
        const primeiroBtn = this.botoesNav[0];
        if (primeiroBtn) {
            primeiroBtn.classList.add('ativado');
            const url = primeiroBtn.getAttribute('data-href');
            if (url) {
                this.iframe.src = url;
                this.currentUrl = url;
            }
        }
    }
    
    mudarConteudo(event, botao) {
        event.preventDefault();
        
        this.botoesNav.forEach(btn => btn.classList.remove('ativado'));
        botao.classList.add('ativado');
        
        const url = botao.getAttribute('data-href');
        if (url) {
            this.iframe.src = url;
            this.currentUrl = url;
            
            // Ajustar scroll do iframe
            this.iframe.scrolling = url === 'Inicio/inicio.html' ? 'auto' : 'no';
            
            // Scroll suave para o iframe
            this.iframe.scrollIntoView({
                behavior: 'smooth',
                block: 'center'
            });
        }
    }
    
    getCurrentUrl() {
        return this.currentUrl;
    }
}