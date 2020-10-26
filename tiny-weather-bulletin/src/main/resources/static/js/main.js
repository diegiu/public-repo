/**
 * The client-side HTML document manipulation and event handling of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
function execute() {
	var cityName = $.trim($("#text-city").val());
	var fromHours = $.trim($("#text-from-hours").val());
	var toHours = $.trim($("#text-to-hours").val());
	
	if (cityName == "" || fromHours == "" || toHours == "") {
		modalDialog("All form fields are mandatory.");
		return;
	}
	$.blockUI({ css: {
	    border: 'none',
	    padding: '15px',
	    backgroundColor: '#000',
	    '-webkit-border-radius': '10px',
	    '-moz-border-radius': '10px',
	    opacity: .5,
	    color: '#fff'
	} });
	$.ajax({
		url: "/weather/bulletin/averages",
		type: "get",
		dataType : "json",
		data: { 
		    cityName: cityName, 
		    fromHours: fromHours, 
		    toHours: toHours
		},
		success: function(response) {
			$.unblockUI();
			if (response != null) {
				modalDialog("<div>" + "City name: " + response.city_name + "<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Latitude: " + response.latitude + "<div style=\"line-height:1.0rem\">&nbsp;</div>" +
					"Longitude: " + response.longitude + "<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Outside working hours temperature average: " + response.outside_working_hours_temp_average + 
					"<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Outside working hours feels-like average: " + response.outside_working_hours_feels_like_average + 
					"<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Outside working hours humidity average: " + response.outside_working_hours_humidity_average +  
					"<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Working hours temperature average: " + response.working_hours_temp_average +  
					"<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Working hours feels-like average: " + response.working_hours_feels_like_average +  
					"<div style=\"line-height:1.0rem\">&nbsp;</div>" + "Working hours humidity average: " + response.working_hours_humidity_average +  "</div>");
			} else {
				modalDialog("No data fetch.");
			}
		},
		error: function(xhr) {
			$.unblockUI();
			modalDialog(xhr.responseText);
		}
	});
}
function modalDialog(message) {
	$("#modal-body-msg").html(message);
	$("#modalDialog").modal("show");
}