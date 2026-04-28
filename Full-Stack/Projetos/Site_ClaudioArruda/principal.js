// Sistema de Navegação SPA
class SPANavigation {
    constructor() {
        this.pages = document.querySelectorAll('[data-page]');
        this.navLinks = document.querySelectorAll('.nav-link');
        this.mobileMenuBtn = document.querySelector('.mobile-menu-btn');
        this.nav = document.querySelector('nav');
        this.overlay = document.querySelector('.overlay');
        this.liveRegion = document.getElementById('live-region');

        this.init();
    }

    init() {
        this.setupEventListeners();
        this.showInitialPage();
    }

    setupEventListeners() {
        // Mobile menu
        this.mobileMenuBtn.addEventListener('click', () => this.toggleMobileMenu());
        this.overlay.addEventListener('click', () => this.closeMobileMenu());

        // Navigation
        document.querySelectorAll('[data-nav]').forEach(btn => {
            btn.addEventListener('click', e => {
                e.preventDefault();
                this.showPage(e.currentTarget.dataset.nav);
            });
        });

        this.navLinks.forEach(link => {
            link.addEventListener('click', e => {
                e.preventDefault();
                this.showPage(link.dataset.target);
            });
        });

        // Hash change
        window.addEventListener('hashchange', () => {
            const id = (location.hash || '#home').replace('#', '');
            this.showPage(id);
        });

        // Keyboard navigation
        document.addEventListener('keydown', e => {
            if (e.key === 'Escape') {
                this.showPage('home');
                this.closeMobileMenu();
            }
        });
    }

    showInitialPage() {
        const initial = (location.hash || '#home').replace('#', '');
        this.showPage(initial);
    }

    showPage(id) {
        // Hide all pages
        this.pages.forEach(page => {
            page.hidden = true;
        });

        // Show target page
        const targetPage = document.getElementById(id);
        if (targetPage) {
            targetPage.hidden = false;

            // Update navigation
            this.navLinks.forEach(link => {
                link.classList.toggle('active', link.dataset.target === id);
                link.setAttribute('aria-current', link.dataset.target === id ? 'page' : 'false');
            });

            // Announce to screen readers
            this.announce(`Página ${this.getPageName(id)} carregada`);

            // Focus management
            this.moveFocusToPage(id);
        }

        this.closeMobileMenu();
        window.scrollTo(0, 0);
    }

    getPageName(id) {
        const pageNames = {
            'home': 'Início',
            'about': 'Sobre Nós',
            'services': 'Serviços',
            'team': 'Nossa Equipe',
            'consult': 'Marcar Consulta',
            'videos': 'Vídeos',
            'news': 'Notícias',
            'contato': 'Contato'
        };
        return pageNames[id] || id;
    }

    moveFocusToPage(pageId) {
        const page = document.getElementById(pageId);
        if (!page) return;

        const mainHeading = page.querySelector('h1, h2');
        if (mainHeading) {
            mainHeading.setAttribute('tabindex', '-1');
            mainHeading.focus();

            // Remove tabindex after focus
            setTimeout(() => {
                mainHeading.removeAttribute('tabindex');
            }, 1000);
        }
    }

    toggleMobileMenu() {
        const isExpanded = this.nav.classList.toggle('active');
        this.overlay.classList.toggle('active');
        this.mobileMenuBtn.setAttribute('aria-expanded', isExpanded);
    }

    closeMobileMenu() {
        this.nav.classList.remove('active');
        this.overlay.classList.remove('active');
        this.mobileMenuBtn.setAttribute('aria-expanded', 'false');
    }

    announce(message) {
        if (this.liveRegion) {
            this.liveRegion.textContent = message;
            setTimeout(() => {
                this.liveRegion.textContent = '';
            }, 1000);
        }
    }
}

// Sistema de Validação de Formulários
class FormValidator {
    constructor(formId, options = {}) {
        this.form = document.getElementById(formId);
        this.fields = {};
        this.isSubmitting = false;
        this.options = {
            showSuccessMessage: true,
            enableCharacterCount: true,
            ...options
        };

        if (this.form) {
            this.init();
        }
    }

    init() {
        this.setupFields();
        this.setupEventListeners();
        if (this.options.enableCharacterCount) {
            this.setupCharacterCounters();
        }
    }

    setupFields() {
        const fields = this.form.querySelectorAll('[name]');

        fields.forEach(field => {
            this.fields[field.name] = {
                element: field,
                rules: this.getValidationRules(field),
                isValid: null
            };

            // Validate initial state
            this.validateField(field.name);
        });
    }

    getValidationRules(field) {
        const rules = [];

        if (field.required) rules.push('required');
        if (field.type === 'email') rules.push('email');
        if (field.type === 'tel') rules.push('phone');
        if (field.hasAttribute('pattern')) rules.push('pattern');
        if (field.hasAttribute('minlength')) rules.push('minLength');
        if (field.hasAttribute('maxlength')) rules.push('maxLength');
        if (field.type === 'date') rules.push('date');
        if (field.type === 'checkbox') rules.push('checkbox');

        return rules;
    }

    setupEventListeners() {
        // Real-time validation
        this.form.addEventListener('input', (e) => {
            if (e.target.name && this.fields[e.target.name]) {
                this.validateField(e.target.name);
            }
        });

        // Blur validation
        this.form.addEventListener('focusout', (e) => {
            if (e.target.name && this.fields[e.target.name]) {
                this.validateField(e.target.name);
            }
        });

        // Form submission
        this.form.addEventListener('submit', (e) => {
            e.preventDefault();
            this.handleSubmit();
        });
    }

    setupCharacterCounters() {
        const textareas = this.form.querySelectorAll('textarea[maxlength]');

        textareas.forEach(textarea => {
            const counterId = textarea.id + '-counter';
            let counter = document.getElementById(counterId);

            if (!counter) {
                counter = document.createElement('span');
                counter.id = counterId;
                counter.className = 'caracter-count';
                textarea.parentNode.appendChild(counter);
            }

            textarea.addEventListener('input', () => {
                this.updateCharacterCounter(textarea, counter);
            });

            // Initial update
            this.updateCharacterCounter(textarea, counter);
        });
    }

    updateCharacterCounter(textarea, counter) {
        const length = textarea.value.length;
        const maxLength = textarea.maxLength;
        counter.textContent = `${length} / ${maxLength}`;

        // Visual feedback
        const remaining = maxLength - length;
        if (remaining < 50) {
            counter.style.color = '#dc3545';
        } else if (remaining < 100) {
            counter.style.color = '#ffc107';
        } else {
            counter.style.color = '#6c757d';
        }
    }

    validateField(fieldName) {
        const field = this.fields[fieldName];
        if (!field) return true;

        const value = field.element.value.trim();
        let isValid = true;
        let errorMessage = '';

        // Apply validation rules
        for (const rule of field.rules) {
            const result = this.applyValidationRule(rule, value, field.element);
            if (!result.isValid) {
                isValid = false;
                errorMessage = result.message;
                break;
            }
        }

        // Update field state
        this.updateFieldState(fieldName, isValid, errorMessage);
        field.isValid = isValid;

        return isValid;
    }

    applyValidationRule(rule, value, element) {
        switch (rule) {
            case 'required':
                return this.validateRequired(value, element.type);
            case 'email':
                return this.validateEmail(value);
            case 'phone':
                return this.validatePhone(value);
            case 'pattern':
                return this.validatePattern(value, element.pattern);
            case 'minLength':
                return this.validateMinLength(value, element.minLength);
            case 'maxLength':
                return this.validateMaxLength(value, element.maxLength);
            case 'date':
                return this.validateDate(value, element.min);
            case 'checkbox':
                return this.validateCheckbox(element.checked);
            default:
                return { isValid: true, message: '' };
        }
    }

    validateRequired(value, type) {
        let isValid = false;

        if (type === 'checkbox') {
            isValid = value;
        } else {
            isValid = value.length > 0;
        }

        return {
            isValid,
            message: 'Este campo é obrigatório'
        };
    }

    validateEmail(value) {
        if (!value) return { isValid: true, message: '' };

        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const isValid = emailRegex.test(value);
        return {
            isValid,
            message: 'Por favor, insira um e-mail válido'
        };
    }

    validatePhone(value) {
        if (!value) return { isValid: true, message: '' };

        const phoneRegex = /(\(?\d{2}\)?\s?)?\d{4,5}-?\d{4}/;
        const isValid = phoneRegex.test(value);
        return {
            isValid,
            message: 'Por favor, insira um telefone válido'
        };
    }

    validatePattern(value, pattern) {
        if (!value) return { isValid: true, message: '' };

        try {
            const regex = new RegExp(pattern);
            const isValid = regex.test(value);
            return {
                isValid,
                message: 'Formato inválido'
            };
        } catch (e) {
            return { isValid: true, message: '' };
        }
    }

    validateMinLength(value, minLength) {
        if (!value) return { isValid: true, message: '' };

        const isValid = value.length >= parseInt(minLength);
        return {
            isValid,
            message: `Mínimo ${minLength} caracteres`
        };
    }

    validateMaxLength(value, maxLength) {
        if (!value) return { isValid: true, message: '' };

        const isValid = value.length <= parseInt(maxLength);
        return {
            isValid,
            message: `Máximo ${maxLength} caracteres`
        };
    }

    validateDate(value, minDate) {
        if (!value) return { isValid: true, message: '' };

        const selectedDate = new Date(value);
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const isValid = selectedDate >= today;
        return {
            isValid,
            message: 'Selecione uma data futura'
        };
    }

    validateCheckbox(checked) {
        return {
            isValid: checked,
            message: 'Você deve concordar com os termos'
        };
    }

    updateFieldState(fieldName, isValid, errorMessage) {
        const field = this.fields[fieldName];
        const feedbackElement = document.getElementById(`${field.element.id}-error`);

        // Update CSS classes
        field.element.classList.remove('is-valid', 'is-invalid');
        field.element.classList.add(isValid ? 'is-valid' : 'is-invalid');

        // Update ARIA
        field.element.setAttribute('aria-invalid', !isValid);

        // Update error message
        if (feedbackElement) {
            feedbackElement.textContent = errorMessage;
            feedbackElement.hidden = isValid;
        }
    }

    validateAllFields() {
        let isFormValid = true;

        for (const fieldName in this.fields) {
            const fieldIsValid = this.validateField(fieldName);
            if (!fieldIsValid) {
                isFormValid = false;

                // Focus first invalid field
                if (isFormValid === false) {
                    this.fields[fieldName].element.focus();
                    break;
                }
            }
        }

        return isFormValid;
    }

    async handleSubmit() {
        if (this.isSubmitting) return;

        // Validate all fields
        const isFormValid = this.validateAllFields();

        if (!isFormValid) {
            this.showError('Por favor, corrija os erros no formulário antes de enviar.');
            return;
        }

        // Submit form
        await this.submitForm();
    }

    // No seu principal.js, na classe FormValidator:
    async submitForm() {
        console.log('🚀 Iniciando envio do formulário...');

        this.isSubmitting = true;
        this.setSubmitButtonState(true);

        try {
            // Obter dados do formulário
            const formData = new FormData(this.form);
            const data = Object.fromEntries(formData);

            console.log('📤 Enviando dados:', data);

            // Fazer a requisição
            const response = await fetch('process-form.php', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(data)
            });

            console.log('📥 Resposta recebida. Status:', response.status);

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`);
            }

            const result = await response.json();
            console.log('✅ Sucesso! Resultado:', result);

            // Mostrar mensagem de sucesso
            this.showSuccess();

            // Limpar formulário
            this.form.reset();

            // Resetar validação
            this.resetValidation();

        } catch (error) {
            console.error('❌ Erro completo:', error);
            this.showError('Erro de conexão: ' + error.message);
        } finally {
            this.isSubmitting = false;
            this.setSubmitButtonState(false);
        }
    }

    // NOVA FUNÇÃO PARA NETLIFY
    async submitToNetlify(formData) {
        // Simulação de envio bem-sucedido
        await new Promise(resolve => setTimeout(resolve, 1500));

        return {
            success: true,
            message: 'Mensagem enviada com sucesso! Entraremos em contato em breve.'
        };
    }

    getFormData() {
        const data = {};
        for (const fieldName in this.fields) {
            const field = this.fields[fieldName];
            if (field.element.type === 'checkbox') {
                data[fieldName] = field.element.checked;
            } else {
                data[fieldName] = field.element.value.trim();
            }
        }
        return data;
    }

    async simulateApiCall(data) {
        // Simulate network delay
        await new Promise(resolve => setTimeout(resolve, 2000));

        // Simulate random failure (remove in production)
        if (Math.random() < 0.1) {
            throw new Error('Simulated network error');
        }

        // In production, replace with:
        // const response = await fetch('/api/submit', {
        //     method: 'POST',
        //     headers: { 'Content-Type': 'application/json' },
        //     body: JSON.stringify(data)
        // });
        // 
        // if (!response.ok) throw new Error('Server error');

        return { success: true, message: 'Form submitted successfully' };
    }

    setSubmitButtonState(loading) {
        const submitBtn = this.form.querySelector('button[type="submit"]');
        if (!submitBtn) return;

        if (loading) {
            submitBtn.disabled = true;
            submitBtn.classList.add('btn-loading');
            const originalText = submitBtn.innerHTML;
            submitBtn.setAttribute('data-original-text', originalText);
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Enviando...';
        } else {
            submitBtn.disabled = false;
            submitBtn.classList.remove('btn-loading');
            const originalText = submitBtn.getAttribute('data-original-text');
            if (originalText) {
                submitBtn.innerHTML = originalText;
            }
        }
    }

    showSuccess() {
        if (!this.options.showSuccessMessage) return;

        const successElement = document.getElementById(`form-success-${this.form.id.replace('form-', '')}`);
        if (successElement) {
            successElement.hidden = false;
            successElement.focus();

            // Hide after 5 seconds
            setTimeout(() => {
                successElement.hidden = true;
            }, 5000);
        }
    }

    showError(message) {
        // Create temporary error message
        const errorDiv = document.createElement('div');
        errorDiv.className = 'alert alert-danger';
        errorDiv.setAttribute('role', 'alert');
        errorDiv.innerHTML = `<i class="fas fa-exclamation-triangle"></i> ${message}`;

        this.form.insertBefore(errorDiv, this.form.firstChild);

        // Remove after 5 seconds
        setTimeout(() => {
            errorDiv.remove();
        }, 5000);
    }

    resetValidation() {
        for (const fieldName in this.fields) {
            const field = this.fields[fieldName];
            field.element.classList.remove('is-valid', 'is-invalid');
            field.element.setAttribute('aria-invalid', 'false');

            const feedbackElement = document.getElementById(`${field.element.id}-error`);
            if (feedbackElement) {
                feedbackElement.hidden = true;
            }
        }
    }
}

// Sistema de Gerenciamento de Estado
class AppState {
    constructor() {
        this.storageKey = 'defesa_agricultor';
        this.init();
    }

    init() {
        // Set current year in footer
        document.getElementById('year').textContent = new Date().getFullYear();
    }

    load(key, fallback = null) {
        try {
            const data = JSON.parse(localStorage.getItem(this.storageKey)) || {};
            return data[key] !== undefined ? data[key] : fallback;
        } catch (e) {
            return fallback;
        }
    }

    save(key, value) {
        try {
            const data = JSON.parse(localStorage.getItem(this.storageKey)) || {};
            data[key] = value;
            localStorage.setItem(this.storageKey, JSON.stringify(data));
        } catch (e) {
            console.error('Error saving to localStorage:', e);
        }
    }
}

// Sistema de Animações
class AnimationManager {
    constructor() {
        this.observer = new IntersectionObserver(this.handleIntersection.bind(this), {
            threshold: 0.1,
            rootMargin: '0px 0px -50px 0px'
        });
    }

    init() {
        this.observeElements('.stat-item');
        this.observeElements('.service-card');
        this.observeElements('.team-card');
    }

    observeElements(selector) {
        const elements = document.querySelectorAll(selector);
        elements.forEach(el => this.observer.observe(el));
    }

    handleIntersection(entries, observer) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
                observer.unobserve(entry.target);
            }
        });
    }
}

// WhatsApp Integration
class WhatsAppManager {
    constructor() {
        this.button = document.getElementById('icon_whatsapp');
        this.init();
    }

    init() {
        if (this.button) {
            this.button.addEventListener('click', () => this.openWhatsApp());
        }
    }

    openWhatsApp() {
        const phone = '5544999173900';
        const message = 'Olá! Gostaria de mais informações sobre os serviços jurídicos.';
        const url = `https://wa.me/${phone}?text=${encodeURIComponent(message)}`;
        window.open(url, '_blank');
    }
}

// Content Management
class ContentManager {
    constructor() {
        this.init();
    }

    init() {
        this.renderPosts();
        this.renderVideos();
        this.renderConsults();
    }

    renderPosts() {
        const posts = this.load('posts', []);
        const el = document.getElementById('posts-list');
        if (!el) return;

        if (!posts.length) {
            el.innerHTML = '<p class="muted">Ainda não há publicações.</p>';
            return;
        }

        el.innerHTML = posts.map(post => `
            <article class="list-item">
                <h3>${this.escapeHtml(post.title)}</h3>
                <div class="meta">${new Date(post.created).toLocaleString('pt-BR')}</div>
                ${post.summary ? `<p>${this.escapeHtml(post.summary)}</p>` : ''}
                <div>${post.body.replace(/\n/g, '<br>')}</div>
            </article>
        `).join('');
    }

    renderVideos() {
        const videos = this.load('videos', []);
        const el = document.getElementById('videos-grid');
        if (!el) return;

        if (!videos.length) {
            el.innerHTML = '<p class="muted">Nenhum vídeo publicado.</p>';
            return;
        }

        el.innerHTML = videos.map(video => `
            <div class="video-item">
                <h4>${this.escapeHtml(video.title || 'Vídeo')}</h4>
                <div class="meta">${new Date(video.created).toLocaleString('pt-BR')}</div>
                <div class="video-container">
                    ${this.getVideoEmbed(video.url)}
                </div>
            </div>
        `).join('');
    }

    renderConsults() {
        const consults = this.load('consults', []);
        const container = document.getElementById('consult-list');
        if (!container) return;

        if (consults.length === 0) {
            container.innerHTML = '<p class="muted">Nenhum pedido de consulta armazenado localmente.</p>';
        } else {
            container.innerHTML = consults.map(consult => `
                <div class="consult-item">
                    <strong>${this.escapeHtml(consult.name)}</strong>
                    <div class="meta">${consult.phone} — ${consult.date || 'Data não informada'}</div>
                    ${consult.msg ? `<p>${this.escapeHtml(consult.msg)}</p>` : ''}
                    <small>Enviado em: ${new Date(consult.created).toLocaleString('pt-BR')}</small>
                </div>
            `).join('');
        }
    }

    getVideoEmbed(url) {
        try {
            const urlObj = new URL(url);
            if (urlObj.hostname.includes('youtube.com') || urlObj.hostname.includes('youtu.be')) {
                const videoId = urlObj.searchParams.get('v') || urlObj.pathname.split('/').pop();
                return `<iframe width="100%" height="400" src="https://www.youtube.com/embed/${videoId}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;
            }
            return `<p><a href="${url}" target="_blank">Assistir vídeo</a></p>`;
        } catch (e) {
            return `<p><a href="${url}" target="_blank">Assistir vídeo</a></p>`;
        }
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    load(key, fallback) {
        try {
            return JSON.parse(localStorage.getItem(key)) || fallback;
        } catch (e) {
            return fallback;
        }
    }
}

// Inicialização da Aplicação
class App {
    constructor() {
        this.navigation = new SPANavigation();
        this.state = new AppState();
        this.animations = new AnimationManager();
        this.whatsapp = new WhatsAppManager();
        this.content = new ContentManager();
        this.formValidators = [];

        this.init();
    }

    init() {
        this.setupFormValidators();
        this.setupPerformanceOptimizations();
        this.setupErrorHandling();
        this.initializeSampleData();

        // Initialize animations after a short delay
        setTimeout(() => {
            this.animations.init();
        }, 100);
    }

    setupFormValidators() {
        // Consult form validator
        const consultValidator = new FormValidator('form-consult', {
            showSuccessMessage: true,
            enableCharacterCount: true
        });
        this.formValidators.push(consultValidator);

        // Contact form validator
        const contactValidator = new FormValidator('form-contact', {
            showSuccessMessage: true,
            enableCharacterCount: true
        });
        this.formValidators.push(contactValidator);
    }

    setupPerformanceOptimizations() {
        // Debounced resize handler
        let resizeTimeout;
        window.addEventListener('resize', () => {
            clearTimeout(resizeTimeout);
            resizeTimeout = setTimeout(() => {
                this.handleResize();
            }, 250);
        });

        // Lazy loading for images
        if ('IntersectionObserver' in window) {
            this.setupLazyLoading();
        }
    }

    setupLazyLoading() {
        const lazyImages = document.querySelectorAll('img[loading="lazy"]');
        const imageObserver = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const img = entry.target;
                    img.src = img.dataset.src || img.src;
                    imageObserver.unobserve(img);
                }
            });
        });

        lazyImages.forEach(img => imageObserver.observe(img));
    }

    handleResize() {
        const width = window.innerWidth;

        // Handle very small screens
        if (width <= 315) {
            this.showMobileError();
            return;
        }

        // Reinitialize animations on resize
        this.animations.init();
    }

    showMobileError() {
        document.body.innerHTML = `
            <div style="text-align: center; padding: 20px; font-family: Arial, sans-serif;">
                <h1>Dispositivo Não Suportado</h1>
                <p>Desculpe, mas esta página não pode ser exibida adequadamente em telas muito pequenas.</p>
                <p>Por favor, acesse este site em um dispositivo com uma tela maior para uma melhor experiência.</p>
                <p><a href="javascript:history.back()">Voltar</a></p>
            </div>
        `;
    }

    setupErrorHandling() {
        // Network status monitoring
        window.addEventListener('online', () => {
            console.log('Application is online');
        });

        window.addEventListener('offline', () => {
            console.log('Application is offline');
            this.showOfflineMessage();
        });

        // Global error handler
        window.addEventListener('error', (e) => {
            console.error('Global error:', e.error);
        });

        // Unhandled promise rejections
        window.addEventListener('unhandledrejection', (e) => {
            console.error('Unhandled promise rejection:', e.reason);
        });
    }

    showOfflineMessage() {
        // Could implement offline functionality here
        console.log('App is offline - some features may be limited');
    }

    initializeSampleData() {
        // Initialize sample posts if none exist
        if (!localStorage.getItem('posts')) {
            const samplePosts = [{
                title: 'Bem-vindo ao Defesa do Agricultor',
                summary: 'Conheça nossos serviços especializados em direito agrário',
                body: 'Há mais de 15 anos defendendo os direitos do produtor rural brasileiro. Oferecemos assessoria jurídica completa para todas as necessidades do agronegócio.\n\nNossa equipe de advogados especializados está pronta para auxiliar você em questões como execuções judiciais, penhoras de máquinas agrícolas, parcelamento de dívidas e muito mais.',
                created: Date.now()
            }];
            localStorage.setItem('posts', JSON.stringify(samplePosts));
            this.content.renderPosts();
        }

        // Initialize sample consultation if none exist (for demo)
        if (!localStorage.getItem('consults') && window.location.hash === '#consult') {
            const sampleConsults = [{
                name: 'João Silva',
                phone: '(44) 99999-9999',
                date: '2024-02-15',
                msg: 'Gostaria de agendar uma consulta sobre penhora de trator',
                created: Date.now() - 86400000 // 1 day ago
            }];
            localStorage.setItem('consults', JSON.stringify(sampleConsults));
            this.content.renderConsults();
        }
    }
}

// Inicializar aplicação quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', () => {
    new App();
});

if ('serviceWorker' in navigator) {
    window.addEventListener('load', () => {
        navigator.serviceWorker.register('/sw.js')
            .then(registration => {
                console.log('SW registered: ', registration);
            })
            .catch(registrationError => {
                console.log('SW registration failed: ', registrationError);
            });
    });
}