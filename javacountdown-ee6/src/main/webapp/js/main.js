var rootURL = url + "/rest/version";
var jdkAdoptionData;
PluginDetect.getVersion(".");
var version = PluginDetect.getVersion('Java');

$(function() {
    initialize();
});

/*
 * 1. Test for our cookie
 * 1.b If it's there and the java version did not change .. we print out a message.
 * 2. If no cookie exists, then get the geolocation and set that in the cookie
 * 2.a Else inform the user we couldn't help 
 */
function initialize() {

    var javaCCookie = $.cookie('javacountdown');
    if (typeof javaCCookie === 'undefined')
    {
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(logPosition, showError);
            $.cookie('javacountdown', version, {expires: 25});
            $("#geoMessage").text("Thanks for contributing!");
        } else {
            $("#geoMessage").text("We weren't able to detect where you are from.");
        }
    } else if (version === javaCCookie) {

        $("#geoMessage").html("You already contributed!<br />You use version " + javaCCookie);
    }


    // Callback for geolocation - logs java version incl lat long  
    function logPosition(position){
        log = new log(version, position.coords.latitude, position.coords.longitude);
        addLog(JSON.stringify(log));
    };

    // Error callback - displays errors.
    function showError(error){
        switch (error.code)
        {
            case error.PERMISSION_DENIED:
                $("#geoMessage").text("User denied the request for Geolocation.");
                break;
            case error.POSITION_UNAVAILABLE:
                $("#geoMessage").text("Location information is unavailable.");
                break;
            case error.TIMEOUT:
                $("#geoMessage").text("The request to get user location timed out.");
                break;
            case error.UNKNOWN_ERROR:
                $("#geoMessage").text("An unknown error occurred.");
                break;
        }
    }
    ;

    // http://jvectormap.com/maps/world/world/
    // fill the gdata object with series-values for the map.
    jdkAdoptionData = getData();

    // Get data from the rest backend
    function getData() {
        var result="";
        $.ajax({
            url: rootURL,
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function(dataWeGotViaJsonp) {
                result = dataWeGotViaJsonp;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
        return result;
    }

    // Render the map
    $('#map_canvas').vectorMap({
        map: 'world_en',
        backgroundColor: "#FFFFFF",
        color: '#004066',
        hoverColor: '#C8EEFF', 
        values: jdkAdoptionData,
        scaleColors: ['#C8EEFF', '#0071A4'],
        normalizeFunction: 'polynomial',     
        onLabelShow: function(e, el, code) {
            total = jdkAdoptionData[code.toLowerCase()] ? jdkAdoptionData[code.toLowerCase()] : "0";
            el.html(el.html() + ' Java 7 Adoption - (' + total + '%)');
        }
    });

    // Write the log via POST to the rest backend
    function addLog(log) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: rootURL,
            dataType: "json",
            data: log,
            success: function(data, textStatus, jqXHR) {
                console.log('Log created successfully');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Error adding: ' + textStatus);
            }
        });
    }

    // The log object
    function log(version, latitude, longitude)
    {
        this.version = version;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}