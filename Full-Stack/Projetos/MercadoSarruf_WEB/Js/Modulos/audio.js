// Modulos/audio.js
import { CONFIG } from '../Configuracoes/constantes.js';
import { ServicoArmazenamento } from '../Servicos/armazenamento.js';
export class GerenciadorAudio {
    constructor() {
        this.audioAtual = null;
        this.sonsCarregados = false;
        this.musicaIniciada = false;
        this.volume = ServicoArmazenamento.obterVolume();
        this.pausar = ServicoArmazenamento.obterPausar();
        this.musicaNome = null;
        this.sons = {
            clique: null,
            hover: null,
            gif: null
        };
        this.listaMusicas = [];
    }

    async carregarSons(dadosSons) {
        try {
            this.sons.clique = new Audio(dadosSons.caminho_som_click);
            this.sons.hover = new Audio(dadosSons.caminho_som_hover);
            this.sons.gif = new Audio('Sons/Efeitos/button_click_01.mp3');
            this.listaMusicas = dadosSons.caminho_musica || [];

            await Promise.all([
                this._aguardarCarregamento(this.sons.clique),
                this._aguardarCarregamento(this.sons.hover),
                this._aguardarCarregamento(this.sons.gif)
            ]);

            this.sonsCarregados = true;
            console.log('Sons carregados com sucesso');
        } catch (erro) {
            console.error('Erro ao carregar sons:', erro);
            this.sonsCarregados = false;
        }
    }

    _aguardarCarregamento(audio) {
        return new Promise((resolve) => {
            if (audio.readyState >= 3) {
                resolve();
                return;
            }
            audio.addEventListener('canplaythrough', resolve, { once: true });
            audio.load();
        });
    }

    executarSom(tipo) {
        if (!this.sonsCarregados) return;

        const som = this.sons[tipo];

        if (som) {
            const volume = tipo === 'clique' ? CONFIG.VOLUME.CLIQUE : CONFIG.VOLUME.HOVER;
            som.volume = volume;
            som.currentTime = 0;
            som.play().catch(e => console.warn(`Som ${tipo} não pôde ser reproduzido:`, e));
        }
    }

    tocarSomGif(src) {
        if (!this.sons.gif) return;
        this.sons.gif.src = src;
        this.sons.gif.volume = 0.5;
        this.sons.gif.currentTime = 0;
        this.sons.gif.play().catch(e => console.warn('Som do GIF não pôde ser reproduzido:', e));
    }

    iniciarMusica() {
        if (this.pausar) return;
        if (this.audioAtual && !this.audioAtual.paused) return;

        if (this.audioAtual) {
            setTimeout(() => this._tocarMusicaAleatoria(), 3000);
        } else {
            this._tocarMusicaAleatoria();
        }
        this.musicaIniciada = true;
    }

    _tocarMusicaAleatoria() {
        if (!this.listaMusicas.length) {
            console.error('Nenhuma música disponível');
            setTimeout(() => this._tocarMusicaAleatoria(), 5000);
            return;
        }

        if (this.audioAtual) {
            this.audioAtual.pause();
            this.audioAtual = null;
        }

        const indice = Math.floor(Math.random() * this.listaMusicas.length);
        const caminho = this.listaMusicas[indice];
        this.musicaNome = caminho.split('/').pop().replace(/\.[^/.]+$/, '');

        console.log('Tocando música:', this.musicaNome);

        this.audioAtual = new Audio(caminho);
        this.audioAtual.volume = 0;
        this.audioAtual.loop = false;

        this.audioAtual.addEventListener('canplaythrough', () => {
            this._fadeIn(this.audioAtual, this.volume, 3000);
            this.audioAtual.play().catch(e => {
                console.error('Erro ao tocar música:', e);
                setTimeout(() => this._tocarMusicaAleatoria(), 2000);
            });
        }, { once: true });

        this.audioAtual.addEventListener('ended', () => {
            console.log('Música terminada, próxima...');
            this._tocarMusicaAleatoria();
        });

        this.audioAtual.addEventListener('error', (e) => {
            console.error('Erro na música:', e);
            setTimeout(() => this._tocarMusicaAleatoria(), 2000);
        });
    }

    _fadeIn(audio, volumeAlvo, duracao) {
        if (!audio) return;

        let volume = 0;
        const passo = volumeAlvo / (duracao / 50);
        audio.volume = 0;

        const intervaloFade = setInterval(() => {
            if (volume < volumeAlvo) {
                volume = Math.min(volume + passo, volumeAlvo);
                audio.volume = volume;
            } else {
                clearInterval(intervaloFade);
            }
        }, 50);
    }

    aumentarVolume() {
        this.volume = Math.min(this.volume + 0.05, 1);
        this._atualizarVolume();
    }

    diminuirVolume() {
        this.volume = Math.max(this.volume - 0.05, 0);
        this._atualizarVolume();
    }

    _atualizarVolume() {
        ServicoArmazenamento.salvarVolume(this.volume);
        if (this.audioAtual) {
            this.audioAtual.volume = this.volume;
        }
        this._notificarVolumeChange();
    }

    _notificarVolumeChange() {
        const evento = new CustomEvent('volumechange', { detail: { volume: this.volume } });
        window.dispatchEvent(evento);
    }

    togglePausar() {
        this.pausar = !this.pausar;
        ServicoArmazenamento.salvarPausar(this.pausar);

        if (this.audioAtual) {
            this.pausar ? this.audioAtual.pause() : this.audioAtual.play();
        }

        const evento = new CustomEvent('pausarchange', { detail: { pausar: this.pausar } });
        window.dispatchEvent(evento);
    }

    proximaMusica() {
        if (this.audioAtual) {
            this.audioAtual.pause();
            this.audioAtual.currentTime = 0;
            this.audioAtual = null;
            this._tocarMusicaAleatoria();
        }
    }

    pausarMusica() {
        if (this.audioAtual) {
            this.audioAtual.pause();
        }
    }

    retomarMusica() {
        if (this.audioAtual && !this.pausar) {
            this.audioAtual.play().catch(e => console.warn('Erro ao retomar música:', e));
        }
    }
}