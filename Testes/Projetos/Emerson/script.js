let currentStudents = [];
let activeFilter = "todos";
let nextId = 1;
let pendingAction = null;

const galeriaDiv = document.getElementById("galeriaContainer");
const filtrosBtns = document.querySelectorAll("#filtrosContainer button");
const studentCountSpan = document.getElementById("studentCount");
const addFormPanel = document.getElementById("addFormPanel");
const showFormBtn = document.getElementById("showFormBtn");
const resetDefaultBtn = document.getElementById("resetDefaultBtn");
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

function handleImageUpload(file, callback) {
    if (!file) return callback(null);
    const reader = new FileReader();
    reader.onload = function (e) { callback(e.target.result); };
    reader.onerror = () => callback(null);
    reader.readAsDataURL(file);
}

function addStudent(name, yearClass, imageSrc) {
    if (!name || name.trim() === "") {
        showModal("Campo obrigatório", "Por favor, informe o nome do estudante.", () => { });
        return false;
    }
    const finalImage = (imageSrc && imageSrc.trim() !== "") ? imageSrc : "https://placehold.co/400x240?text=Estudante+IFPR";
    const newStudent = { id: nextId++, name: name.trim(), yearClass: yearClass, image: finalImage };
    currentStudents.push(newStudent);
    renderGallery();
    return true;
}

function deleteStudentById(id) {
    const student = currentStudents.find(s => s.id === id);
    if (!student) return;
    showModal("Remover estudante", `Deseja remover ${student.name} da galeria?`, () => {
        currentStudents = currentStudents.filter(s => s.id !== id);
        renderGallery();
    });
}

function deleteAllByYear(yearClass, yearLabel) {
    const studentsToDelete = currentStudents.filter(s => s.yearClass === yearClass);
    if (studentsToDelete.length === 0) {
        showModal("Nenhum estudante", `Não há estudantes no ${yearLabel}.`, () => { });
        return;
    }
    showModal("Excluir todos", `Tem certeza que deseja excluir TODOS os ${studentsToDelete.length} estudante(s) do ${yearLabel}? Esta ação é irreversível.`, () => {
        currentStudents = currentStudents.filter(s => s.yearClass !== yearClass);
        if (activeFilter === yearClass && currentStudents.filter(s => s.yearClass === yearClass).length === 0) {
            activeFilter = "todos";
            updateDynamicButton();
        }
        renderGallery();
    });
}

function promoteYear(fromClass, toClass, fromLabel, toLabel) {
    const studentsToPromote = currentStudents.filter(s => s.yearClass === fromClass);
    if (studentsToPromote.length === 0) {
        showModal("Nenhum estudante", `Não há estudantes no ${fromLabel} para promover.`, () => { });
        return;
    }
    showModal("Promover turma", `Promover ${studentsToPromote.length} estudante(s) do ${fromLabel} para o ${toLabel}? Eles manterão nome e foto.`, () => {
        for (let student of currentStudents) {
            if (student.yearClass === fromClass) {
                student.yearClass = toClass;
            }
        }
        activeFilter = "todos";
        updateDynamicButton();
        renderGallery();
    });
}

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
        dynamicActionBtn.onclick = () => promoteYear("2ano", "3ano", "2º ano", "3º ano");
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
    renderGallery();
}

function clearAllStudents() {
    if (currentStudents.length === 0) {
        showModal("Lista vazia", "Não há estudantes para remover.", () => { });
        return;
    }
    showModal("Limpar tudo", `Tem certeza que deseja remover TODOS os ${currentStudents.length} estudantes? Esta ação é irreversível.`, () => {
        currentStudents = [];
        nextId = 1;
        if (activeFilter !== "todos") {
            activeFilter = "todos";
        }
        renderGallery();
    });
}

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
    resetDefaultBtn.addEventListener("click", () => {
        clearAllStudents();
        if (addFormPanel.style.display === "block") {
            addFormPanel.style.display = "none";
            showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
        }
    });
    confirmAddBtn.addEventListener("click", () => {
        const name = studentNameInput.value.trim();
        const year = studentYearSelect.value;
        let providedImage = imageUrlInput.value.trim();
        const file = imageUploadInput.files[0];
        if (file) {
            handleImageUpload(file, (base64Image) => {
                if (base64Image) {
                    if (addStudent(name, year, base64Image)) {
                        studentNameInput.value = "";
                        imageUrlInput.value = "";
                        imageUploadInput.value = "";
                        addFormPanel.style.display = "none";
                        showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
                    }
                } else {
                    showModal("Erro", "Erro ao processar a imagem. Tente novamente.", () => { });
                }
            });
        } else {
            if (!providedImage) providedImage = "";
            if (addStudent(name, year, providedImage)) {
                studentNameInput.value = "";
                imageUrlInput.value = "";
                imageUploadInput.value = "";
                addFormPanel.style.display = "none";
                showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
            }
        }
    });
}

function init() {
    renderGallery();
    setupDelegation();
    initTheme();
    initEventListeners();
}

init();

// const DEFAULT_STUDENTS = [
//     { id: 1, name: "Ana Luiza", yearClass: "1ano", image: "fotos/1_ano/ANA_LUIZA-LINUX.jpeg" },
//     { id: 2, name: "Enzo Eiji", yearClass: "1ano", image: "fotos/1_ano/ENZO_EIJI-LINUX.jpeg" },
//     { id: 3, name: "Evellyn Estefani", yearClass: "1ano", image: "fotos/1_ano/EVELLYN_ESTEFANI-LINUX.jpeg" },
//     { id: 4, name: "Ana Julia dos Santos Ferreira", yearClass: "2ano", image: "fotos/2_ano/ANA_JULIA_DOS_SANTOS_FERREIRA.jpeg" },
//     { id: 5, name: "Ana Leticia Bessa", yearClass: "2ano", image: "fotos/2_ano/ANA_LETICIA_BESSA.jpeg" },
//     { id: 6, name: "Arthur Kuiava Fernandes", yearClass: "2ano", image: "fotos/2_ano/ARTHUR_KUIAVA_FERNANDES.jpeg" },
//     { id: 7, name: "Amanda da Silva Faria", yearClass: "3ano", image: "fotos/3_ano/AMANDA_DA_SILVA_FARIA.jpeg" },
//     { id: 8, name: "Amanda Eloá", yearClass: "3ano", image: "fotos/3_ano/AMANDA_ELOÁ.jpeg" },
//     { id: 9, name: "Ariane Matos da Silva", yearClass: "3ano", image: "fotos/3_ano/ARIANE_MATOS_DA_SILVA.jpeg" }
// ];

// let currentStudents = [];
// let activeFilter = "todos";
// let nextId = 10;
// let pendingAction = null;

// const galeriaDiv = document.getElementById("galeriaContainer");
// const filtrosBtns = document.querySelectorAll("#filtrosContainer button");
// const studentCountSpan = document.getElementById("studentCount");
// const addFormPanel = document.getElementById("addFormPanel");
// const showFormBtn = document.getElementById("showFormBtn");
// const resetDefaultBtn = document.getElementById("resetDefaultBtn");
// const confirmAddBtn = document.getElementById("confirmAddBtn");
// const studentNameInput = document.getElementById("studentName");
// const studentYearSelect = document.getElementById("studentYear");
// const imageUrlInput = document.getElementById("imageUrl");
// const imageUploadInput = document.getElementById("imageUpload");
// const dynamicActionBtn = document.getElementById("dynamicActionBtn");
// const modal = document.getElementById("customModal");
// const modalTitle = document.getElementById("modalTitle");
// const modalMessage = document.getElementById("modalMessage");
// const modalConfirmBtn = document.getElementById("modalConfirmBtn");
// const modalCancelBtn = document.getElementById("modalCancelBtn");

// function getYearLabel(yearClass) {
//     const map = { "1ano": "1º Ano", "2ano": "2º Ano", "3ano": "3º Ano", "4ano": "4º Ano" };
//     return map[yearClass] || "Turma";
// }

// function escapeHtml(str) {
//     if (!str) return '';
//     return str.replace(/[&<>]/g, function(m) {
//         if (m === '&') return '&amp;';
//         if (m === '<') return '&lt;';
//         if (m === '>') return '&gt;';
//         return m;
//     });
// }

// function showModal(title, message, onConfirm) {
//     modalTitle.innerText = title;
//     modalMessage.innerText = message;
//     modal.style.display = "flex";
//     const confirmHandler = () => {
//         modal.style.display = "none";
//         modalConfirmBtn.removeEventListener("click", confirmHandler);
//         modalCancelBtn.removeEventListener("click", cancelHandler);
//         if (onConfirm) onConfirm();
//     };
//     const cancelHandler = () => {
//         modal.style.display = "none";
//         modalConfirmBtn.removeEventListener("click", confirmHandler);
//         modalCancelBtn.removeEventListener("click", cancelHandler);
//     };
//     modalConfirmBtn.addEventListener("click", confirmHandler);
//     modalCancelBtn.addEventListener("click", cancelHandler);
// }

// function handleImageUpload(file, callback) {
//     if (!file) return callback(null);
//     const reader = new FileReader();
//     reader.onload = function(e) { callback(e.target.result); };
//     reader.onerror = () => callback(null);
//     reader.readAsDataURL(file);
// }

// function addStudent(name, yearClass, imageSrc) {
//     if (!name || name.trim() === "") {
//         showModal("Campo obrigatório", "Por favor, informe o nome do estudante.", () => {});
//         return false;
//     }
//     const finalImage = (imageSrc && imageSrc.trim() !== "") ? imageSrc : "https://placehold.co/400x240?text=Estudante+IFPR";
//     const newStudent = { id: nextId++, name: name.trim(), yearClass: yearClass, image: finalImage };
//     currentStudents.push(newStudent);
//     renderGallery();
//     return true;
// }

// function deleteStudentById(id) {
//     const student = currentStudents.find(s => s.id === id);
//     if (!student) return;
//     showModal("Remover estudante", `Deseja remover ${student.name} da galeria?`, () => {
//         currentStudents = currentStudents.filter(s => s.id !== id);
//         renderGallery();
//     });
// }

// function deleteAllByYear(yearClass, yearLabel) {
//     const studentsToDelete = currentStudents.filter(s => s.yearClass === yearClass);
//     if (studentsToDelete.length === 0) {
//         showModal("Nenhum estudante", `Não há estudantes no ${yearLabel}.`, () => {});
//         return;
//     }
//     showModal("Excluir todos", `Tem certeza que deseja excluir TODOS os ${studentsToDelete.length} estudante(s) do ${yearLabel}? Esta ação é irreversível.`, () => {
//         currentStudents = currentStudents.filter(s => s.yearClass !== yearClass);
//         if (activeFilter === yearClass && currentStudents.filter(s => s.yearClass === yearClass).length === 0) {
//             activeFilter = "todos";
//             updateDynamicButton();
//         }
//         renderGallery();
//     });
// }

// function promoteYear(fromClass, toClass, fromLabel, toLabel) {
//     const studentsToPromote = currentStudents.filter(s => s.yearClass === fromClass);
//     if (studentsToPromote.length === 0) {
//         showModal("Nenhum estudante", `Não há estudantes no ${fromLabel} para promover.`, () => {});
//         return;
//     }
//     showModal("Promover turma", `Promover ${studentsToPromote.length} estudante(s) do ${fromLabel} para o ${toLabel}? Eles manterão nome e foto.`, () => {
//         for (let student of currentStudents) {
//             if (student.yearClass === fromClass) {
//                 student.yearClass = toClass;
//             }
//         }
//         activeFilter = "todos";
//         updateDynamicButton();
//         renderGallery();
//     });
// }

// function updateDynamicButton() {
//     if (activeFilter === "todos") {
//         dynamicActionBtn.style.display = "none";
//         return;
//     }
//     dynamicActionBtn.style.display = "inline-flex";
//     if (activeFilter === "1ano") {
//         dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 1º para 2º Ano';
//         dynamicActionBtn.className = "btn-dynamic promote";
//         dynamicActionBtn.onclick = () => promoteYear("1ano", "2ano", "1º ano", "2º ano");
//     } else if (activeFilter === "2ano") {
//         dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 2º para 3º Ano';
//         dynamicActionBtn.className = "btn-dynamic promote";
//         dynamicActionBtn.onclick = () => promoteYear("2ano", "3ano", "2º ano", "3º ano");
//     } else if (activeFilter === "3ano") {
//         dynamicActionBtn.innerHTML = '<i class="fas fa-arrow-right"></i> Promover 3º para 4º Ano';
//         dynamicActionBtn.className = "btn-dynamic promote";
//         dynamicActionBtn.onclick = () => promoteYear("3ano", "4ano", "3º ano", "4º ano");
//     } else if (activeFilter === "4ano") {
//         dynamicActionBtn.innerHTML = '<i class="fas fa-trash-alt"></i> Excluir todos do 4º Ano';
//         dynamicActionBtn.className = "btn-dynamic delete-fourth";
//         dynamicActionBtn.onclick = () => deleteAllByYear("4ano", "4º ano");
//     }
// }

// function renderGallery() {
//     let filtered = [...currentStudents];
//     if (activeFilter !== "todos") {
//         filtered = filtered.filter(student => student.yearClass === activeFilter);
//     }
//     studentCountSpan.innerHTML = `<i class="fas fa-user-graduate"></i> ${filtered.length} estudante(s) exibido(s) | Total: ${currentStudents.length}`;
//     if (filtered.length === 0) {
//         galeriaDiv.innerHTML = `<div style="grid-column:1/-1; text-align:center; padding:50px; background:var(--bg-surface); border-radius:32px;">
//                                     <i class="fas fa-user-slash" style="font-size:3rem; opacity:0.5;"></i>
//                                     <p>Nenhum estudante nesta turma. Adicione novos!</p>
//                                 </div>`;
//         highlightActiveFilterButton();
//         return;
//     }
//     let html = "";
//     filtered.forEach(student => {
//         const yearLabel = getYearLabel(student.yearClass);
//         html += `<div class="card" data-id="${student.id}">
//                     <img class="card-img" src="${student.image}" alt="${escapeHtml(student.name)}" onerror="this.onerror=null; this.src='https://placehold.co/400x240?text=Sem+Imagem';">
//                     <div class="info">
//                         <h3>${escapeHtml(student.name)}</h3>
//                         <p>${yearLabel}</p>
//                         <button class="delete-btn" data-id="${student.id}" title="Remover estudante">
//                             <i class="fas fa-trash-alt"></i>
//                         </button>
//                     </div>
//                 </div>`;
//     });
//     galeriaDiv.innerHTML = html;
//     highlightActiveFilterButton();
//     updateDynamicButton();
// }

// function highlightActiveFilterButton() {
//     filtrosBtns.forEach(btn => {
//         const filterVal = btn.getAttribute("data-filter");
//         if ((activeFilter === "todos" && filterVal === "todos") || filterVal === activeFilter) {
//             btn.classList.add("ativo");
//         } else {
//             btn.classList.remove("ativo");
//         }
//     });
// }

// function setFilter(filter) {
//     activeFilter = filter;
//     renderGallery();
// }

// function resetToDefault() {
//     currentStudents = DEFAULT_STUDENTS.map(s => ({ ...s, id: s.id }));
//     nextId = DEFAULT_STUDENTS.length + 1;
//     if (activeFilter !== "todos") {
//         activeFilter = "todos";
//     }
//     renderGallery();
// }

// function setupDelegation() {
//     galeriaDiv.addEventListener("click", (e) => {
//         const deleteBtn = e.target.closest(".delete-btn");
//         if (deleteBtn && deleteBtn.dataset.id) {
//             const studentId = parseInt(deleteBtn.dataset.id);
//             deleteStudentById(studentId);
//             e.stopPropagation();
//         }
//     });
// }

// function initTheme() {
//     const themeBtn = document.getElementById("themeToggle");
//     function applyTheme(isDark) {
//         if (isDark) {
//             document.body.classList.add("dark");
//             themeBtn.innerHTML = '<i class="fas fa-sun"></i> <span id="themeText">Modo Claro</span>';
//             localStorage.setItem("ifpr_theme", "dark");
//         } else {
//             document.body.classList.remove("dark");
//             themeBtn.innerHTML = '<i class="fas fa-moon"></i> <span id="themeText">Modo Escuro</span>';
//             localStorage.setItem("ifpr_theme", "light");
//         }
//         const newSpan = themeBtn.querySelector("#themeText");
//         if (newSpan) newSpan.innerText = isDark ? "Modo Claro" : "Modo Escuro";
//     }
//     const savedTheme = localStorage.getItem("ifpr_theme");
//     if (savedTheme === "dark") applyTheme(true);
//     else applyTheme(false);
//     themeBtn.addEventListener("click", () => {
//         const isDark = document.body.classList.contains("dark");
//         applyTheme(!isDark);
//     });
// }

// function initEventListeners() {
//     filtrosBtns.forEach(btn => {
//         btn.addEventListener("click", () => {
//             const filterValue = btn.getAttribute("data-filter");
//             setFilter(filterValue);
//         });
//     });
//     showFormBtn.addEventListener("click", () => {
//         if (addFormPanel.style.display === "none" || addFormPanel.style.display === "") {
//             addFormPanel.style.display = "block";
//             showFormBtn.innerHTML = '<i class="fas fa-minus-circle"></i> Fechar Formulário';
//         } else {
//             addFormPanel.style.display = "none";
//             showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
//         }
//     });
//     resetDefaultBtn.addEventListener("click", () => {
//         showModal("Restaurar padrão", "Restaurar a lista original? Todas as adições e remoções serão perdidas.", () => {
//             resetToDefault();
//             if (addFormPanel.style.display === "block") addFormPanel.style.display = "none";
//             showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
//         });
//     });
//     confirmAddBtn.addEventListener("click", () => {
//         const name = studentNameInput.value.trim();
//         const year = studentYearSelect.value;
//         let providedImage = imageUrlInput.value.trim();
//         const file = imageUploadInput.files[0];
//         if (file) {
//             handleImageUpload(file, (base64Image) => {
//                 if (base64Image) {
//                     if (addStudent(name, year, base64Image)) {
//                         studentNameInput.value = "";
//                         imageUrlInput.value = "";
//                         imageUploadInput.value = "";
//                         addFormPanel.style.display = "none";
//                         showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
//                     }
//                 } else {
//                     showModal("Erro", "Erro ao processar a imagem. Tente novamente.", () => {});
//                 }
//             });
//         } else {
//             if (!providedImage) providedImage = "";
//             if (addStudent(name, year, providedImage)) {
//                 studentNameInput.value = "";
//                 imageUrlInput.value = "";
//                 imageUploadInput.value = "";
//                 addFormPanel.style.display = "none";
//                 showFormBtn.innerHTML = '<i class="fas fa-plus-circle"></i> Novo Estudante';
//             }
//         }
//     });
// }

// function init() {
//     resetToDefault();
//     setupDelegation();
//     initTheme();
//     initEventListeners();
// }

// init();