// Sistema de navegação entre seções
document.addEventListener('DOMContentLoaded', function () {
    const navLinks = document.querySelectorAll('.nav-link');
    const gameBtns = document.querySelectorAll('.game-btn');
    const sections = document.querySelectorAll('.section-container');
    const confiraSection = document.getElementById('confira');
    const downloadDiv = document.getElementById('download');
    const nav = document.querySelector('nav');
    const menuIcon = document.getElementById('menu-icon');
    const navList = document.querySelector('ul#nav');
    let menuOpen = false;

    const validSections = ['inicio', 'subnautica1', 'belowzero', 'subnautica2', 'mapas'];

    // Função para mostrar uma seção específica
    function showSection(sectionId) {
        sections.forEach(section => {
            section.classList.remove('active-section');
        });

        const targetSection = document.getElementById(sectionId);
        if (targetSection) {
            targetSection.classList.add('active-section');
        }

        // Atualiza o estado ativo dos links do nav (importante para o #inicio)
        navLinks.forEach(link => {
            if (link.dataset.section === sectionId) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });

        gameBtns.forEach(btn => {
            if (btn.dataset.section === sectionId) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });

        // Sombra do nav por seção
        const shadows = {
            'inicio': '2px 2px 4px 1px rgb(135, 135, 155)',
            'subnautica1': '2px 2px 4px 1px rgba(0, 53, 186, 1)',
            'belowzero': '2px 2px 4px 1px rgba(0, 221, 140, 0.8)',
            'subnautica2': '2px 2px 4px 1px rgba(170, 0, 255, 0.65)'
        };
        nav.style.boxShadow = shadows[sectionId] || shadows['inicio'];

        updateConfiraSection(sectionId);
        updateDownloadDiv(sectionId);

        if (menuOpen && window.innerWidth <= 500) {
            toggleMenu();
        }

        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // ===== MENU MOBILE SIMPLIFICADO =====
    function toggleMenu() {
        menuOpen = !menuOpen;
        nav.classList.toggle('menu-open', menuOpen);
        menuIcon.classList.toggle('menu-open', menuOpen);
    }

    // Função para atualizar o conteúdo da seção #confira
    function updateConfiraSection(sectionId) {
        if (!confiraSection) return;

        let confiraHTML = '';

        switch (sectionId) {
            case 'inicio':
                confiraHTML = `
                    <p>Confira a lore de <a href="#" class="nav-link" data-section="subnautica1">Subnautica 1</a></p>
                `;
                break;
            case 'subnautica1':
                confiraHTML = `
                    <p>Confira a lore de <a href="#" class="nav-link" data-section="belowzero">Below Zero</a></p>
                `;
                break;
            case 'belowzero':
                confiraHTML = `
                    <p>Confira também o novo <a href="#" class="nav-link" data-section="subnautica2">Subnautica 2</a></p>
                `;
                break;
            case 'subnautica2':
                confiraHTML = `
                    <p>Confira os <a href="#" class="nav-link" data-section="mapas">Mapas e Biomas</a></p>
                `;
                break;
            case 'mapas':
                confiraHTML = `
                    <p>Confira também a lore de <a href="#" class="nav-link" data-section="subnautica1">Subnautica 1</a></p>
                    <p>Confira também a lore de <a href="#" class="nav-link" data-section="belowzero">Subnautica Below Zero</a></p>
                    <a style="text-decoration: none; width: fit-content; background-color: rgba(0, 155, 255, 0.05); color: transparent; text-shadow: none;"
                    href="Mapas/Obrigado pela Vista;).html">.</a>
                `;
                break;
        }

        confiraSection.innerHTML = confiraHTML;

        confiraSection.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                showSection(this.dataset.section);
                history.pushState(null, null, `#${this.dataset.section}`);
            });
        });
    }

    function updateDownloadDiv(sectionId) {
        if (!downloadDiv) return;
        let confiraHTML = '';
        switch (sectionId) {
            case 'inicio':
                confiraHTML = ``;
                break;
            case 'subnautica1':
                confiraHTML = `
                <div class="linkers">
                    <a href="https://store.steampowered.com/app/264710/Subnautica/" target="_blank">Download na Steam</a>
                </div>
                `;
                break;
            case 'belowzero':
                confiraHTML = `
                <div class="linkers">
                    <a href="https://store.steampowered.com/app/848450/Subnautica_Below_Zero/" target="_blank">Download na Steam</a>
                </div>
                `;
                break;
            case 'subnautica2':
                confiraHTML = `
                <div class="linkers">
                    <a href="https://store.steampowered.com/app/1962700/Subnautica_2/" target="_blank">Download na Steam (Early Access)</a>
                </div>
                `;
                break;
            case 'mapas':
                confiraHTML = `
                <div class="linkers">
                    <a href="https://mapgenie.io/subnautica/maps/world" target="_blank">Mapa interativo de Map-Genie</a>
                </div>
                `;
                break;
        }

        downloadDiv.innerHTML = confiraHTML;

        downloadDiv.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                showSection(this.dataset.section);
                history.pushState(null, null, `#${this.dataset.section}`);
            });
        });
    }

    // Eventos dos links de navegação principais
    navLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const sectionId = this.dataset.section;
            showSection(sectionId);
            history.pushState(null, null, `#${sectionId}`);
        });
    });

    // Eventos dos botões de jogo
    gameBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            const sectionId = this.dataset.section;
            showSection(sectionId);
            history.pushState(null, null, `#${sectionId}`);
        });
    });

    // Evento do botão de menu
    menuIcon.addEventListener('click', toggleMenu);

    // ===== LIDA COM A URL INICIAL =====
    // Isso garante que, ao abrir https://site-sarruf2.shop/#inicio,
    // o botão "Início" fique ativo corretamente.
    function handleHashChange() {
        const hash = window.location.hash.substring(1);
        if (validSections.includes(hash)) {
            showSection(hash);
        } else {
            // Se não houver hash ou for inválido, mostra o início
            showSection('inicio');
            // Opcional: coloca #inicio na URL
            history.replaceState(null, null, '#inicio');
        }
    }

    // Executa ao carregar a página
    handleHashChange();

    // Ouve mudanças de hash (inclusive ao digitar a URL direto, clicar em links
    // internos ou usar voltar/avançar do navegador)
    window.addEventListener('hashchange', handleHashChange);
    window.addEventListener('popstate', handleHashChange);

    // Configurar menu responsivo inicial
    function setupResponsiveMenu() {
        if (window.innerWidth <= 500) {
            menuIcon.style.display = 'flex';
            // O CSS controla max-height/opacity/transform da lista
        } else {
            menuIcon.style.display = 'none';
            navList.style.display = 'flex';
            navList.style.maxHeight = '';
            navList.style.opacity = '';
            navList.style.transform = '';
            navList.style.transition = '';
            nav.classList.remove('menu-open');
            menuIcon.classList.remove('menu-open');
            menuOpen = false;
        }
    }

    setupResponsiveMenu();
    window.addEventListener('resize', setupResponsiveMenu);

    // Fechar menu ao clicar fora (mobile)
    document.addEventListener('click', function (event) {
        if (window.innerWidth <= 500 && menuOpen) {
            const isClickInsideNav = nav.contains(event.target);
            const isClickOnMenuIcon = menuIcon.contains(event.target);

            if (!isClickInsideNav && !isClickOnMenuIcon) {
                toggleMenu();
            }
        }
    });
});
