// Gerenciamento de temas visuais

import { CONFIG } from '../Configuracoes/constantes.js';
import { ServicoArmazenamento } from '../Servicos/armazenamento.js';
import { ServicoApi } from './api.js';

export class GerenciadorTema {
    constructor(bodyElement) {
        this.body = bodyElement;
        this.temaAtual = null;
        this.temaBotoesAtual = null;
        this.estrelasContainer = null;
    }

    async carregarTema(dadosTema) {
        this.temaAtual = dadosTema;

        if (dadosTema.tema === 'moderno') {
            this._aplicarTemaModerno();
            return;
        }

        const temaSalvo = ServicoArmazenamento.obterTema();
        this.body.setAttribute('tema', temaSalvo);
        this._atualizarBotoesTemaUI(temaSalvo);

        if (temaSalvo === 'Tema_Animado_1') {
            this.criarEstrelas();
        } else {
            this.removerEstrelas();
        }

    }

    async carregarTemaBotoes(dadosTema) {
        this.temaBotoesAtual = dadosTema.tema;

        const linkCSS = document.querySelector('link[href*="butoes_tema.css"]');
        if (CONFIG.TEMAS_CSS[dadosTema.tema]) {
            linkCSS.href = CONFIG.TEMAS_CSS[dadosTema.tema];
        }

        this._atualizarBotoesTemaOpcoes(dadosTema.tema);
    }

    async alterarTemaBotoes(temaNovo) {
        try {
            await ServicoApi.alterarTema(temaNovo);
            setTimeout(() => {
                window.dispatchEvent(new CustomEvent('temabotoeschange', { detail: { tema: temaNovo } }));
            }, 1500);
        } catch (error) {
            console.error('Erro ao alterar tema dos botões:', error);
        }
    }

    _aplicarTemaModerno() {
        const eClaro = ServicoArmazenamento.obterEclaro();
        const tema = eClaro ? 'Tema_Moderno_Claro' : 'Tema_Moderno_Escuro';
        this.body.setAttribute('tema', tema);
    }

    alternarTemaModerno() {
        const eClaro = !ServicoArmazenamento.obterEclaro();
        ServicoArmazenamento.salvarEclaro(eClaro);
        const tema = eClaro ? 'Tema_Moderno_Claro' : 'Tema_Moderno_Escuro';
        this.body.setAttribute('tema', tema);
        return eClaro;
    }

    _atualizarBotoesTemaUI(tema) {
        const botoes = document.querySelectorAll('#tema_opcoes button');
        botoes.forEach(btn => {
            btn.classList.toggle('ativado', btn.getAttribute('tema') === tema);
        });
    }

    _atualizarBotoesTemaOpcoes(tema) {
        const botoes = document.querySelectorAll('#butoes_tema_opcoes button');
        botoes.forEach(btn => {
            const textoBtn = btn.textContent.split(' ')[1]?.toLowerCase();
            btn.classList.toggle('ativado', textoBtn === tema);
        });
    }

    criarEstrelas() {
        this.removerEstrelas();

        this.estrelasContainer = document.createElement('div');
        this.estrelasContainer.className = 'estrelas';
        this.body.appendChild(this.estrelasContainer);

        for (let i = 0; i < 400; i++) {
            setTimeout(() => {
                const estrela = document.createElement('div');
                estrela.className = 'estrela';

                const size = Math.random() * 5 + 1;
                estrela.style.width = size + 'px';
                estrela.style.height = size + 'px';
                estrela.style.left = Math.random() * 100 + '%';
                estrela.style.top = Math.random() * 100 + '%';

                const duration = Math.random() * 4 + 1;
                estrela.style.animationDuration = duration + 's';
                estrela.style.animationDelay = Math.random() * 2 + 's';
                estrela.style.opacity = Math.random() * 0.8 + 0.4;

                this.estrelasContainer.appendChild(estrela);
            }, i * 800);
        }
    }

    removerEstrelas() {
        if (this.estrelasContainer) {
            this.estrelasContainer.remove();
            this.estrelasContainer = null;
        }
    }

    aplicarTema(tema) {
        ServicoArmazenamento.salvarTema(tema);
        this.body.setAttribute('tema', tema);
        this._atualizarBotoesTemaUI(tema);

        if (tema === 'Tema_Animado_1') {
            this.criarEstrelas();
        } else {
            this.removerEstrelas();
        }
    }
}