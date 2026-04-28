// Gerenciamento de cursores personalizados

export class GerenciadorCursor {
    constructor(bodyElement) {
        this.body = bodyElement;
        this.caminhoTudo = null;
        this.caminhoLink = null;
    }
    
    async carregarCursor(dadosCursor) {
        this.caminhoTudo = dadosCursor.caminho_cursor_tudo;
        this.caminhoLink = dadosCursor.caminho_cursor_link;
        
        this.body.style.cursor = `url('${this.caminhoTudo}'), default`;
        
        const elementosInterativos = document.querySelectorAll('a, button, input[type="range"]');
        elementosInterativos.forEach(elemento => {
            elemento.style.cursor = `url('${this.caminhoLink}'), pointer`;
        });
    }
    
    getCaminhoTudo() {
        return this.caminhoTudo;
    }
    
    getCaminhoLink() {
        return this.caminhoLink;
    }
}