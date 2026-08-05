// ================================================
// CONFIGURAÇÃO: URL do servidor
// ================================================
const API_BASE_URL = '';

// ================================================
// VARIÁVEIS GLOBAIS
// ================================================
let currentStudents = [];
let activeFilter = "todos";
let nextId = 1;

// ================================================
// DOM ELEMENTOS
// ================================================
const galeriaDiv = document.getElementById("galeriaContainer");
const filtrosBtns = document.querySelectorAll("#filtrosContainer button");
const studentCountSpan = document.getElementById("studentCount");
const addFormPanel = document.getElementById("addFormPanel");
const showFormBtn = document.getElementById("showFormBtn");
const confirmAddBtn = document.getElementById("confirmAddBtn");
const studentNameInput = document.getElementById("studentName");
const studentYearSelect = document.getElementById("studentYear");
const imageUrlInput = document.getElementById("imageUrl");
const imageUploadInput = document.getElementById("imageUpload");
const dynamicActionBtn = document.getElementById("dynamicActionBtn");
const modal = document.getElementById("customModal");
const modalTitle = document.getElementById("modalTitle");
const modalMessage = document.getElementById("modalMessage");
const modalConfirmBtn = document.getElementById("modalConfirmBtn");
const modalCancelBtn = document.getElementById("modalCancelBtn");

// ================================================
// FUNÇÕES AUXILIARES
// ================================================
function getYearLabel(yearClass) {
    const map = { "1ano": "1º Ano", "2ano": "2º Ano", "3ano": "3º Ano", "4ano": "4º Ano" };
    return map[yearClass] || "Turma";
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>]/g, function (m) {
        if (m === '&') return '&amp;';
        if (m === '<') return '&lt;';
        if (m === '>') return '&gt;';
        return m;
    });
}

function showModal(title, message, onConfirm) {
    modalTitle.innerText = title;
    modalMessage.innerText = message;
    modal.style.display = "flex";
    const confirmHandler = () => {
        modal.style.display = "none";
        modalConfirmBtn.removeEventListener("click", confirmHandler);
        modalCancelBtn.removeEventListener("click", cancelHandler);
        if (onConfirm) onConfirm();
    };
    const cancelHandler = () => {
        modal.style.display = "none";
        modalConfirmBtn.removeEventListener("click", confirmHandler);
        modalCancelBtn.removeEventListener("click", cancelHandler);
    };
    modalConfirmBtn.addEventListener("click", confirmHandler);
    modalCancelBtn.addEventListener("click", cancelHandler);
}

// ================================================
// FUNÇÕES DE COMUNICAÇÃO COM O SERVIDOR (API)
// ================================================

// Buscar alunos do servidor
async function loadStudents() {
    try {
        const url = activeFilter === 'todos'
            ? `${API_BASE_URL}/students`
            : `${API_BASE_URL}/students?year=${activeFilter}`;
        const response = await fetch(url);
        const data = await response.json();
        currentStudents = data;
        nextId = currentStudents.length ? Math.max(...currentStudents.map(s => s.id)) + 1 : 1;
        renderGallery();
    } catch (error) {
        console.error("Erro ao carregar alunos:", error);
        alert("Não foi possível conectar ao servidor.");
    }
}

// Adicionar aluno
async function addStudentToServer(name, yearClass, imageBase64, file) {
    const formData = new FormData();
    formData.append('name', name);
    formData.append('yearClass', yearClass);

    if (file) {
        // Se selecionou um arquivo, envia o arquivo
        formData.append('image', file);
    } else if (imageBase64 && imageBase64.trim() !== '') {
        // Se colocou uma URL
        formData.append('imageUrl', imageBase64);
    }

    try {
        const response = await fetch(`${API_BASE_URL}/students`, {
            method: 'POST',
            body: formData
        });
        if (response.ok) {
            await loadStudents(); // Recarrega a lista
            return true;
        } else {
            const err = await response.json();
            alert("Erro ao adicionar: " + err.error);
            return false;
        }
    } catch (error) {
        console.error(error);
        alert("Erro de rede. O servidor está rodando?");
        return false;
    }
}

// Remover aluno
async function deleteStudentById(id) {
    const student = currentStudents.find(s => s.id === id);
    if (!student) return;
    showModal("Remover estudante", `Deseja remover ${student.name} da galeria?`, async () => {
        try {
            await fetch(`${API_BASE_URL}/students/${id}`, { method: 'DELETE' });
            await loadStudents();
        } catch (error) {
            console.error(error);
        }
    });
}

// Promover turma
async function promoteYear(fromClass, toClass, fromLabel, toLabel) {
    const studentsToPromote = currentStudents.filter(s => s.yearClass === fromClass);
    if (studentsToPromote.length === 0) {
        showModal("Nenhum estudante", `Não há estudantes no ${fromLabel} para promover.`, () => { });
        return;
    }
    showModal("Promover turma", `Promover ${studentsToPromote.length} estudante(s) do ${fromLabel} para o ${toLabel}?`, async () => {
        try {
            await fetch(`${API_BASE_URL}/students/promote`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fromClass, toClass })
            });
            activeFilter = "todos";
            await loadStudents();
            updateDynamicButton();
        } catch (error) {
            console.error(error);
        }
    });
}

// Excluir todos de uma turma
async function deleteAllByYear(yearClass, yearLabel) {
    const studentsToDelete = currentStudents.filter(s => s.yearClass === yearClass);
    if (studentsToDelete.length === 0) {
        showModal("Nenhum estudante", `Não há estudantes no ${yearLabel}.`, () => { });
        return;
    }
    showModal("Excluir todos", `Tem certeza que deseja excluir TODOS os ${studentsToDelete.length} estudante(s) do ${yearLabel}?`, async () => {
        try {
            await fetch(`${API_BASE_URL}/students?year=${yearClass}`, { method: 'DELETE' });
            if (activeFilter === yearClass) activeFilter = "todos";
            await loadStudents();
            updateDynamicButton();
        } catch (error) {
            console.error(error);
        }
    });
}

// ================================================
// FUNÇÕES DE RENDERIZAÇÃO (Visuais)
// ================================================

function updateDynamicButton() {
    if (activeFilter === "todos") {
        dynamicActionBtn.style.display = "none";
        return;
    }
    dynamicActionBtn.style.display = "inline-flex";
    if (activeFilter === "1ano") {
        dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 1º para 2º Ano';
        dynamicActionBtn.className = "btn-dynamic promote";
        dynamicActionBtn.onclick = () => promoteYear("1ano", "2ano", "1º ano", "2º ano");
    } else if (activeFilter === "2ano") {
        dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 2º para 3º Ano';
        dynamicActionBtn.className = "btn-dynamic promote";
        dynamicActionBtn.onclick = () => promoteYear("2ano", "3ano", "3º ano", "4º ano"); // Corrigi para 3->4
    } else if (activeFilter === "3ano") {
        dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 3º para 4º Ano';
        dynamicActionBtn.className = "btn-dynamic promote";
        dynamicActionBtn.onclick = () => promoteYear("3ano", "4ano", "3º ano", "4º ano");
    } else if (activeFilter === "4ano") {
        dynamicActionBtn.innerHTML = '<i class="fas fa-trash-alt"></i> Excluir todos do 4º Ano';
        dynamicActionBtn.className = "btn-dynamic delete-fourth";
        dynamicActionBtn.onclick = () => deleteAllByYear("4ano", "4º ano");
    }
}

function renderGallery() {
    // currentStudents já vem filtrado pela API, mas vamos manter a lógica local também
    let filtered = [...currentStudents];
    if (activeFilter !== "todos") {
        filtered = filtered.filter(student => student.yearClass === activeFilter);
    }
    studentCountSpan.innerHTML = `<i class="fas fa-user-graduate"></i> ${filtered.length} estudante(s) exibido(s) | Total: ${currentStudents.length}`;

    if (filtered.length === 0) {
        galeriaDiv.innerHTML = `<div style="grid-column:1/-1; text-align:center; padding:50px; background:var(--bg-surface); border-radius:32px;">
                                    <i class="fas fa-user-slash" style="font-size:3rem; opacity:0.5;"></i>
                                    <p>Nenhum estudante nesta turma. Adicione novos!</p>
                                </div>`;
        highlightActiveFilterButton();
        return;
    }
    let html = "";
    filtered.forEach(student => {
        const yearLabel = getYearLabel(student.yearClass);
        html += `<div class="card" data-id="${student.id}">
                    <img class="card-img" src="${student.image}" alt="${escapeHtml(student.name)}" onerror="this.onerror=null; this.src='https://placehold.co/400x240?text=Sem+Imagem';">
                    <div class="info">
                        <h3>${escapeHtml(student.name)}</h3>
                        <p>${yearLabel}</p>
                        <button class="delete-btn" data-id="${student.id}" title="Remover estudante">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </div>
                </div>`;
    });
    galeriaDiv.innerHTML = html;
    highlightActiveFilterButton();
    updateDynamicButton();
}

function highlightActiveFilterButton() {
    filtrosBtns.forEach(btn => {
        const filterVal = btn.getAttribute("data-filter");
        if ((activeFilter === "todos" && filterVal === "todos") || filterVal === activeFilter) {
            btn.classList.add("ativo");
        } else {
            btn.classList.remove("ativo");
        }
    });
}

function setFilter(filter) {
    activeFilter = filter;
    loadStudents(); // Recarrega os dados filtrados
}

// ================================================
// EVENTOS E INICIALIZAÇÃO
// ================================================

function setupDelegation() {
    galeriaDiv.addEventListener("click", (e) => {
        const deleteBtn = e.target.closest(".delete-btn");
        if (deleteBtn && deleteBtn.dataset.id) {
            const studentId = parseInt(deleteBtn.dataset.id);
            deleteStudentById(studentId);
            e.stopPropagation();
        }
    });
}

function initTheme() {
    const themeBtn = document.getElementById("themeToggle");
    function applyTheme(isDark) {
        if (isDark) {
            document.body.classList.add("dark");
            themeBtn.innerHTML = '<i class="fas fa-sun"></i> <span id="themeText">Modo Claro</span>';
            localStorage.setItem("ifpr_theme", "dark");
        } else {
            document.body.classList.remove("dark");
            themeBtn.innerHTML = '<i class="fas fa-moon"></i> <span id="themeText">Modo Escuro</span>';
            localStorage.setItem("ifpr_theme", "light");
        }
        const newSpan = themeBtn.querySelector("#themeText");
        if (newSpan) newSpan.innerText = isDark ? "Modo Claro" : "Modo Escuro";
    }
    const savedTheme = localStorage.getItem("ifpr_theme");
    if (savedTheme === "dark") applyTheme(true);
    else applyTheme(false);
    themeBtn.addEventListener("click", () => {
        const isDark = document.body.classList.contains("dark");
        applyTheme(!isDark);
    });
}

function initEventListeners() {
    filtrosBtns.forEach(btn => {
        btn.addEventListener("click", () => {
            const filterValue = btn.getAttribute("data-filter");
            setFilter(filterValue);
        });
    });

    showFormBtn.addEventListener("click", () => {
        if (addFormPanel.style.display === "none" || addFormPanel.style.display === "") {
            addFormPanel.style.display = "block";
            showFormBtn.innerHTML = '<i class="fas fa-minus-circle"></i> Fechar Formulário';
        } else {
            addFormPanel.style.display = "none";
            showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
        }
    });

    confirmAddBtn.addEventListener("click", async () => {
        const name = studentNameInput.value.trim();
        const year = studentYearSelect.value;
        const urlImage = imageUrlInput.value.trim();
        const file = imageUploadInput.files[0];

        if (!name) {
            showModal("Campo obrigatório", "Por favor, informe o nome do estudante.", () => { });
            return;
        }

        const success = await addStudentToServer(name, year, urlImage, file);
        if (success) {
            studentNameInput.value = "";
            imageUrlInput.value = "";
            imageUploadInput.value = "";
            addFormPanel.style.display = "none";
            showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
        }
    });
}

// INICIALIZAÇÃO PRINCIPAL
async function init() {
    await loadStudents(); // Carrega os dados do servidor
    setupDelegation();
    initTheme();
    initEventListeners();
}

init();