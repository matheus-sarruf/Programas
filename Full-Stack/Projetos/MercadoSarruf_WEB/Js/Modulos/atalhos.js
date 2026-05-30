// Gerenciamento de atalhos de teclado

export class GerenciadorAtalhos {
    constructor(audioManager, uiManager, zoomManager) {
        this.audioManager = audioManager;
        this.uiManager = uiManager;
        this.zoomManager = zoomManager;
        this.sequenciaSAPO = '';
        this.timeoutSAPO = null;
    }
    
    init() {
        document.addEventListener('keydown', (e) => this.gerenciarAtalhos(e));
    }
    
    gerenciarAtalhos(e) {
        // Sequência secreta SAPO
        if (e.ctrlKey && e.altKey) {
            const tecla = e.key.toLowerCase();
            
            if (['s', 'a', 'p', 'o'].includes(tecla)) {
                e.preventDefault();
                this.sequenciaSAPO += tecla;
                clearTimeout(this.timeoutSAPO);
                this.timeoutSAPO = setTimeout(() => { this.sequenciaSAPO = ''; }, 2000);
                
                if (this.sequenciaSAPO === 'sapo') {
                    this._executarFuncaoSAPO();
                    this.sequenciaSAPO = '';
                    return;
                }
                
                if (this.sequenciaSAPO.length > 4 || !'sapo'.startsWith(this.sequenciaSAPO)) {
                    this.sequenciaSAPO = '';
                }
                return;
            }
        }
        
        // Atalhos com Ctrl + Alt
        if (e.ctrlKey && e.altKey) {
            e.preventDefault();
            const tecla = e.key.toLowerCase();
            
            const atalhos = {
                'x': () => this.uiManager?.toggleInfo(),
                'n': () => this.audioManager?.proximaMusica(),
                'z': () => this.audioManager?.togglePausar(),
                'd': () => this.audioManager?.diminuirVolume(),
                'f': () => this.audioManager?.aumentarVolume()
            };
            
            if (atalhos[tecla]) {
                atalhos[tecla]();
            }
        }
        
        // Atalhos de zoom com Ctrl
        if (e.ctrlKey && !e.altKey) {
            if (e.key === '+' || e.key === '=') {
                e.preventDefault();
                this.zoomManager?.zoomIn();
            } else if (e.key === '-') {
                e.preventDefault();
                this.zoomManager?.zoomOut();
            } else if (e.key === '0') {
                e.preventDefault();
                this.zoomManager?.resetZoom();
            }
        }
    }
    
    _executarFuncaoSAPO() {
        console.log('🐸 Modo SAPO ativado!');
        // Efeito especial secreto
        document.body.style.backgroundImage = 'url("")';
        
        const intervalo = setInterval(() => {
            if (this.audioManager?.audioAtual) {
                this.audioManager.audioAtual.currentTime = 0;
                this.audioManager.audioAtual.play().catch(() => {});
            }
        }, 10000);
        
        // Limpar após 30 segundos
        setTimeout(() => clearInterval(intervalo), 30000);
    }
}