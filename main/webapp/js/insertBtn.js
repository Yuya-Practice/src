'use strict';
$(function() {
	
	// 4桁以上もしくは0を弾く、名前と所属部署に何か入っているかのチェック
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

    $(document).on('click', '#insert-btn', function() {
        const employeeno = $('#employeeno').val();
        const name       = changeSpace($('#name').val());
        const department = changeSpace($('#department').val());
		let   insertData = {
                			employeeno : employeeno,
                			name       : name,
                			department : department,
           				   };

        if (check(employeeno, name, department)) {
	
           $.ajax({
               url   : 'InsertDB',
               type  : 'POST',
               cache : 'false',
               data  : insertData,
           }).done(function(result) {
               const json = JSON.parse(result);
               if (json.checker) {
                   $('#insert-message').text('正常に登録されました。');
				   $('#employeeno').val('');
				   $('#name').val('');
				   $('#department').val('');
               } else {
                   $('#insert-message').text('登録エラー');
               }
           }).fail(function() {
				alert('通信エラー');
		   });
        } else {
			document.getElementById('insert-message').textContent = "";
            window.alert('検索項目の入力に不備があります。');
        }
    });
});