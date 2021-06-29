<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<link rel="stylesheet" href="css/stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
    <body>
        <div class="title" id="update-title">
			<h1>更新画面</h1>
		</div><!-- /#update-title.title -->
		<div class="form">
			<form>
				<dl class="form-dl">
					<dt>
						社員番号
					</dt>
					<dd>
						<input type="number" maxlength="3" min="0" max="999" name="employeeno" id="employeeno" required>
					</dd>
					<dt>
						氏名
					</dt>
					<dd>
						<input type="text" maxlength="30" name="name" id="name" required>
					</dd>
					<dt>
						所属部署
					</dt>
					<dd>
						<input type="text" maxlength="50" name="department" id="department" required>
					</dd>
				</dl>
				<div class="do" id="do-update">
					<input type="button" id="update-btn" value="更新">
				</div><!-- /#do-update.do -->
			</form>
		</div><!-- /.form -->
				メッセージ欄
				<div class="message" id="update-message">
				</div><!-- /#update-message.message -->
			<div class="back">
				<button id="to-menu">戻る</button>
			</div><!-- /.back -->
		<script src="js/updateBtn.js"></script>
		<script src="js/back2menu.js"></script>
    </body>
</html>