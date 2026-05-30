
import { CONFIG } from './Configuracoes/constantes.js';
import { ServicoApi } from './Modulos/api.js';
import { GerenciadorAudio } from './Modulos/audio.js';
import { GerenciadorTema } from './Modulos/tema.js';
import { GerenciadorNavegacao } from './Modulos/navegacao.js';
import { GerenciadorUI } from './Modulos/ui.js';
import { GerenciadorGif } from './Modulos/gif.js';
import { GerenciadorAtalhos } from './Modulos/atalhos.js';
import { GerenciadorResponsivo } from './Modulos/responsivo.js';
import { GerenciadorCursor } from './Modulos/cursor.js';
import { ServicoArmazenamento } from './Servicos/armazenamento.js';
import { Ajuda } from './Utilidades/ajuda.js';

class Application {
    constructor() {
        // Inicializar gerenciadores
        this.gerenciadorAudio = new GerenciadorAudio();
        this.gerenciadorCursor = new GerenciadorCursor(document.body);
        this.gerenciadorTema = new GerenciadorTema(document.body);
        this.gerenciadorResponsivo = new GerenciadorResponsivo(
            document.body,
            document.getElementById('1'),
            document.querySelector('footer')
        );

        // UI depende do audio e cursor
        this.gerenciadorUI = new GerenciadorUI(this.gerenciadorAudio, this.gerenciadorCursor, this.gerenciadorTema);

        // GIF depende do audio
        this.gerenciadorGif = new GerenciadorGif(document.getElementById('gif_header'), this.gerenciadorAudio);

        // Atalhos depende de vários managers
        this.gerenciadorAtalhos = new GerenciadorAtalhos(this.gerenciadorAudio, this.gerenciadorUI, this.gerenciadorResponsivo);

        // Navegação
        this.gerenciadorNavegacao = new GerenciadorNavegacao(
            document.querySelectorAll('nav ul li button'),
            document.getElementById('centro')
        );

        this.inicializado = false;
    }

    async init() {
        if (this.inicializado) return;

        try {
            // Mostrar loading (opcional)
            this._showLoading();
            // 1. Carregar dados da API
            const dados = await ServicoApi.carregarTodosDados();

            // 2. Inicializar módulos em paralelo
            await Promise.all([
                this.gerenciadorAudio.carregarSons(dados.sons),
                this.gerenciadorCursor.carregarCursor(dados.cursor),
                this.gerenciadorTema.carregarTema(dados.tema),
                this.gerenciadorTema.carregarTemaBotoes(dados.tema)
            ]);

            // 3. Inicializar outros módulos
            this.gerenciadorNavegacao.init();
            this.gerenciadorUI.init();
            this.gerenciadorGif.init();
            this.gerenciadorAtalhos.init();
            this.gerenciadorResponsivo.init();

            // 4. Configurar eventos globais
            this._configurarEventosGlobais();

            // 5. Iniciar música após 2 segundos
            setTimeout(() => {
                this.gerenciadorAudio.iniciarMusica();
            }, 2000);

            // 6. Verificar tela muito pequena
            this._verificarTelaPequena();

            // 7. Configurar listeners de eventos personalizados
            this._configurarEventosPersonalizados();

            this.inicializado = true;
            this._hideLoading();

            console.log('Aplicação inicializada com sucesso!');

        } catch (error) {
            console.error('Falha na inicialização:', error);
            this._showError();
        }
    }

    _configurarEventosGlobais() {
        // Sons para botões 
        document.addEventListener('click', (e) => {
            const target = e.target;

            if (target.nodeType !== Node.ELEMENT_NODE) {
                return;
            }

            if (target.matches(CONFIG.SELECTORS.INTERACTIVE_ELEMENTS)) {
                this.gerenciadorAudio.executarSom('clique');
            }
        });

        // Som de hover para botões
        document.addEventListener('mouseenter', (e) => {
            const target = e.target;

            if (target.nodeType !== Node.ELEMENT_NODE) {
                return;
            }

            if (target.matches('button') &&
                !target.matches('#play_pause') &&
                !target.matches('#nova_musica')) {
                this.gerenciadorAudio.executarSom('hover');
            }
        }, true);

        // Pausar música quando a aba não está visível
        document.addEventListener('visibilitychange', () => {
            if (this.gerenciadorAudio.audioAtual) {
                document.visibilityState === 'hidden'
                    ? this.gerenciadorAudio.pausarMusica()
                    : this.gerenciadorAudio.retomarMusica();
            }
        });

        // Configurar tema principal (canto superior ESQUERDO)
        this._configurarTemaPrincipal();

        // Configurar tema dos botões (canto superior DIREITO)
        this._configurarTemaBotoes();
    }

    _configurarEventosPersonalizados() {
        // Quando o tema dos botões muda
        window.addEventListener('temabotoeschange', async (e) => {
            await this.gerenciadorTema.carregarTemaBotoes({ tema: e.detail.tema });
        });

        // Quando o volume muda
        window.addEventListener('volumechange', (e) => {
            this.gerenciadorUI.atualizarVolumeDisplay();
        });

        // Quando o estado de pausa muda
        window.addEventListener('pausarchange', (e) => {
            this.gerenciadorUI.atualizarPlayPauseBtn();
        });

        // Recriar estrelas
        window.addEventListener('recriarestrelas', () => {
            if (document.body.getAttribute('tema') === 'Tema_Animado_1') {
                this.gerenciadorTema.criarEstrelas();
            }
        });
    }

    _configurarTemaPrincipal() {
        const temaContainer = document.getElementById('tema_conteiner');
        const temaOpcoes = document.getElementById('tema_opcoes');

        if (temaContainer) {
            temaContainer.addEventListener('click', (e) => {
                // Caso especial para tema moderno
                if (this.gerenciadorTema.temaAtual?.tema === 'moderno') {
                    const eClaro = this.gerenciadorTema.alternarTemaModerno();
                    const playPauseBtn = document.getElementById('play_pause');
                    if (playPauseBtn && eClaro !== undefined) {
                        // Atualizar UI se necessário
                    }
                    return;
                }

                e.stopPropagation();
                const isVisible = temaOpcoes.style.display === 'flex';
                temaOpcoes.style.display = isVisible ? 'none' : 'flex';

                this.gerenciadorResponsivo.verLargura();
                if (this.gerenciadorResponsivo.width_tela <= 600) {
                    temaContainer.style.borderRadius = isVisible ? '15px' : '15px 15px 0px 0px';
                }
                this.gerenciadorResponsivo.ajustarMarginTemaOpcoes(!isVisible);
                this.gerenciadorAudio.executarSom('clique');
            });
        }

        const botoesTema = document.querySelectorAll('#tema_opcoes button');
        botoesTema.forEach(btn => {
            btn.addEventListener('click', () => {
                const tema = btn.getAttribute('tema');
                this.gerenciadorTema.aplicarTema(tema);
                if (temaOpcoes) temaOpcoes.style.display = 'none !important';
                this.gerenciadorResponsivo.ajustarMarginTemaOpcoes(false);

                if (this.gerenciadorResponsivo.width_tela <= 600 && temaContainer) {
                    temaContainer.style.borderRadius = '15px';
                }
                this.gerenciadorAudio.executarSom('clique');
            });
        });

        document.addEventListener('click', () => {
            if (temaOpcoes) {
                temaOpcoes.style.display = 'none';
                this.gerenciadorResponsivo.ajustarMarginTemaOpcoes(false);

                if (this.gerenciadorResponsivo.width_tela <= 600 && temaContainer) {
                    temaContainer.style.borderRadius = '15px';
                }
            }
        });
    } 

    _configurarTemaBotoes() {
        const botoesTemaContainer = document.getElementById('butoes_tema_conteiner');
        const botoesTemaOpcoes = document.getElementById('butoes_tema_opcoes');

        if (botoesTemaContainer) {
            botoesTemaContainer.addEventListener('click', (e) => {
                const isVisible = botoesTemaOpcoes.style.display === 'flex';
                botoesTemaOpcoes.style.display = isVisible ? 'none' : 'flex';
                this.gerenciadorResponsivo.ajustarMarginBotoesTema(!isVisible);
                this.gerenciadorAudio.executarSom('clique');
                e.stopPropagation();
            });
        }

        const botoes = document.querySelectorAll('#butoes_tema_opcoes button');
        botoes.forEach(btn => {
            btn.addEventListener('click', async () => {
                const textoBtn = btn.textContent.split(' ')[1].toLowerCase();
                await this.gerenciadorTema.alterarTemaBotoes(textoBtn);

                botoes.forEach(b => b.classList.remove('ativado'));
                btn.classList.add('ativado');
                this.gerenciadorAudio.executarSom('clique');
                setTimeout(() => {
                    location.reload();
                }, 250);
            });
        });

        document.addEventListener('click', () => {
            if (botoesTemaOpcoes) {
                botoesTemaOpcoes.style.display = 'none';
                this.gerenciadorResponsivo.ajustarMarginBotoesTema(false);
            }
        });
    }

    _verificarTelaPequena() {
        if (this.gerenciadorResponsivo.width_tela <= 315) {
            document.body.innerHTML = `
                <div style="text-align: center; padding: 20px;">
                    <h1>Erro 405 - Página não encontrada</h1>
                    <p>Desculpe, mas a página que você está tentando não pode ser exibida em telas tão pequenas.</p>
                    <p>Por favor, tente acessar este site em um dispositivo com uma tela maior para uma melhor experiência.</p>
                    <p><a href="javascript:history.back()">Voltar</a></p>
                </div>
            `;
        }
    }

    _showLoading() {
        console.log('Carregando aplicação...');
    }

    _hideLoading() {
        console.log('Aplicação carregada!');
    }

    _showError() {
        const errorDiv = document.createElement('div');
        errorDiv.style.cssText = `
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: red;
            color: white;
            padding: 20px;
            border-radius: 10px;
            z-index: 9999;
            text-align: center;
        `;
        errorDiv.innerHTML = `
            <h2>Erro ao carregar aplicação</h2>
            <p>Verifique se o servidor está rodando em ${CONFIG.API_URL}</p>
            <button onclick="location.reload()">Tentar novamente</button>
        `;
        document.body.appendChild(errorDiv);
    }
}

// Inicializar aplicação quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', () => {
    const app = new Application();
    app.init();
});