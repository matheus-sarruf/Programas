// Gerenciamento de elementos da interface (infoDiv, etc)

import { ServicoArmazenamento } from '../Servicos/armazenamento.js';
import { Ajuda } from '../Utilidades/ajuda.js';

export class GerenciadorUI {
    constructor(audioManager, cursorManager, temaManager) {
        this.audioManager = audioManager;
        this.cursorManager = cursorManager;
        this.temaManager = temaManager;
        this.infoVisivel = false;
        this.infoInterval = null;
        
        this.elementos = {
            infoDiv: document.getElementById('infoDiv'),
            infoTexto: document.getElementById('infoTexto'),
            fecharInfo: document.getElementById('fecharInfo'),
            informacoesIcone: document.getElementById('informacoes_icone')
        };
        
        // Bind dos métodos para remover listeners depois
        this._handleVolumeChange = this._handleVolumeChange.bind(this);
        this._handlePausarChange = this._handlePausarChange.bind(this);
        this._handleMusicaChange = this._handleMusicaChange.bind(this);
    }
    
    init() {
        if (this.elementos.informacoesIcone) {
            this.elementos.informacoesIcone.addEventListener('click', (e) => this.toggleInfo(e));
        }
        
        if (this.elementos.fecharInfo) {
            this.elementos.fecharInfo.addEventListener('click', () => this.esconderInfo());
        }
        
        // Fechar info ao clicar fora
        document.addEventListener('click', (e) => {
            if (this.infoVisivel && 
                !this.elementos.infoDiv?.contains(e.target) && 
                e.target !== this.elementos.informacoesIcone) {
                this.esconderInfo();
            }
        });
        
        // Listeners para eventos que devem atualizar a UI
        window.addEventListener('volumechange', this._handleVolumeChange);
        window.addEventListener('pausarchange', this._handlePausarChange);
        window.addEventListener('musicachange', this._handleMusicaChange);
    }
    
    verInfo() {
        if (this.infoVisivel) return;
        
        this.infoVisivel = true;
        this.elementos.infoDiv.style.display = 'block';
        this._criarEstruturaInfo(); // Criar estrutura uma vez
        this._atualizarDataHora(); // Atualizar data/hora inicial
        
        // Atualizar APENAS a data/hora a cada segundo
        this.infoInterval = setInterval(() => this._atualizarDataHora(), 1000);
        
        setTimeout(() => {
            this.elementos.infoDiv.classList.add('mostrar');
        }, 30);
    }
    
    esconderInfo() {
        if (!this.infoVisivel) return;
        
        this.infoVisivel = false;
        this.elementos.infoDiv.classList.remove('mostrar');
        
        if (this.infoInterval) {
            clearInterval(this.infoInterval);
            this.infoInterval = null;
        }
        
        // Remover listeners de eventos quando a info está fechada
        window.removeEventListener('volumechange', this._handleVolumeChange);
        window.removeEventListener('pausarchange', this._handlePausarChange);
        window.removeEventListener('musicachange', this._handleMusicaChange);
        
        setTimeout(() => {
            this.elementos.infoDiv.style.display = 'none';
        }, 500);
    }
    
    toggleInfo(e) {
        e?.stopPropagation();
        if (this.infoVisivel) {
            this.esconderInfo();
        } else {
            this.verInfo();
        }
        this.audioManager?.executarSom('clique');
    }
    
    _criarEstruturaInfo() {
        if (!this.elementos.infoTexto) return;
        
        this.elementos.infoTexto.innerHTML = `
            <ul>
                <li id="info-data-hora"><b>Data e Hora:</b> ${Ajuda.formatarData()}.</li>
                <li>Sistema Operacional: ${Ajuda.detectarSO()}.</li>
                <li>Largura da Tela: ${window.innerWidth}px.</li>
                <li id="info-musica">Musica: ${this.audioManager?.musicaNome || 'Nenhuma musica tocando'}.</li>
                <li id="info-volume">Volume: ${Ajuda.formatarPorcentagem(this.audioManager?.volume || 0)}</li>
                <div class="infoDivGuardarBotoes1">
                    Mixer Musica 
                    <div class="infoDivGuardarBotoes2">
                        <button id="play_pause" class="${ServicoArmazenamento.obterPausar() ? 'pausar' : ''}"></button>
                        <button id="nova_musica"></button>
                    </div>
                    <div class="infoDivGuardarBotoes2">
                        <input type="range" id="volumeSlider" min="0" max="1" step="0.01" value="${this.audioManager?.volume || 0.5}">
                        <span id="volumeValue">${Ajuda.formatarPorcentagem(this.audioManager?.volume || 0.5)}</span>
                    </div>
                </div>
            </ul>
            <div id="atalhos_info">
                <ul>
                    <h2>Atalhos:</h2>
                    <li><b>Ctrl + Alt + X:</b> Mostrar/Esconder informações.</li>
                    <li><b>Ctrl + '+':</b> Zoom In.</li>
                    <li><b>Ctrl + '-':</b> Zoom Out.</li>
                    <li><b>Ctrl + 0:</b> Reset Zoom.</li>
                    <li><b>Ctrl + Alt + Z:</b> Pausar Música.</li>
                    <li><b>Ctrl + Alt + N:</b> Nova Música.</li>
                    <li><b>Ctrl + Alt + D:</b> Diminuir Volume.</li>
                    <li><b>Ctrl + Alt + F:</b> Aumentar Volume.</li>
                </ul>
            </div>
        `;
        
        this._configurarControlesInfo();
    }
    
    _atualizarDataHora() {
        const dataHoraElement = document.getElementById('info-data-hora');
        if (dataHoraElement && this.infoVisivel) {
            dataHoraElement.innerHTML = `<b>Data e Hora:</b> ${Ajuda.formatarData()}.`;
        }
    }
    
    _atualizarMusica() {
        const musicaElement = document.getElementById('info-musica');
        if (musicaElement && this.infoVisivel && this.audioManager) {
            musicaElement.textContent = `Musica: ${this.audioManager.musicaNome || 'Nenhuma musica tocando'}.`;
        }
    }
    
    _atualizarVolume() {
        const volumeElement = document.getElementById('info-volume');
        const volumeValue = document.getElementById('volumeValue');
        const volumeSlider = document.getElementById('volumeSlider');
        
        if (this.infoVisivel && this.audioManager) {
            const volumePercent = Ajuda.formatarPorcentagem(this.audioManager.volume);
            
            if (volumeElement) {
                volumeElement.textContent = `Volume: ${volumePercent}`;
            }
            
            if (volumeValue) {
                volumeValue.textContent = volumePercent;
            }
            
            if (volumeSlider) {
                volumeSlider.value = this.audioManager.volume;
            }
        }
    }
    
    _atualizarPlayPauseButton() {
        const playPauseBtn = document.getElementById('play_pause');
        if (playPauseBtn && this.infoVisivel && this.audioManager) {
            playPauseBtn.classList.toggle('pausar', this.audioManager.pausar);
        }
    }
    
    // Handlers para eventos
    _handleVolumeChange(e) {
        this._atualizarVolume();
    }
    
    _handlePausarChange(e) {
        this._atualizarPlayPauseButton();
    }
    
    _handleMusicaChange(e) {
        this._atualizarMusica();
    }
    
    _configurarControlesInfo() {
        const volumeSlider = document.getElementById('volumeSlider');
        const volumeValue = document.getElementById('volumeValue');
        const playPauseBtn = document.getElementById('play_pause');
        const novaMusicaBtn = document.getElementById('nova_musica');
        
        if (volumeSlider) {
            if (this.cursorManager) {
                volumeSlider.style.cursor = `url('${this.cursorManager.caminhoLink}'), pointer`;
            }
            
            volumeSlider.addEventListener('input', (e) => {
                const volume = parseFloat(e.target.value);
                if (this.audioManager) {
                    this.audioManager.volume = volume;
                    if (this.audioManager.audioAtual) {
                        this.audioManager.audioAtual.volume = volume;
                    }
                    ServicoArmazenamento.salvarVolume(volume); // Usar método correto
                    this._atualizarVolume(); // Atualizar UI imediatamente
                    
                    // Disparar evento para sincronizar
                    const evento = new CustomEvent('volumechange', { detail: { volume } });
                    window.dispatchEvent(evento);
                }
            });
        }
        
        if (playPauseBtn) {
            if (this.cursorManager) {
                playPauseBtn.style.cursor = `url('${this.cursorManager.caminhoLink}'), pointer`;
            }
            
            playPauseBtn.addEventListener('click', () => {
                this.audioManager?.togglePausar();
                this._atualizarPlayPauseButton(); // Atualizar UI imediatamente
            });
        }
        
        if (novaMusicaBtn) {
            if (this.cursorManager) {
                novaMusicaBtn.style.cursor = `url('${this.cursorManager.caminhoLink}'), pointer`;
            }
            
            novaMusicaBtn.addEventListener('click', () => {
                this.audioManager?.proximaMusica();
                // O evento 'musicachange' será disparado pelo audioManager
            });
        }
    }
    
    // Métodos públicos para compatibilidade
    atualizarVolumeDisplay() {
        this._atualizarVolume();
    }
    
    atualizarPlayPauseBtn() {
        this._atualizarPlayPauseButton();
    }
}