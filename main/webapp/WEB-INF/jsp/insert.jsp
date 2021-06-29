<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link rel="stylesheet" href="css/stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
	<body>
		<div class="title" id="insert-title">
			<h1>登録画面</h1>
		</div><!-- /#insert-title.title -->
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
				<div class="do" id="do-insert">
					<input type="button" id="insert-btn" value="登録">
				</div><!-- /#do-insert.do -->
			</form>
		</div><!-- /.form -->
		メッセージ欄
		<div class="message" id="insert-message">
		</div><!-- /#insert-message.message -->
		<div class="back">
			<button id="to-menu">戻る</button>
		</div><!-- /.back -->
		<script src="js/insertBtn.js"></script>
		<script src="js/back2menu.js"></script>
	</body>
</html>