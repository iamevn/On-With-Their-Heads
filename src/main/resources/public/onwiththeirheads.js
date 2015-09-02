$(document).ready(function(){
  $('#rw1').mirror('#ro1'); 
}); 


$.fn.mirror = function (selector) {
    return this.each(function () {
        var $this = $(this);
        var $selector = $(selector);
        $this.bind('keyup', function () {
            $selector.val(($this.val()));
        });
    });
};
