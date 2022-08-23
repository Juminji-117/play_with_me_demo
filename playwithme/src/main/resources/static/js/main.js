(function($) {

	"use strict";

	// Setup the calendar with the current date
$(document).ready(function(){
    var date = new Date();
    var today = date.getDate();
    // Set click handlers for DOM elements
    $(".right-button").click({date: date}, next_year);
    $(".left-button").click({date: date}, prev_year);
    $(".month").click({date: date}, month_click);
    $("#add-button").click({date: date}, new_event);
    // Set current month as active
    $(".months-row").children().eq(date.getMonth()).addClass("active-month");
    init_calendar(date);
    var events = check_events(today, date.getMonth()+1, date.getFullYear());
    show_events(events, months[date.getMonth()], today);
});

// Initialize the calendar by appending the HTML dates
function init_calendar(date) {
    $(".tbody").empty();
    $(".events-container").empty();
    var calendar_days = $(".tbody");
    var month = date.getMonth();
    var year = date.getFullYear();
    var day_count = days_in_month(month, year);
    var row = $("<tr class='table-row'></tr>");
    var today = date.getDate();
    // Set date to 1 to find the first day of the month
    date.setDate(1);
    var first_day = date.getDay();
    // 35+firstDay is the number of date elements to be added to the dates table
    // 35 is from (7 days in a week) * (up to 5 rows of dates in a month)
    for(var i=0; i<35+first_day; i++) {
        // Since some of the elements will be blank,
        // need to calculate actual date from index
        var day = i-first_day+1;
        // If it is a sunday, make a new row
        if(i%7===0) {
            calendar_days.append(row);
            row = $("<tr class='table-row'></tr>");
        }
        // if current index isn't a day in this month, make it blank
        if(i < first_day || day > day_count) {
            var curr_date = $("<td class='table-date nil'>"+"</td>");
            row.append(curr_date);
        }
        else {
            var curr_date = $("<td class='table-date'>"+day+"</td>");
            var events = check_events(day, month+1, year);
            if(today===day && $(".active-date").length===0) {
                curr_date.addClass("active-date");
                show_events(events, months[month], day);
            }
            // If this date has any events, style it with .event-date
            if(events.length!==0) {
                curr_date.addClass("event-date");
            }
            // Set onClick handler for clicking a date
            curr_date.click({events: events, year: year, month: month+1, day:day}, date_click);
            row.append(curr_date);
        }
    }
    // Append the last row and set the current year
    calendar_days.append(row);
    $(".year").text(year);
}

// Get the number of days in a given month/year
function days_in_month(month, year) {
    var monthStart = new Date(year, month, 1);
    var monthEnd = new Date(year, month + 1, 1);
    return (monthEnd - monthStart) / (1000 * 60 * 60 * 24);
}
// Event handler for when a date is clicked
function date_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    $(".active-date").removeClass("active-date");
    $(this).addClass("active-date");
    show_events(event.data.events, event.data.month, event.data.day);

    //여기서부터 URL 관련 추가한 부분
    var dateString = event.data.year + "-";
      if ((event.data.month) < 10) {
         dateString += "0";
          }
      dateString += (event.data.month) + "-";
      if (event.data.day < 10) {
         dateString += "0";
         }
         dateString += event.data.day;
   let params = (new URL(document.location)).searchParams;
   const categoryName = params.get("category");
   const categoryName_2 = categoryName;
   params.set("category",categoryName_2);
   params.set("date",dateString);
   const state = { 'category':categoryName_2, 'date': dateString }
   const title = ''
   const url = "event?"+ params
   history.pushState(state, title, url)
   console.log(categoryName_2);
   let name2 = params.get("date");
   console.log(name2);
   // URL 관련 추가 끝

   //ajax로 Event 받아오기
   $.ajax({
   type:"GET",
   async: false, // 동기 호출(실행 다 끝난 다음에 다음 로직 수행)
   url:"/getEvent?"+ params,
   dataType:"JSON",

   success: function(data){
       console.log("통신성공");
       //console.log(data);
       event_data2.push(data); // 아래 미작동시 후보코드, 문제: 이 코드는 배열 속 배열 생성
       //event_data2 = data.slice();
       console.log(event_data2[0]); // 제대로 출력
       console.log(event_data2[0].length); // 제대로 출력

   },
   error:function(){
       console.log("통신에러");
   }
   })

    // 캘린더 날짜와 이벤트 날짜 동일한지 체크
    console.log("event_data2 그대로 있는지 아래 확인 : ");
    console.log(event_data2[0]); // 확인
     for(var i=0; i<event_data2[0].length; i++) {
          var event_data3 = []
           var event = event_data2[0][i];
           console.log("event :");
           console.log(event); // 제대로 작동
           const str = event_data2[0][i].date;
           console.log(str); // 2022-08-15T17:30:00로 출력
           var a=str.split("-")[0]
           var b=str.split("-")[1]
           var c=str.split("-")[2].split("T")[0]
           // console.log(a); // 2022
           // console.log(b); // 08
           // console.log(c); // 15
          // var strArr = str.toString().split('-'); // 후보
           //console.log(strArr[0], strArr[1]-1, strArr[2]); // 후보
              var dt = new Date();
           if(a==dt.getFullYear() &&
              b==dt.getMonth()+1 &&
               c==dt.getDate()) {//TODO: 이 조건문 false
               console.log('실행')
                   event_data3.push(event);
                   console.log("event_data3 :");
                   console.log(event_data3);
               }

       }


       // 이벤트 카드에 이벤트 보여주기
        $(".events-container").empty();
           $(".events-container").show(250);
           //console.log(event_data["events"]);
           // If there are no events for this date, notify the user
           if(event_data3.length===0) {
               var event_card = $("<div class='event-card'></div>");
               var event_name = $("<div class='event-name'>There are no events planned today </div>");
               $(event_card).css({ "border-left": "10px solid #FF1744" });
               $(event_card).append(event_name);
               $(".events-container").append(event_card);
           }
           else {
               // Go through and add each event as a card to the events container
               for(var i=0; i<event_data3.length; i++) { //TODO: 여기에 Event 엔티티 컬럼들 추가
                   var event_card = $("<div class='event-card'></div>");
                   var event_name = $("<div class='event-name'>"+event_data3[i].name+":</div>"); // occasion 대신 변수명 //TODO: 작동 체크
                   var event_location = $("<div class='event-location'>"+event_data3[i].location+"</div>"); // invited_count 대신 변수명 //TODO: 작동 체크
                   $(event_card).append(event_name).append(event_location); // 요소 추가하면 여기도 추가 //TODO: 작동 체크
                   $(".events-container").append(event_card);
               }
           }
};

// Event handler for when a month is clicked
function month_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    var date = event.data.date;
    $(".active-month").removeClass("active-month");
    $(this).addClass("active-month");
    var new_month = $(".month").index(this);
    date.setMonth(new_month);
    init_calendar(date);
}

// Event handler for when the year right-button is clicked
function next_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()+1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

// Event handler for when the year left-button is clicked
function prev_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()-1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

// Event handler for clicking the new event button
function new_event(event) {
    // if a date isn't selected then do nothing
    if($(".active-date").length===0)
        return;
    // remove red error input on click
    $("input").click(function(){
        $(this).removeClass("error-input");
    })
    // empty inputs and hide events
    $("#dialog input[type=text]").val('');
    $("#dialog input[type=number]").val('');
    $(".events-container").hide(250);
    $("#dialog").show(250);
    // Event handler for cancel button
    $("#cancel-button").click(function() {
        $("#name").removeClass("error-input");
        $("#count").removeClass("error-input");
        $("#dialog").hide(250);
        $(".events-container").show(250);
    });
    // Event handler for ok button
    $("#ok-button").unbind().click({date: event.data.date}, function() {
        var date = event.data.date;
        var name = $("#name").val().trim();
        var count = parseInt($("#count").val().trim());
        var day = parseInt($(".active-date").html());
        // Basic form validation
        if(name.length === 0) {
            $("#name").addClass("error-input");
        }
        else if(isNaN(count)) {
            $("#count").addClass("error-input");
        }
        else {
            $("#dialog").hide(250);
            console.log("new event");
            new_event_json(name, count, date, day);
            date.setDate(day);
            init_calendar(date);
        }
    });
}

// Adds a json event to event_data
function new_event_json(name, count, date, day) { // TODO: json 형태말고 일반 객체 속성을 event_data["events"]배열에 저장하는 법 찾아보기
    var event = {
        "occasion": name,
        "invited_count": count,
        "year": date.getFullYear(),
        "month": date.getMonth()+1,
        "day": day
    };
    event_data["events"].push(event);
}

// Display all events of the selected date in card views
function show_events(events, month, day) {
    // Clear the dates container
    $(".events-container").empty();
    $(".events-container").show(250);
    //console.log(event_data["events"]);
    // If there are no events for this date, notify the user
    if(events.length===0) {
        var event_card = $("<div class='event-card'></div>");
        var event_name = $("<div class='event-name'>There are no events planned for "+month+" "+day+".</div>");
        $(event_card).css({ "border-left": "10px solid #FF1744" });
        $(event_card).append(event_name);
        $(".events-container").append(event_card);
    }
    else {
        // Go through and add each event as a card to the events container
        for(var i=0; i<events.length; i++) { //TODO: 여기에 Event 엔티티 컬럼들 추가
            var event_card = $("<div class='event-card'></div>");
            var event_name = $("<div class='event-name'>"+events[i]["name"]+":</div>"); // occasion 대신 변수명
            var event_location = $("<div class='event-location'>"+events[i]["location"]+"</div>"); // invited_count 대신 변수명
            $(event_card).append(event_name).append(event_location); // 요소 추가하면 여기도 추가
            $(".events-container").append(event_card);
        }
    }
}


// Checks if a specific date has any events
function check_events(day, month, year) {
    var events = [];
    for(var i=0; i<event_data["events"].length; i++) {
        var event = event_data["events"][i];
        if(event["day"]===day &&
            event["month"]===month &&
            event["year"]===year) {
                events.push(event);
            }
    }
    return events;
}


//TODO: 작동 체크
//어차피 slice하면 sample date 사라짐
//함수 내부에서 var 붙이면 지역변수, 안붙이면 전역변수
//함수내부가 아닐 때는 var 꼭 붙여야 함
var event_data2=[];

// Given data for events in JSON format
var event_data = {
    "events": [
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
    {
        "occasion": " Test Event",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 11
    }
    ]
};

//TODO
const months = [
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12
];

})(jQuery);
