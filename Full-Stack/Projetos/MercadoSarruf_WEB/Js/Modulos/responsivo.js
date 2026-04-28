// Gerenciamento de responsividade e zoom

import { CONFIG } from '../Configuracoes/constantes.js';
import { ServicoArmazenamento } from '../Servicos/armazenamento.js';

export class GerenciadorResponsivo {
    constructor(bodyElement, headerElement, footerElement) {
        this.body = bodyElement;
        this.header = headerElement;
        this.footer = footerElement;
        this.currentZoom = ServicoArmazenamento.get('currentZoom', CONFIG.ZOOM.DEFAULT);
        this.width_tela = document.documentElement.clientWidth;
        this.margin_cima = 74;
    }
    
    init() {
        this.verLargura();
        this.aplicarZoom();
        
        window.addEventListener('resize', () => this.onResize());
    }
    
    verLargura() {
        this.width_tela = document.documentElement.clientWidth;
        
        for (const [breakpoint, margin] of Object.entries(CONFIG.RESPONSIVE_BREAKPOINTS)) {
            if (this.width_tela <= parseInt(breakpoint)) {
                this.margin_cima = margin;
                return;
            }
        }
        this.margin_cima = 74;
    }
    
    onResize() {
        this.verLargura();
        
        // Recarregar após redimensionamento para ajustar layouts complexos
        clearTimeout(this.resizeTimeout);
        this.resizeTimeout = setTimeout(() => {
            if (this.body.getAttribute('tema') === 'Tema_Animado_1') {
                // Recriar estrelas se necessário
                window.dispatchEvent(new CustomEvent('recriarestrelas'));
            }
            window.location.reload();
        }, 5000);
    }
    
    aplicarZoom() {
        this.body.style.transform = `scale(${this.currentZoom})`;
        this.body.style.transformOrigin = 'top center';
    }
    
    zoomIn() {
        this.currentZoom = Math.min(this.currentZoom + CONFIG.ZOOM.STEP, CONFIG.ZOOM.MAX);
        this._aplicarZoomEAtualizar();
    }
    
    zoomOut() {
        this.currentZoom = Math.max(this.currentZoom - CONFIG.ZOOM.STEP, CONFIG.ZOOM.MIN);
        this._aplicarZoomEAtualizar();
    }
    
    resetZoom() {
        this.currentZoom = CONFIG.ZOOM.DEFAULT;
        this._aplicarZoomEAtualizar();
    }
    
    _aplicarZoomEAtualizar() {
        this.aplicarZoom();
        ServicoArmazenamento.set('currentZoom', this.currentZoom);
        window.location.reload();
    }
    
    ajustarMarginTemaOpcoes(aberto) {
        if (this.width_tela <= 1150) {
            this.header.style.marginTop = aberto ? '80px' : this.margin_cima + 'px';
        }
    }
    
    ajustarMarginBotoesTema(aberto) {
        if (this.width_tela <= 600) {
            this.footer.style.marginTop = aberto ? '190px' : '45px';
        } else {
            this.footer.style.marginTop = aberto ? '75px' : '45px';
        }
    }
}