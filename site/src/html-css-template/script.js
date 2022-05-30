var balao = document.getElementById('balao');
var balao2 = document.getElementById('balao2');
function togglebalao() {
    if (balao.classList.contains('show')) {
        balao.classList.remove('show');
    } else {
        balao.classList.add('show');
    } if (balao2.classList.contains('show')) {
        balao2.classList.remove('show');
    } else {
        balao2.classList.add('show');
    }
}


function verificaConteudo(el) {
    if (el.value == '') {
        el.classList.remove('focus');
    } else {
        el.classList.add('focus');
    }
}