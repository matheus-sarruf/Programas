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

    // Função para mostrar uma seção específica
    function showSection(sectionId) {
        // Esconder todas as seções
        sections.forEach(section => {
            section.classList.remove('active-section');
        });

        // Mostrar a seção desejada
        const targetSection = document.getElementById(sectionId);
        if (targetSection) {
            targetSection.classList.add('active-section');
        }

        // Atualizar estado ativo dos links de navegação
        navLinks.forEach(link => {
            if (link.dataset.section === sectionId) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });

        // Atualizar estado ativo dos botões de jogo
        gameBtns.forEach(btn => {
            if (btn.dataset.section === sectionId) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });

        // Atualizar sombra do nav baseado na seção
        if (sectionId === 'inicio') {
            nav.style.boxShadow = '2px 2px 4px 1px rgb(135, 135, 155)';
        }
        else if (sectionId === 'subnautica1') {
            nav.style.boxShadow = '2px 2px 4px 1px rgba(0, 53, 186, 1)';
        }
        else if (sectionId === 'belowzero') {
            nav.style.boxShadow = '2px 2px 4px 1px rgba(0, 221, 140, 0.8)';
        }
        else if (sectionId === 'subnautica2') {
            nav.style.boxShadow = '2px 2px 4px 1px rgba(170, 0, 255, 0.65)';
        }
        else {
            nav.style.boxShadow = '2px 2px 4px 1px rgb(135, 135, 155)';
        }

        updateConfiraSection(sectionId);
        updateDownloadDiv(sectionId);

        // Fechar menu mobile se aberto
        if (menuOpen && window.innerWidth <= 500) {
            toggleMenu();
        }

        // Rolar para o topo da página
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // Função para alternar o menu mobile
    function toggleMenu() {
        menuOpen = !menuOpen;

        if (menuOpen) {
            // Abrir menu
            menuIcon.classList.add('menu-open');
            nav.classList.add('menu-open');
            navList.style.display = 'flex';

            // Pequeno delay para garantir a transição
            setTimeout(() => {
                navList.style.maxHeight = '500px';
                navList.style.opacity = '1';
                navList.style.transform = 'translateY(-55px)';
            }, 50);
        } else {
            // Fechar menu
            menuIcon.classList.remove('menu-open');
            navList.style.maxHeight = '0';
            navList.style.opacity = '0';
            navList.style.transform = 'translateY(-20px)';

            // Remover classe após animação
            setTimeout(() => {
                nav.classList.remove('menu-open');
                navList.style.display = 'none';
            }, 520);
        }
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
                    <p>Confira também a lore de <a href="#" class="nav-link" data-section="subnautica2">Subnautica 2</a></p>
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

        // Reaplicar eventos aos links recém-criados
        const newNavLinks = confiraSection.querySelectorAll('.nav-link');
        newNavLinks.forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                const newSectionId = this.dataset.section;
                showSection(newSectionId);
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
                    <a href="https://store.steampowered.com/app/1962700/Subnautica_2/" target="_blank">Download na Steam</a>
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

        // Reaplicar eventos aos links recém-criados
        const newNavLinks = downloadDiv.querySelectorAll('.nav-link');
        newNavLinks.forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                const newSectionId = this.dataset.section;
                showSection(newSectionId);
            });
        });
    }

    // Adicionar eventos aos links de navegação
    navLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const sectionId = this.dataset.section;
            showSection(sectionId);
        });
    });

    // Adicionar eventos aos botões de jogo
    gameBtns.forEach(btn => {
        btn.addEventListener('click', function () {
            const sectionId = this.dataset.section;
            showSection(sectionId);
        });
    });

    // Evento para o botão de menu
    menuIcon.addEventListener('click', toggleMenu);

    // Verificar se há um hash na URL para carregar uma seção específica
    const hash = window.location.hash.substring(1);
    const validSections = ['inicio', 'subnautica1', 'belowzero', 'subnautica2', 'mapas'];

    if (validSections.includes(hash)) {
        showSection(hash);
    } else {
        // Por padrão, mostrar a seção inicial
        showSection('inicio');
    }

    // Atualizar hash na URL sem recarregar a página
    navLinks.forEach(link => {
        link.addEventListener('click', function () {
            const sectionId = this.dataset.section;
            history.pushState(null, null, `#${sectionId}`);
        });
    });

    // Lidar com botões de voltar/avançar do navegador
    window.addEventListener('popstate', function () {
        const hash = window.location.hash.substring(1);
        if (validSections.includes(hash)) {
            showSection(hash);
        }
    });

    // Configurar menu responsivo inicial
    function setupResponsiveMenu() {
        if (window.innerWidth <= 500) {
            menuIcon.style.display = 'flex';
            navList.style.display = 'none';
            navList.style.maxHeight = '0';
            navList.style.opacity = '0';
            navList.style.transform = 'translateY(calc(100vw - 100px))';
            navList.style.transition = 'all 0.5s cubic-bezier(0.5, 0, 0.58, 1)';
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

    // Configurar inicialmente
    setupResponsiveMenu();

    // Atualizar ao redimensionar
    window.addEventListener('resize', setupResponsiveMenu);

    // Fechar menu ao clicar fora (apenas em mobile)
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