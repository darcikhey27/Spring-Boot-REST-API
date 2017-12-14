$(document).ready(init);

function init() {
    $.ajaxSetup({
        cache: false,
        contentType:"application/json; charset=utf-8",
        dataType:"json"
    });
    $("#btn-add-post").on("click", btnAddCity);
    $("#btn-add-get").on("click", btnGetCityName);
    $("#btn-update").on("click", btnUpdate);
    $("#btn-delete").on("click", btnDelete);
    $("#btn-refresh").on("click", btnRefreshCity);
    $("#btn-delete").on("click", btnDeleteCity);

}

function btnDelete() {
    var name = $("#city-name-delete").val();
    $.ajax({
        type: 'POST',
        url: 'http://darcikhey.com/api/v1/weather/city/delete/',
        data: { city_name: name },
        success: function (data) {
            $("#code-delete").append(JSON.stringify(data));
        }
    });
}

function btnUpdate() {
    var name = $("#city-name-update").val();
    $.ajax({
        type: 'POST',
        url: 'http://darcikhey.com/api/v1/weather/city/update/',
        data: { city_name: name },
        success: function (data) {
            $("#code-update").append(JSON.stringify(data));
        }
    });
}

/* add a city when the user types in the city name */
function btnAddCity() {
    console.log("btn click");
    // TODO: -> validate name makesure they type a good string
    var name = $("#city-name").val();
    var formdata = {city_name : name};
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/add',
        data: JSON.stringify(formdata),
        success: function (data) {
            $("#code-post").append("city "+ name);
        },
        error: function () {
            $("#code-post").append("error adding city");
        }
    });
}
function btnGetCityName() {
    var name = $("#city-name-get").val();
    var formdata = {city_name : name};
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/get/'+name,
        success: function (data) {
            //$("#code-get").append(JSON.stringify(data));
            console.log(data);
            var name = data.name;
            var cityID = data.id;
            var temp = data.main.temp;
            var weatherMain = data.weather[0].description;
            var weatherMain2 = data.weather[0].main;
            var icon = data.weather[0].icon;
            updateFields(cityID, name, cityID, weatherMain, weatherMain2, icon, temp);
        }
    });
}
/* update the fields for each temp widget via a json object */
function updateFields(id, name, cityID, description, main, icon, temp) {
    console.log(id);
    console.log(name);
    console.log(cityID);
    console.log(description);
    console.log(icon);
    console.log(temp);
    //TODO: get a hold of all field

    $("#my-table").append("<tr id='tr" + cityID + "'></tr>")
    $("#my-table tr:last").append(
        "<td><div class='widget'><h3 id='city-name'>City: " + name + "</h3><p id='temp'>temp: " + temp + "</p><p id='icon-url'></p><p id='description'>Description: "+main +", "+ description + " </p><p id='city-id'>CityID: " + cityID + "</p>"+
        "<button class='btn btn-success'>refresh</button><button class='btn btn-danger'>delete</button></div></td>"

    );

    // $("my-table tr:last").append("<td><button class='btn btn-primary btn-edit'>edit</button><button class='btn btn-danger btn-delete'>delete</button></td>");
    // $("#city-name").append(name);
    // $("#temp").append(temp);
    var img = $('<img>'); //Equivalent: $(document.createElement('img'))
    img.attr('src', "http://openweathermap.org/img/w/" + icon + ".png");
    img.appendTo('#tr' + cityID + ' #icon-url');
    // $("#description").append(description);
    // $("#city-id").append(cityID);

    //TODO: make an img tag, href to http://openweathermap.org/img/w/10d.png to populate icon image

}

function btnRefreshCity() {

}
function btnDeleteCity() {

}
