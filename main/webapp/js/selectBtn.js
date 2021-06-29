'use strict';
$(function() {
	
	// いずれかは入力されていることを確認
    function selectCheck(employeeno, name, department) {
        if (employeeno !== "" || name !== "" || department !== "" ) {
            return true;
        }
        return false;
    }

    $(document).on('click', '#select-btn', function() {
		// 検索結果を初期化
		$('#select-table tr:nth-child(n+2)').remove();
        const employeeno = $('#employeeno').val();
        const name       = $('#name').val();
        const department = $('#department').val();
		let   selectData = {
                			employeeno : employeeno,
                			name       : name,
                			department : department,
           				   };
		
        if (selectCheck(employeeno, name, department)) {
	
           $.ajax({
               url   : 'SelectDB',
               type  : 'POST',
               cache : 'false',
               data  : selectData,
           }).done(function(result) {
			   let array = result.split("+");
			   if (array.length >= 2) {
					// 最後は空なのでlength-1
					for (let i=0; i<array.length-1; i++) {
						let json = JSON.parse(array[i]);
						// 三桁に埋め合わせ
						json.employeeno = ('000' + json.employeeno).slice(-3);
						let new_element = `<tr><td>${json.employeeno}</td>
											   <td>${json.name}</td>
											   <td>${json.department}</td></tr>`;
						$('tbody').append(new_element);
					}
					$('#employeeno').val('');
				    $('#name').val('');
				    $('#department').val('');
               		$('#select-message').text('正常に検索されました。');
               } else {
                   $('#select-message').text('検索対象が存在しません');
               }
           }).fail(function() {
				window.alert('通信エラー');
		   });
        } else {
			document.getElementById('select-message').textContent = "";
			window.alert('検索項目のいずれかを入力してください。');
		}
    });
});