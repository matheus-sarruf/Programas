const express = require('express');
const multer = require('multer');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(express.json());
app.use('/uploads', express.static('uploads'));
app.use(express.static('public')); 
app.use('/fotos', express.static('fotos'));

// Configuração do multer para salvar as imagens
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    const year = req.body.yearClass || 'outros';
    const dir = path.join(__dirname, 'uploads', year);
    if (!fs.existsSync(dir)) fs.mkdirSync(dir, { recursive: true });
    cb(null, dir);
  },
  filename: (req, file, cb) => {
    const unique = Date.now() + '-' + Math.round(Math.random() * 1E9);
    const ext = path.extname(file.originalname);
    cb(null, unique + ext);
  }
});
const upload = multer({ storage });

// Caminho do arquivo JSON
const DATA_FILE = path.join(__dirname, 'data', 'students.json');

// Função para ler dados
const readData = () => {
  if (!fs.existsSync(DATA_FILE)) {
    fs.writeFileSync(DATA_FILE, JSON.stringify([]));
    return [];
  }
  const data = fs.readFileSync(DATA_FILE, 'utf8');
  return JSON.parse(data);
};

// Função para escrever dados
const writeData = (data) => {
  fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 2));
};

// ---------- ROTAS DA API ----------

// GET /students – lista todos ou filtra por turma
app.get('/students', (req, res) => {
  const { year } = req.query;
  let students = readData();
  if (year) {
    students = students.filter(s => s.yearClass === year);
  }
  res.json(students);
});

// POST /students – adiciona novo estudante (com upload de imagem)
app.post('/students', upload.single('image'), (req, res) => {
  const { name, yearClass } = req.body;
  if (!name || !yearClass) {
    return res.status(400).json({ error: 'Nome e turma são obrigatórios' });
  }
  const students = readData();
  const newId = students.length ? Math.max(...students.map(s => s.id)) + 1 : 1;
  let imageUrl = '';
  if (req.file) {
    imageUrl = `/uploads/${yearClass}/${req.file.filename}`;
  } else if (req.body.imageUrl) {
    imageUrl = req.body.imageUrl; // mantém URL externa se enviada
  }
  const newStudent = {
    id: newId,
    name,
    yearClass,
    image: imageUrl || 'https://placehold.co/400x240?text=Estudante+IFPR'
  };
  students.push(newStudent);
  writeData(students);
  res.status(201).json(newStudent);
});

// PUT /students/:id – atualiza um estudante (nome, turma, imagem)
app.put('/students/:id', upload.single('image'), (req, res) => {
  const id = parseInt(req.params.id);
  const { name, yearClass } = req.body;
  let students = readData();
  const index = students.findIndex(s => s.id === id);
  if (index === -1) return res.status(404).json({ error: 'Estudante não encontrado' });

  // Atualiza campos
  if (name) students[index].name = name;
  if (yearClass) students[index].yearClass = yearClass;
  if (req.file) {
    // Remove imagem antiga se existir (opcional)
    students[index].image = `/uploads/${yearClass || students[index].yearClass}/${req.file.filename}`;
  } else if (req.body.imageUrl) {
    students[index].image = req.body.imageUrl;
  }
  writeData(students);
  res.json(students[index]);
});

// DELETE /students/:id – remove um estudante e sua imagem
app.delete('/students/:id', (req, res) => {
  const id = parseInt(req.params.id);
  let students = readData();
  const index = students.findIndex(s => s.id === id);
  if (index === -1) return res.status(404).json({ error: 'Estudante não encontrado' });

  // Remove a imagem do disco (se for local)
  const student = students[index];
  if (student.image && !student.image.startsWith('http')) {
    const imagePath = path.join(__dirname, student.image);
    if (fs.existsSync(imagePath)) fs.unlinkSync(imagePath);
  }
  students.splice(index, 1);
  writeData(students);
  res.json({ message: 'Removido com sucesso' });
});

// DELETE /students?year=... – remove todos de uma turma
app.delete('/students', (req, res) => {
  const { year } = req.query;
  if (!year) return res.status(400).json({ error: 'Parâmetro year obrigatório' });
  let students = readData();
  const toRemove = students.filter(s => s.yearClass === year);
  // Remove imagens
  toRemove.forEach(s => {
    if (s.image && !s.image.startsWith('http')) {
      const imagePath = path.join(__dirname, s.image);
      if (fs.existsSync(imagePath)) fs.unlinkSync(imagePath);
    }
  });
  students = students.filter(s => s.yearClass !== year);
  writeData(students);
  res.json({ message: `Removidos ${toRemove.length} estudantes do ${year}` });
});

// POST /students/promote – promove estudantes de uma turma para outra
app.post('/students/promote', (req, res) => {
  const { fromClass, toClass } = req.body;
  if (!fromClass || !toClass) {
    return res.status(400).json({ error: 'fromClass e toClass são obrigatórios' });
  }
  let students = readData();
  let promoted = 0;
  students.forEach(s => {
    if (s.yearClass === fromClass) {
      s.yearClass = toClass;
      promoted++;
    }
  });
  writeData(students);
  res.json({ message: `${promoted} estudantes promovidos de ${fromClass} para ${toClass}` });
});

// Inicia o servidor
app.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});