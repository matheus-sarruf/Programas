// Funções auxiliares gerais

export class Ajuda {
    static detectarSO() {
        const userAgent = navigator.userAgent;
        
        if (userAgent.indexOf('Win') !== -1) return 'Windows';
        if (userAgent.indexOf('Mac') !== -1) return 'macOS';
        if (userAgent.indexOf('Linux') !== -1) return 'Linux';
        if (/Android/.test(userAgent)) return 'Android';
        if (/(iPhone|iPad|iPod)/.test(userAgent)) return 'iOS';
        
        return 'SO Desconhecido';
    }
    
    static formatarData(data = new Date()) {
        return data.toLocaleString('pt-BR', {
            dateStyle: 'short',
            timeStyle: 'medium'
        });
    }
    
    static debounce(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    }
    
    static throttle(func, limit) {
        let inThrottle;
        return function(...args) {
            if (!inThrottle) {
                func.apply(this, args);
                inThrottle = true;
                setTimeout(() => inThrottle = false, limit);
            }
        };
    }
    
    static randomBetween(min, max) {
        return Math.random() * (max - min) + min;
    }
    
    static formatarPorcentagem(valor, decimais = 0) {
        return `${(valor * 100).toFixed(decimais)}%`;
    }
    
    static copiarTexto(texto) {
        navigator.clipboard.writeText(texto);
    }
    
    static sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
}