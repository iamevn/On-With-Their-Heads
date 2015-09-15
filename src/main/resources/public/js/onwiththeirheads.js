//TODO remove console.log fcns
//TODO white font?

var numBoxes = 9;

$(document).ready(function(){
  mirrorTextareas(numBoxes);

  $("#button1").click(function() {  
    $("#input1").swap({  
      target: "input2", // Mandatory. The ID of the element we want to swap with  
      opacity: "0.9", // if set, give the swapping elements a translucent effect in motion  
      speed: 500, // Optional. The time taken in milliseconds for the animation to occur  
      callback: function() { // Optional. Callback function once the swap is complete  
        $('#input1').attr('id','inputtemp1');
        $('#input2').attr('id','input1');
        $('#inputtemp1').attr('id','input2');
        $("#display1").swap({  
          target: "display2", // Mandatory. The ID of the element we want to swap with  
          opacity: "0.9", // if set, give the swapping elements a translucent effect in motion. 
          speed: 500, // Optional. The time taken in milliseconds for the animation to occur  
          callback: function() { // Optional. Callback function once the swap is complete  
            $('#display1').attr('id','displaytemp1');
            $('#display2').attr('id','display1');
            $('#displaytemp1').attr('id','display2');
          }  
        });  
      }  
    });  
  });  

  $("#button2").click(function() {  
    $("#input2").swap({  
      target: "input3", // Mandatory. The ID of the element we want to swap with  
      opacity: "0.9", // if set, give the swapping elements a translucent effect in motion  
      speed: 500, // Optional. The time taken in milliseconds for the animation to occur  
      callback: function() { // Optional. Callback function once the swap is complete  
        $('#input2').attr('id','inputtemp2');
        $('#input3').attr('id','input2');
        $('#inputtemp2').attr('id','input3');
        $("#display2").swap({  
          target: "display3", // Mandatory. The ID of the element we want to swap with  
          opacity: "0.9", // if set, give the swapping elements a translucent effect in motion 
          speed: 500, // Optional. The time taken in milliseconds for the animation to occur  
          callback: function() { // Optional. Callback function once the swap is complete  
            $('#display2').attr('id','displaytemp2');
            $('#display3').attr('id','display2');
            $('#displaytemp2').attr('id','display3');
          }  
        });  
      }  
    });  
  });  

}); 

function mirrorTextareas(numBoxes){
  var readonly;
  var readwrite;
  for(var i = 0; i < numBoxes; i++){
    //first column is ro displayarea vals, second column is normal inputarea vals
    //this fcn mirror the inputarea to the displayarea
    readonly = $("textarea")[i];
    readwrite = $("textarea")[i+numBoxes];
    $(readwrite).mirror(readonly); 
  }

}

$.fn.mirror = function (selector) {
  return this.each(function () {
    var $this = $(this);
    var $selector = $(selector);
    $this.bind('keyup', function () {
      $selector.val(($this.val()));
    });
  });
};

/* not used for now
   function populateTextareaMatrix(matrixLength) {
   var matrix = new Array(matrixLength);
   for(var i = 0; i < matrixLength; i++){
   matrix[i] = new Array(2);
   }
   for(var i = 0; i < (matrixLength * 2); i++){
        // the readonly textareas are the first 9
        if(i < matrixLength){
        matrix[0][i] = $("textarea")[i];
        } else { //the normal textareas are the next 9
        matrix[1][i] = $("textarea")[i];
        }
        }
        return matrix;
        };
        */
