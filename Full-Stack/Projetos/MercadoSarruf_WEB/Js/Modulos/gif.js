// Interações do GIF

import { CONFIG } from '../Configuracoes/constantes.js';

export class GerenciadorGif {
    constructor(gifElement, audioManager) {
        this.gif = gifElement;
        this.audioManager = audioManager;
        this.invertida = false;
        this.contagem = 0;
        
        this.estilosGif = {
            caso5: 'sepia(0.3) hue-rotate(160deg) saturate(1.8) brightness(1.05) drop-shadow(0 0 25px rgba(0, 255, 255, 0.4))',
            caso10: {
                backgroundImage: 'url(Gifs/meme.gif)',
                filter: 'none',
                height: '400px',
                animation: 'borda_RGB 5s infinite linear, box-shadow_RGB 5s infinite linear'
            },
            retorno: {
                backgroundImage: 'url(Gifs/nyan-cat.gif)',
                filter: 'sepia(0.3) hue-rotate(160deg) saturate(1.8) brightness(1.05) drop-shadow(0 0 25px rgba(0, 255, 255, 0.4))',
                height: '150px',
                animation: 'none'
            }
        };
    }
    
    init() {
        if (this.gif) {
            this.gif.addEventListener('click', () => this.onClick());
        }
    }
    
    onClick() {
        this.contagem = this.contagem >= CONFIG.GIF.CONTAGEM_MAX ? 0 : this.contagem + 1;
        
        switch (this.contagem) {
            case 5:
                this.gif.style.filter = this.estilosGif.caso5;
                break;
                
            case 10:
                this._ativarEasterEgg();
                return;
                
            case 11:
                this._retornoGif();
                break;
                
            case 15:
                this.gif.style.animation = 'girar-gif 5s infinite linear';
                break;
        }
        
        if (this.contagem !== 10) {
            this._inverterGif();
            this.audioManager?.tocarSomGif('Sons/Efeitos/button_click_01.mp3');
        }
    }
    
    _ativarEasterEgg() {
        if (this.audioManager?.audioAtual) {
            this.audioManager.pausarMusica();
        }
        
        this._aplicarEstilos(this.estilosGif.caso10);
        this.audioManager?.tocarSomGif('Sons/Meme/funk_do_surprise.mp3');
        
        setTimeout(() => {
            this._retornoGif();
            if (this.audioManager?.audioAtual && !this.audioManager.pausar) {
                this.audioManager.retomarMusica();
            }
            this.contagem = 11;
        }, CONFIG.GIF.ANIMATION_DURATION);
    }
    
    _retornoGif() {
        this._aplicarEstilos(this.estilosGif.retorno);
        this._inverterGif();
        this.audioManager?.tocarSomGif('Sons/button_click_01.mp3');
    }
    
    _inverterGif() {
        this.gif.style.transform = this.invertida ? 'scaleX(1)' : 'scaleX(-1)';
        this.invertida = !this.invertida;
    }
    
    _aplicarEstilos(estilos) {
        if (estilos instanceof Object) {
            Object.assign(this.gif.style, estilos);
        } else if (typeof estilos === 'string') {
            this.gif.style.filter = estilos;
        }
    }
    
    reset() {
        this.contagem = 0;
        this.invertida = false;
        this._aplicarEstilos(this.estilosGif.retorno);
        this.gif.style.transform = 'scaleX(1)';
        this.gif.style.animation = 'none';
    }
}