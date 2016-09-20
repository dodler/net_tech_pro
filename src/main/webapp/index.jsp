<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

function getDataClick() {
    var showData = $('#show-data');

    var value = $("#text_data").val();

    value = value.substring(value.lastIndexOf("\n") + 1, value.length);
    
    var commands = value.split(" ");

    var req = 'http://localhost:8080/rest/';
    for (i in commands){
        req = req.concat(commands[i]);
        req = req.concat('/');
    }


    document.getElementById('text_data').value += '\n';

    $.getJSON(req, function (data) {

      moveCursorToEnd('text_data');

      var textarea = document.getElementById('text_data');
      textarea.value += JSON.stringify(data);
      textarea.value += '\n';
      textarea.focus();

      textarea.scrollTop = textarea.scrollHeight;


      var items = data.Tasks.map(function (item) {
        return item.name + ":" + item.status;
      });

      showData.empty();

      if (items.length) {
        var content = '<li>' + items.join('</li><li>') + '</li>';
        var list = $('<ul />').html(content);
        // showData.append(list);
      }
    }).fail(function(){
      // var textarea = document.getElementById('text_data');
      // textarea.value += "Error occured during processing request. Try again.\n";
      // textarea.scrollTop = textarea.scrollHeight;
    });

    showData.text('Loading the JSON file.');
  };

$(document).ready(function () {

document.getElementById('text_data').value = "Enter commands. Type help to show short manual.";

$('#text_data').on("keypress", function (e) {
    if (e.keyCode == 13){
      console.log('enter pressed');
      var textarea = document.getElementById('text_data');
      if (textarea.value.length < 5){
        return;
      }
      $('#get-data').click();
    }
});

    $('#get-data').click(getDataClick);
});

function moveCursorToEnd(el) {
    if (typeof el.selectionStart == "number") {
        el.selectionStart = el.selectionEnd = el.value.length;
    } else if (typeof el.createTextRange != "undefined") {
        el.focus();
        var range = el.createTextRange();
        range.collapse(false);
        range.select();
    }
}


function onTestChange(event) {
    var key = event.keyCode;

    // If the user has pressed enter
    if (key == 13) {
        var str = $("#text_data").val();
        $("#text_data").value = str.substring(str.length-1, str.length);
        $('#get-data').trigger("click");
    }
}

</script>

</head>
<body>
    <a href="#" id="get-data">Get JSON data</a>
    <div id="show-data"></div>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

    <textarea rows="20" cols="100"  line-height="20" id="text_data" class="boxsizingBorder" onkeypress="onTestChange(event);">
    </textarea>
  </body>

</html>

