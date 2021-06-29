'use strict';
$(function() {

    function check(employeeno, name, department) {
        if (employeeno.length > 3 || parseInt(employeeno) === 0) {
            return false;
        }
		// 区切り文字で使う'+'を含んだ名前を許さない。
        if (name.length === 0 && name.includes('+')) {
            return false;
        }
        if (department.length === 0 && name.includes('+')) {
            return false;
        }
        return true;
    }
	
	// スペースの類を埋める。
	function changeSpace(emt) {
		return emt.replace(/[\s　]/g, '');
	}

    $(document).on('click', '#update-btn', function() {
        const employeeno = $('#employeeno').val();
        const name       = changeSpace($('#name').val());
        const department = changeSpace($('#department').val());
		let   updateData = {
                			employeeno : employeeno,
                			name       : name,
                			department : department,
           				   };

        if (check(employeeno, name, department)) {
	
           $.ajax({
               url   : 'UpdateDB',
               type  : 'POST',
               cache : 'false',
               data  : updateData,
           }).done(function(result) {
               const json = JSON.parse(result);
               if (json.checker) {
                   $('#update-message').text('正常に更新されました。');
				   $('#employeeno').val('');
				   $('#name').val('');
				   $('#department').val('');
               } else {
                   $('#update-message').text('更新エラー');
               }
           }).fail(function() {
				alert('通信エラー');
		   });
        } else {
			document.getElementById('update-message').textContent = "";
		    window.alert('検索項目の入力に不備があります。');
		}
    });
});