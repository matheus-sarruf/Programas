const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = 3000;
const JSON_FILE_PATH = path.join(__dirname, 'api.json');

// O index.html está na pasta anterior
const HTML_PATH = path.join(__dirname, '..', 'index.html');

const mimeTypes = {
    '.html': 'text/html',
    '.js': 'application/javascript',
    '.css': 'text/css',
    '.json': 'application/json',
    '.png': 'image/png',
    '.jpg': 'image/jpeg',
    '.gif': 'image/gif',
    '.mp3': 'audio/mpeg',
    '.wav': 'audio/wav',
    '.cur': 'image/x-icon'
};

const server = http.createServer((req, res) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

    if (req.method === 'OPTIONS') {
        res.writeHead(200);
        res.end();
        return;
    }

    console.log(req.method, req.url);

    // Rotas da API
    if (req.url === '/alterar_tema' && req.method === 'POST') {
        let body = '';
        req.on('data', chunk => body += chunk);
        req.on('end', () => {
            try {
                const { tema } = JSON.parse(body);
                const dados = JSON.parse(fs.readFileSync(JSON_FILE_PATH, 'utf8'));
                dados.preferenciasTema.tema = tema;
                fs.writeFileSync(JSON_FILE_PATH, JSON.stringify(dados, null, 2));
                res.writeHead(200, { 'Content-Type': 'application/json' });
                res.end(JSON.stringify({ success: true, novoTema: tema }));
            } catch (error) {
                res.writeHead(500);
                res.end(JSON.stringify({ error: 'Erro interno' }));
            }
        });
        return;
    }

    if (req.url === '/obter_tema' && req.method === 'GET') {
        try {
            const dados = JSON.parse(fs.readFileSync(JSON_FILE_PATH, 'utf8'));
            res.writeHead(200, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify(dados.preferenciasTema));
        } catch (error) {
            res.writeHead(500);
            res.end(JSON.stringify({ error: 'Erro ao obter tema' }));
        }
        return;
    }

    if (req.url === '/obter_cursor' && req.method === 'GET') {
        try {
            const dados = JSON.parse(fs.readFileSync(JSON_FILE_PATH, 'utf8'));
            res.writeHead(200, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify(dados.cursor));
        } catch (error) {
            res.writeHead(500);
            res.end(JSON.stringify({ error: 'Erro ao obter cursor' }));
        }
        return;
    }

    if (req.url === '/obter_sons' && req.method === 'GET') {
        try {
            const dados = JSON.parse(fs.readFileSync(JSON_FILE_PATH, 'utf8'));
            res.writeHead(200, { 'Content-Type': 'application/json' });
            res.end(JSON.stringify(dados.sons));
        } catch (error) {
            res.writeHead(500);
            res.end(JSON.stringify({ error: 'Erro ao obter sons' }));
        }
        return;
    }

    // Servir index.html
    if (req.url === '/' || req.url === '/index.html') {
        fs.readFile(HTML_PATH, (err, data) => {
            if (err) {
                console.log('Erro ao ler index.html:', err);
                res.writeHead(404);
                res.end('index.html nao encontrado em: ' + HTML_PATH);
                return;
            }
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.end(data);
        });
        return;
    }

    // Servir outros arquivos estaticos (CSS, JS, etc)
    let filePath = path.join(__dirname, '..', req.url);

    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(404);
            res.end('Arquivo nao encontrado: ' + req.url);
            return;
        }

        const ext = path.extname(filePath);
        const contentType = mimeTypes[ext] || 'text/plain';
        res.writeHead(200, { 'Content-Type': contentType });
        res.end(data);
    });
});

server.listen(PORT, () => {
    console.log('Servidor rodando em http://localhost:' + PORT);
});

server.on('error', (error) => {
    if (error.code === 'EADDRINUSE') {
        console.error('Porta ' + PORT + ' ja esta em uso');
    } else {
        console.error('Erro no servidor:', error);
    }
});