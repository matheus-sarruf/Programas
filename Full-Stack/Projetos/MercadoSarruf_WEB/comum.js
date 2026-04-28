if (document.documentElement.clientWidth < 850) {
    document.getElementById('alinha_pai').innerHTML = '';
}

let tema_data = {};
let cursor_data = {};
let sons_data = {};

async function carregarApis() {
    try {
        const resposta_tema = await fetch('http://localhost:3000/obter_tema');
        const resposta_cursor = await fetch('http://localhost:3000/obter_cursor');
        const resposta_sons = await fetch('http://localhost:3000/obter_sons');
        tema_data = await resposta_tema.json();
        cursor_data = await resposta_cursor.json();
        sons_data = await resposta_sons.json();
        carregarTemaButoes(tema_data);
        carregarSons(sons_data);
        carregarCursor(cursor_data);
    } catch (erro) {
        console.error('Erro: 101. Erro ao carregar API: ' + erro);
        console.error('Erro: 111. Erro ao conectar com o servidor. Verifique se o Node.js está rodando.');
    }
}

const temasCSS = {
    verde: './Temas/tema_verde.css',
    azul: './Temas/tema_azul.css',
    tema_misto_1: './Temas/tema_misto_1.css',
    tema_misto_2: './Temas/tema_misto_2.css',
    moderno: './Temas/tema_moderno.css'
};

const linkCSS = document.querySelector('link[href*="butoes_tema.css"]');

async function carregarTemaButoes(temaD) {
    const temasV = ['verde', 'azul', 'misto1', 'misto2', 'moderno'];
    let ct = false;

    if (temasCSS[temaD.tema]) {
        linkCSS.href = temasCSS[temaD.tema];
    }

    for (let i = 0; i < temasV.length; i++) {
        if (temaD.tema == temasV[i]) {
            ct = true;
            return;
        }
    }
    if (!ct) {
        console.error("Erro: 122. Tema desconhecido: " + temaD.tema);
    }
}

async function carregarCursor(cursorD) {

    document.body.style.cursor = `url('${cursorD.caminho_cursor_tudo}'), default`;

    const links = document.querySelectorAll('a');
    links.forEach(link => {
        link.style.cursor = `url('${cursorD.caminho_cursor_link}'), pointer`;
    });

    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.style.cursor = `url('${cursorD.caminho_cursor_link}'), pointer`;
    });
}

async function carregarSons(sonsD) {
    try {
        somClique.src = sonsD.caminho_som_click;
        somHover.src = sonsD.caminho_som_hover;

        await Promise.all([
            new Promise(resolve => {
                somClique.addEventListener('canplaythrough', resolve);
                somClique.load();
            }),
            new Promise(resolve => {
                somHover.addEventListener('canplaythrough', resolve);
                somHover.load();
            })
        ]);

        sonsCarregados = true;

    } catch (erro) {
        console.error('Erro: 221. Erro ao carregar sons:', erro);
        sonsCarregados = false;
    }
}

const somClique = document.getElementById('somClique');
const somHover = document.getElementById('somHover');
let sonsCarregados = false;
const botoes = document.querySelectorAll('button');

botoes.forEach(botao => {
    botao.addEventListener('click', (e) => {
        if (sonsCarregados) {
            somClique.volume = 1;
            somClique.currentTime = 0;
            somClique.play();
        }
    });

    botao.addEventListener('mouseenter', () => {
        if (sonsCarregados) {
            somHover.volume = 0.25;
            somHover.currentTime = 0;
            somHover.play();
        }
    });
});