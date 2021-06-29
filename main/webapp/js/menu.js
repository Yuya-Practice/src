'use strict';

// 画面遷移
document.getElementById("jump-select").onclick = () => {
    window.location.href = 'select';
}

document.getElementById("jump-insert").onclick = () => {
    window.location.href = 'insert';
}

document.getElementById("jump-update").onclick = () => {
    window.location.href = 'update';
}

document.getElementById("jump-delete").onclick = () => {
    window.location.href = 'delete';
}

document.getElementById("exit").onclick = () => {
	window.close();
}

// 終了
document.getElementById("exit").onclick = () => {
	window.open('about:blank','_self').close();
}