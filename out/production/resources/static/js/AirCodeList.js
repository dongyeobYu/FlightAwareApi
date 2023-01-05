function CodeList(){

    $.ajax({
        type: "GET",
        url: "/test",
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