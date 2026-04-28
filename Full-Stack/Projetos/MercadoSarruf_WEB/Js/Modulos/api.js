// Comunicação com o servidor

import { CONFIG } from '../Configuracoes/constantes.js';

export class ServicoApi {
    static async request(endpoint, method = 'GET', data = null) {
        try {
            const options = {
                method,
                headers: { 'Content-Type': 'application/json' }
            };
            
            if (data && (method === 'POST' || method === 'PUT')) {
                options.body = JSON.stringify(data);
            }
            
            const response = await fetch(`${CONFIG.API_URL}${endpoint}`, options);
            
            if (!response.ok) {
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }
            
            return await response.json();
        } catch (error) {
            console.error(`Erro na API (${endpoint}):`, error);
            throw error;
        }
    }
    
    static async obterTema() {
        return this.request('/obter_tema');
    }
    
    static async obterCursor() {
        return this.request('/obter_cursor');
    }
    
    static async obterSons() {
        return this.request('/obter_sons');
    }
    
    static async alterarTema(tema) {
        return this.request('/alterar_tema', 'POST', { tema });
    }
    
    static async carregarTodosDados() {
        try {
            const [tema, cursor, sons] = await Promise.all([
                this.obterTema(),
                this.obterCursor(),
                this.obterSons()
            ]);
            
            return { tema, cursor, sons };
        } catch (error) {
            console.error('Erro ao carregar dados da API:', error);
            throw error;
        }
    }
}