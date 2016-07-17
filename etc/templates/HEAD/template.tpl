<body onLoad = "scrollChatDown()">						// Scroll Chat down
	<div id = "ku_win">
		<div id="header">
			<div class="header_title">
				{{ shell, cat /var/www/head_title.txt }}			// Run command ls -lm /var/www
				{{ show_output }}							// Show output from command ls -lm /var/www
			</div>
			<div class="header_text">
				{{ shell, cat /var/www/head.txt }}			// Run command ls -lm /var/www
				{{ show_output }}							// Show output from command ls -lm /var/www
			</div>
			// {{ input }}
		</div>
		<div id="column_1">
			<div class="boxes_title">
				{{ shell, cat /var/www/1.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
			<div class="boxes_content">
				{{ shell, cat /var/www/1-content.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
		</div>
		<div id="column_2">
			<div class="boxes_title">
				{{ shell, cat /var/www/2.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
			<div class="boxes_content">
				{{ shell, cat /var/www/2-content.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
		</div>
		<div id="column_3">
			<div class="boxes_title">
				{{ shell, cat /var/www/3.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
			<div class="boxes_content">
				{{ shell, cat /var/www/3-content.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
		</div>
		<div id="column_4">
			<div class="boxes_title">
				{{ shell, cat /var/www/4.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
			<div class="boxes_content">
				{{ shell, cat /var/www/4-content.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
		</div>
		<div id="column_5">
			<div class="boxes_title">
				{{ shell, cat /var/www/5.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
			<div class="boxes_content">
				{{ shell, cat /var/www/5-content.txt }}		// Run command from $_REQUEST
				{{ show_output }}						// Show output from command
			</div>
		</div>
		<script type="text/javascript" src="bin/javascript/autocomplete.js"></script>
	</div>
</body>
