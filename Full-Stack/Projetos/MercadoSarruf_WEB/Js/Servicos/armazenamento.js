// Servicos/armazenamento.js
export class ServicoArmazenamento {
    static get(key, defaultValue = null) {
        try {
            const value = localStorage.getItem(key);
            return value !== null ? value : defaultValue;
        } catch (error) {
            console.error('Erro ao ler localStorage:', error);
            return defaultValue;
        }
    }

    static set(key, value) {
        try {
            localStorage.setItem(key, value);
        } catch (error) {
            console.error('Erro ao salvar no localStorage:', error);
        }
    }

    static remove(key) {
        try {
            localStorage.removeItem(key);
        } catch (error) {
            console.error('Erro ao remover do localStorage:', error);
        }
    }

    static clear() {
        try {
            localStorage.clear();
        } catch (error) {
            console.error('Erro ao limpar localStorage:', error);
        }
    }

    static obterTema() {
        const tema = this.get('temaSelecionado', 'Tema_Escuro');
        if (tema === 'Tema_Moderno_Claro' || tema === 'Tema_Moderno_Escuro') {
            return 'Tema_Escuro';
        }
        return tema;
    }

    static salvarTema(tema) {
        this.set('temaSelecionado', tema);
    }

    static obterVolume() {
        return parseFloat(this.get('volumeMusica', '0.5'));
    }

    static salvarVolume(volume) {
        this.set('volumeMusica', volume);
    }

    static obterPausar() {
        return this.get('pausar', 'false') === 'true';
    }

    static salvarPausar(pausar) {
        this.set('pausar', pausar.toString());
    }

    static obterEclaro() {
        return this.get('eClaro', 'true') === 'true';
    }

    static salvarEclaro(eClaro) {
        this.set('eClaro', eClaro.toString());
    }
}