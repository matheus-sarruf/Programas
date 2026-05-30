// Configurações centralizadas do sistema

export const CONFIG = {
    // API
    API_URL: 'http://localhost:3000',

    // Temas CSS dos botões
    TEMAS_CSS: {
        verde: 'Temas/tema_verde.css',
        azul: 'Temas/tema_azul.css',
        misto1: 'Temas/tema_misto_1.css',
        misto2: 'Temas/tema_misto_2.css',
        moderno: 'Temas/tema_moderno.css'
    },

    // Valores de temas disponíveis
    TEMAS_VALORES: ['verde', 'azul', 'misto1', 'misto2', 'moderno'],

    // Breakpoints responsivos
    RESPONSIVE_BREAKPOINTS: {
        1150: 230,
        806: 300,
        600: 360,
        450: 500
    },

    // Configurações de volume
    VOLUME: {
        CLIQUE: 0.4,
        HOVER: 0.3,
        MUSICA_FADE_DURATION: 3000,
        MUSICA_FADE_INTERVAL: 50
    },

    // Configurações de zoom
    ZOOM: {
        MIN: 0.1,
        MAX: 3,
        STEP: 0.1,
        DEFAULT: 1
    },

    // Configurações do GIF
    GIF: {
        ANIMATION_DURATION: 26500,
        CONTAGEM_MAX: 11
    },

    // Seletores DOM (para evitar strings mágicas)
    SELECTORS: {
        // Navegação
        NAV_BUTTONS: 'nav ul li button',

        // Elementos interativos gerais
        INTERACTIVE_ELEMENTS: 'a, button, #tema_icone, #tema_opcoes button, #butoes_tema_icone, #butoes_tema_opcoes button',

        // Temas principal
        TEMA_CONTAINER: '#tema_conteiner',
        TEMA_ICONE: '#tema_icone',
        TEMA_OPCOES: '#tema_opcoes',
        TEMA_OPCOES_BOTOES: '#tema_opcoes button',

        // Temas dos botões
        BOTOES_TEMA_CONTAINER: '#butoes_tema_conteiner',
        BOTOES_TEMA_ICONE: '#butoes_tema_icone',
        BOTOES_TEMA_OPCOES: '#butoes_tema_opcoes',
        BOTOES_TEMA_OPCOES_BOTOES: '#butoes_tema_opcoes button',

        // Controles de áudio
        VOLUME_SLIDER: '#volumeSlider',
        VOLUME_VALUE: '#volumeValue',
        PLAY_PAUSE: '#play_pause',
        NOVA_MUSICA: '#nova_musica',

        // Informações
        INFO_ICONE: '#informacoes_icone',
        INFO_DIV: '#infoDiv',
        FECHAR_INFO: '#fecharInfo',

        // Menu responsivo
        MENU_ICONE: '#menu_icone',

        // Iframe
        IFRAME: '#centro',

        // GIF
        GIF_HEADER: '#gif_header'
    }
};