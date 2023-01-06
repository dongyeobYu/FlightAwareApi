function CodeList(){

    $.ajax({
        type: "GET",
        url: "/code",
        contentType: JSON,
        success: function (data){
            const obj = JSON.stringify(data);
            let str ="";
            for (let i=0; i<data["item"].length; i++){
                str += "<p>"
                str += "<text>"+ data["item"][i]["cityKor"] +"</text> </br>"
                str += "<text>"+ data["item"][i]["cityEng"] +"</text> </br>"
                str += "<text>"+ data["item"][i]["cityCode"] +"</text> </br>"
                str += "</p>"
            }

            $(".smallbox").html(str);
        }
    })
}

function FindDomestic(){
    const date = $("#date").val();
    const deptCode = $("#deptCode").val();
    const ArrCode = $("#ArrCode").val();
    const AirLine = $("#AirLine").val();
    const AirLineNum = $("#AirLineNum").val();

    let str="";
    $.ajax({
        type: "GET",
        url: "/domestic",
        data: {date : date, deptCode : deptCode, ArrCode : ArrCode, AirLine : AirLine, AirLineNum : AirLineNum},
        success: function (data){

            str += "</br>"
            str += "영어명 : <text>" + data["item"]["airlineEnglish"] + "</text> </br>"
            str += "한국명 : <text>" + data["item"]["airlineKorean"] + "</text> </br>"
            str += "출발 도시 : <text>" + data["item"]["flightPurpose"] + "</text> </br>"
            str += "도착 도시 : <text>" + data["item"]["arrivalcity"] + "</text> </br>"
            str += "도착 도시 코드 : <text>" + data["item"]["arrivalcityCode"] + "</text> </br>"
            str += "출발 예정 시간 : <text>" + data["item"]["domesticStartTime"] + "</text> </br>"
            str += "도착 예정 시간 : <text>" + data["item"]["domesticArrivalTime"] + "</text> </br>"
            str += "항공 종류 : <text>" + data["item"]["domesticArrivalTime"] + "</text> </br>"
            str += "항공편명 : <text>" + data["item"]["domesticNum"] + "</text> </br>"


            $(".domesticBox").html(str);
        }
    })
}