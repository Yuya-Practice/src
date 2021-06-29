'use strict';
$(function() {

    function check(employeeno, name, department) {
        if (employeeno.length > 3 || parseInt(employeeno) === 0) {
            return false;
        }
        if (name.length === 0) {
            return false;
        }
        if (department.length === 0) {
            return false;
        }
        return true;
    }

    $(document).on('click', '#delete-btn', function() {
        const employeeno = $('#employeeno').val();
        const name       = $('#name').val();
        const department = $('#department').val();
		let   deleteData = {
                			employeeno : employeeno,
                			name       : name,
                			department : department,
           				   };

        if (check(employeeno, name, department)) {
	
           $.ajax({
               url   : 'DeleteDB',
               type  : 'POST',
               cache : 'false',
               data  : deleteData,
           }).done(function(result) {
               const json = JSON.parse(result);
               if (json.checker) {
                   $('#delete-message').text('正常に削除されました。');
				   $('#employeeno').val('');
				   $('#name').val('');
				   $('#department').val('');
               } else {
                   $('#delete-message').text('削除エラー');
               }
           }).fail(function() {
				alert('通信エラー');
		   });
        } else {
			document.getElementById('delete-message').textContent = "";
		    window.alert('検索項目の入力に不備があります。');
		}
    });
});