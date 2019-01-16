jQuery(function() {
	$('#accountBox').show();

	$('.textbox').find('input[type=password]').focus(function() {
		$(this).siblings('.lined').addClass('lined-on');
		$(this).siblings('.text-tit').removeClass('text-tit-color').addClass('text-tit-focus');
	}).blur(function() {
		if (jQuery.trim($(this).val()) == '') {
			$(this).siblings('.lined').removeClass('lined-on');
			$(this).siblings('.text-tit').removeClass('text-tit-focus');
		} else {
			$(this).siblings('.lined').removeClass('lined-on');
			$(this).siblings('.text-tit').addClass('text-tit-color');
		}
	});
	$('.textbox').find('input[type=text]').focus(function() {
		$(this).siblings('.lined').addClass('lined-on');
		$(this).siblings('.text-tit').removeClass('text-tit-color').addClass('text-tit-focus');
	}).blur(function() {
		if (jQuery.trim($(this).val()) == '') {
			$(this).siblings('.lined').removeClass('lined-on');
			$(this).siblings('.text-tit').removeClass('text-tit-focus');
		} else {
			$(this).siblings('.lined').removeClass('lined-on');
			$(this).siblings('.text-tit').addClass('text-tit-color');
		}
	});

	$('#accountBox .textbox').find('input[type=text]').focus();
	$('.operate').on('click', 'span', function(e) {
		$(this).siblings('.btnBox').fadeToggle(300);
		e.stopPropagation();
	});

	$(document).on('click', function() {
		$('.operate').find('.btnBox').fadeOut(300);
	});

	$("#txtPassword1").keydown(function() {
		if (event.keyCode == 13) {
			login();
		}
	});
	$("#txtEmail").keydown(function() {
		if (event.keyCode == 13) {
			$("#txtPassword1").focus();
		}
	});

	// 自定义滚动条
	$("body").niceScroll({
		cursorcolor : "#c2c2c2",
		cursoropacitymax : 1,
		touchbehavior : false,
		cursorwidth : "10px",
		cursorborder : "0",
		cursorborderradius : "30px"
	});

	$("#recordBox").niceScroll({
		cursorcolor : "#c2c2c2",
		cursoropacitymax : 1,
		touchbehavior : false,
		cursorwidth : "7px",
		cursorborder : "0",
		cursorborderradius : "30px"
	});
});