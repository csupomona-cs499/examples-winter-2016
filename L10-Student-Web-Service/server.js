var express = require('express');
var randomstring = require("randomstring");
var app = express();

function randomIntInc (low, high) {
    return Math.floor(Math.random() * (high - low + 1) + low);
}

app.get('/list/students', function (req, res) {
  var students = [];
  for(var i = 0; i < 10; i++) {
    var stu = {
      name : randomstring.generate(7),
      age : randomIntInc(1,100),
      major : 'CS'
    };
    students.push(stu);
  }

  res.send(students);  
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
