$('#foo').keypress(function(eventObject){
  alert('Вы ввели символ с клавиатуры. Его код равен ' + eventObject.which);
});