<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link rel="stylesheet" href="css/stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
	<body>
		<div class="title" id="select-title">
			<h1>検索画面</h1>
		</div><!-- /#select-title.title -->
		<div class="form">
			<form>
				<dl class="form-dl">
					<dt>
						社員番号
					</dt>
					<dd>
						<input type="number" maxlength="3" min="0" max="999" name="employeeno" id="employeeno">
					</dd>
					<dt>
						氏名
					</dt>
					<dd>
						<input type="text" maxlength="30" name="name" id="name">
					</dd>
					<dt>
						所属部署
					</dt>
					<dd>
						<input type="text" maxlength="50" name="department" id="department">
					</dd>
				</dl>
				<div class="do" id="do-select">
					<input type="button" id="select-btn" value="検索">
				</div><!-- /#do-select.do -->
			</form>
		</div><!-- /.form -->
		■ 検索結果
		<table border="1" id="select-table">
			<tr>
				<th>
					社員番号
				</th>
				<th>
					氏名
				</th>
				<th>
					所属部署
				</th>
			</tr>
		</table>
		メッセージ欄
		<div class="message" id="select-message">
		</div><!-- /#select-message.message -->
		<div class="back">
			<button id="to-menu">戻る</button>
		</div><!-- /.back -->
		<script src="js/selectBtn.js"></script>
		<script src="js/back2menu.js"></script>
	</body>
</html>