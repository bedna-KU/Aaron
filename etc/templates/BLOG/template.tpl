<body onLoad = "start()">
	<div id = "ku_win">
		<div id="header">
		</div>
		<div id="menu_up">
			<div class="menu_up_button">
				<a href="?shell=add">ADD</a>	// Add button
			</div>
			<div class="menu_up_button">
				<a href="?shell=login">LOGIN</a>	// Login button
			</div>
			<div class="menu_up_button">
				<a href="?shell=logout">LOGOUT</a>	// Logout button
			</div>
		</div>
		<div id="left_column">
			<div class="menu_left">
				{{ shell, ls -n /var/www/blog }}			// Run command ls -lm /var/www
				{{ show_output }}					// Show output from command ls -lm /var/www
			</div>
		</div>
		<div class="page">
			{{ shell, $shell_request }}		// Run command from $_REQUEST
			// {{ show_current_command }}		// Show current command
			{{ show_output }}				// Show output from command
			// {{ input }}						// Show command line
			<br><br><br>
		</div>
		<script type="text/javascript" src="bin/javascript/autocomplete.js"></script>
	</div>
</body>
